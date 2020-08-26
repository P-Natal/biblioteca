package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.Autor;
import com.natal.biblioteca.controller.model.Editora;
import com.natal.biblioteca.controller.model.Livro;
import com.natal.biblioteca.infrastructure.entities.AutorEntity;
import com.natal.biblioteca.infrastructure.entities.EditoraEntity;
import com.natal.biblioteca.infrastructure.entities.LivroEntity;
import com.natal.biblioteca.infrastructure.repository.AutorRepository;
import com.natal.biblioteca.infrastructure.repository.EditoraRepository;
import com.natal.biblioteca.infrastructure.repository.LivroRepository;

import javax.swing.text.DateFormatter;
import javax.ws.rs.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Path("/livro-controller")
public class LivroController {

    private LivroRepository repository = new LivroRepository();
    private EditoraRepository editoraRepository = new EditoraRepository();
    private AutorRepository autorRepository = new AutorRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-livros")
    public List<Livro> todosLivroes(){

        List<Livro> livros =  new ArrayList<Livro>();

        List<LivroEntity> listaLivroEntity = repository.buscaTodos();

        for (LivroEntity entity : listaLivroEntity) {
            livros.add(
                    new Livro(
                            entity.getId(),
                            entity.getTitulo(),
                            String.valueOf(entity.getData_publicacao()),
                            entity.isAcesso_livre(),
                            entity.getDoi(),
                            new Autor(
                                    entity.getId(),
                                    entity.getAutor().getPrimeiro_nome(),
                                    entity.getAutor().getNome_do_meio(),
                                    entity.getAutor().getUltimo_nome(),
                                    entity.getAutor().getAfiliacao(),
                                    entity.getAutor().getEmail(),
                                    entity.getAutor().getPais()
                            ),
                            new Editora(
                                    entity.getEditora().getId(),
                                    entity.getEditora().getNome(),
                                    entity.getEditora().getPais()
                            ),
                            entity.getIsbn()
                    )
            );
        }
        return livros;
    }

    @GET
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/buscar/{id}")
    public Livro buscaLivro(@PathParam("id") Long id){
        LivroEntity livroEntity = repository.getLivro(id);

        EditoraEntity editoraEntity = editoraRepository.getEditora(livroEntity.getEditora().getId());

        if (editoraEntity == null) {
            editoraEntity = new EditoraEntity(
                    livroEntity.getEditora().getNome(),
                    livroEntity.getEditora().getPais()
            );
        }

        return new Livro(
                livroEntity.getId(),
                livroEntity.getTitulo(),
                String.valueOf(livroEntity.getData_publicacao()),
                livroEntity.isAcesso_livre(),
                livroEntity.getDoi(),
                new Autor(
                        livroEntity.getId(),
                        livroEntity.getAutor().getPrimeiro_nome(),
                        livroEntity.getAutor().getNome_do_meio(),
                        livroEntity.getAutor().getUltimo_nome(),
                        livroEntity.getAutor().getAfiliacao(),
                        livroEntity.getAutor().getEmail(),
                        livroEntity.getAutor().getPais()
                ),
                new Editora(editoraEntity.getId(), editoraEntity.getNome(), editoraEntity.getPais()),
                livroEntity.getIsbn()
        );
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(Livro livro){
        LivroEntity entity;
        try {
            EditoraEntity editoraEntity = editoraRepository.getEditora(livro.getEditora().getId());
            AutorEntity autorEntity = autorRepository.getAutor(livro.getAutor().getId());

            Date data_publicacao = new SimpleDateFormat("dd-MM-yyyy").parse(livro.getData_publicacao());

            if (autorEntity != null && editoraEntity != null){
                entity = new LivroEntity(
                        livro.getTitulo(),
                        data_publicacao,
                        livro.isAcesso_livre(),
                        livro.getDoi(),
                        autorEntity,
                        editoraEntity,
                        livro.getIsbn()
                );
                repository.salvar(entity);
                return "Registro cadastrado com sucesso!";
            }
            else{
                throw new NullPointerException("Autor e editora nao podem ser nulos!");
            }
        } catch (Exception e) {
            return "Erro ao cadastrar um registro " + e.getMessage();
        }
    }

    @DELETE
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/deletar")
    public String deletar(Livro livro){
        try {
            repository.excluir(livro.getId());
            return "Livro removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao remover o registro " + e.getMessage();
        }
    }

}
