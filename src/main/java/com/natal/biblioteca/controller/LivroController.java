package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.Editora;
import com.natal.biblioteca.controller.model.Livro;
import com.natal.biblioteca.infrastructure.entities.AutorEntity;
import com.natal.biblioteca.infrastructure.entities.EditoraEntity;
import com.natal.biblioteca.infrastructure.entities.LivroEntity;
import com.natal.biblioteca.infrastructure.repository.AutorRepository;
import com.natal.biblioteca.infrastructure.repository.EditoraRepository;
import com.natal.biblioteca.infrastructure.repository.LivroRepository;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

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
                            entity.getData_publicacao(),
                            entity.isAcesso_livre(),
                            entity.getDoi(),
                            entity.getAutor().getId(),
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
    @Path("/deletar")
    public Livro buscaLivro(Long id){
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
                livroEntity.getData_publicacao(),
                livroEntity.isAcesso_livre(),
                livroEntity.getDoi(),
                livroEntity.getAutor().getId(),
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
            AutorEntity autorEntity = autorRepository.getAutor(livro.getId_autor());

            if (autorEntity != null){
                entity = new LivroEntity(
                        livro.getTitulo(),
                        livro.getData_publicacao(),
                        livro.isAcesso_livre(),
                        livro.getDoi(),
                        autorEntity,
                        new EditoraEntity(
                                livro.getEditora().getNome(),
                                livro.getEditora().getPais()
                        ),
                        livro.getIsbn()
                );
                repository.salvar(entity);
                return "Registro cadastrado com sucesso!";
            }
            else {
                throw new NullPointerException("Autor nao pode ser nulo!");
            }

        } catch (Exception e) {
            return "Erro ao cadastrar um registro " + e.getMessage();
        }
    }

    @DELETE
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/deletar/{id}")
    public String deletar(@PathParam("id") int id){
        try {
            repository.excluir((long) id);
            return "Livro removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao remover o registro " + e.getMessage();
        }
    }

}
