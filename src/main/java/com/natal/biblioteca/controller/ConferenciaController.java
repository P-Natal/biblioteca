package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.Conferencia;
import com.natal.biblioteca.infrastructure.entities.ConferenciaEntity;
import com.natal.biblioteca.infrastructure.repository.ConferenciaRepository;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/conferencia-controller")
public class ConferenciaController {

    private ConferenciaRepository repository = new ConferenciaRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-conferenciaes")
    public List<Conferencia> todosConferenciaes(){

        List<Conferencia> conferenciaes =  new ArrayList<Conferencia>();

        List<ConferenciaEntity> listaConferenciaEntity = repository.buscaTodos();

        for (ConferenciaEntity entity : listaConferenciaEntity) {
            conferenciaes.add(new Conferencia(
                    entity.getId(),
                    entity.getNome(),
                    entity.getAcronimo(),
                    entity.getEdicao(),
                    entity.getCidade(),
                    entity.getPais(),
                    entity.getData_inicio(),
                    entity.getData_fim()
            ));
        }
        return conferenciaes;
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/buscar")
    public Conferencia buscaConferencia(Long id){
        ConferenciaEntity entity = repository.getConferencia(id);
        return new Conferencia(
                entity.getId(),
                entity.getNome(),
                entity.getAcronimo(),
                entity.getEdicao(),
                entity.getCidade(),
                entity.getPais(),
                entity.getData_inicio(),
                entity.getData_fim()
        );
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(Conferencia conferencia){
        ConferenciaEntity entity;
        try {
            entity = new ConferenciaEntity(
                    conferencia.getNome(),
                    conferencia.getAcronimo(),
                    conferencia.getEdicao(),
                    conferencia.getCidade(),
                    conferencia.getPais(),
                    conferencia.getData_inicio(),
                    conferencia.getData_fim()
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
            return "Conferencia removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao remover o registro " + e.getMessage();
        }
    }

}
