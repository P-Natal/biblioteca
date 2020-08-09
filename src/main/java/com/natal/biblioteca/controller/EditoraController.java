package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.Editora;
import com.natal.biblioteca.infrastructure.entities.EditoraEntity;
import com.natal.biblioteca.infrastructure.repository.EditoraRepository;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/editora-controller")
public class EditoraController {

    private EditoraRepository repository = new EditoraRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-editoras")
    public List<Editora> todosEditoraes(){

        List<Editora> editoras =  new ArrayList<Editora>();

        List<EditoraEntity> listaEditoraEntity = repository.buscaTodos();

        for (EditoraEntity entity : listaEditoraEntity) {
            editoras.add(
                    new Editora(
                            entity.getId(),
                            entity.getNome(),
                            entity.getPais()
                    )
            );
        }
        return editoras;
    }

    @GET
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/deletar")
    public Editora buscaEditora(Long id){
        EditoraEntity entity = repository.getEditora(id);
        return new Editora(
                entity.getId(),
                entity.getNome(),
                entity.getPais()
        );
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(Editora editora){
        EditoraEntity entity;
        try {
            entity = new EditoraEntity(
                    editora.getNome(),
                    editora.getPais()
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
    public String deletar(Editora editora){
        try {
            repository.excluir(editora.getId());
            return "Editora removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao remover o registro " + e.getMessage();
        }
    }

}
