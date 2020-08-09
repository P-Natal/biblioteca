package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.Editora;
import com.natal.biblioteca.controller.model.Livro;
import com.natal.biblioteca.infrastructure.entities.EditoraEntity;
import com.natal.biblioteca.infrastructure.entities.LivroEntity;
import com.natal.biblioteca.infrastructure.repository.EditoraRepository;
import com.natal.biblioteca.infrastructure.repository.LivroRepository;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/livro-controller")
public class LivroController {

    private LivroRepository repository = new LivroRepository();
    private EditoraRepository editoraRepository = new EditoraRepository();

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
                            new Editora(
                                    entity.getEditoraEntity().getId(),
                                    entity.getEditoraEntity().getNome(),
                                    entity.getEditoraEntity().getPais()
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

        EditoraEntity editoraEntity = editoraRepository.getEditora(livroEntity.getEditoraEntity().getId());

        if (editoraEntity == null) {
            editoraEntity = new EditoraEntity(
                    livroEntity.getEditoraEntity().getNome(),
                    livroEntity.getEditoraEntity().getPais()
            );
        }

        return new Livro(
                livroEntity.getId(),
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
            entity = new LivroEntity(
                    new EditoraEntity(
                            livro.getEditora().getNome(),
                            livro.getEditora().getPais()
                    ),
                    livro.getIsbn()
            );
            repository.salvar(entity);
            return "Registro cadastrado com sucesso!";
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
