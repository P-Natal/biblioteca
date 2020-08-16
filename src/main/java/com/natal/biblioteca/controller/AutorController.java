package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.Autor;
import com.natal.biblioteca.infrastructure.entities.AutorEntity;
import com.natal.biblioteca.infrastructure.repository.AutorRepository;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/autor")
public class AutorController {

    private AutorRepository repository = new AutorRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-todos")
    public List<Autor> todosAutores(){

        List<Autor> autores =  new ArrayList<Autor>();

        List<AutorEntity> listaAutorEntity = repository.buscaTodos();

        for (AutorEntity entity : listaAutorEntity) {
            autores.add(new Autor(entity.getId(), entity.getPrimeiro_nome(), entity.getNome_do_meio(), entity.getUltimo_nome(), entity.getAfiliacao(), entity.getEmail(), entity.getPais()));
        }
        return autores;
    }

    @GET
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/deletar")
    public Autor buscaAutor(Long id){
        AutorEntity autorEntity = repository.getAutor(id);
        return new Autor(
                autorEntity.getId(),
                autorEntity.getPrimeiro_nome(),
                autorEntity.getNome_do_meio(),
                autorEntity.getUltimo_nome(),
                autorEntity.getAfiliacao(),
                autorEntity.getEmail(),
                autorEntity.getPais()
        );
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(Autor autor){
        AutorEntity entity;
        try {
            entity = new AutorEntity(
                    autor.getPrimeiro_nome(),
                    autor.getNome_do_meio(),
                    autor.getUltimo_nome(),
                    autor.getAfiliacao(),
                    autor.getEmail(),
                    autor.getPais()
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
    @Path("/deletar/{id}")
    public String deletar(@PathParam("id") int id){
        try {
            repository.excluir((long) id);
            return "Autor removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao remover o registro " + e.getMessage();
        }
    }

}
