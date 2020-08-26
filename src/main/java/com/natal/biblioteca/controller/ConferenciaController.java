package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.Conferencia;
import com.natal.biblioteca.infrastructure.entities.ConferenciaEntity;
import com.natal.biblioteca.infrastructure.repository.ConferenciaRepository;

import javax.ws.rs.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/conferencia-controller")
public class ConferenciaController {

    private ConferenciaRepository repository = new ConferenciaRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-conferencias")
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
                    String.valueOf(entity.getData_inicio()),
                    String.valueOf(entity.getData_fim())
            ));
        }
        return conferenciaes;
    }

    @GET
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/deletar")
    public Conferencia buscaConferencia(Long id){
        ConferenciaEntity entity = repository.getConferencia(id);
        return new Conferencia(
                entity.getId(),
                entity.getNome(),
                entity.getAcronimo(),
                entity.getEdicao(),
                entity.getCidade(),
                entity.getPais(),
                String.valueOf(entity.getData_inicio()),
                String.valueOf(entity.getData_fim())
        );
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(Conferencia conferencia){
        ConferenciaEntity entity;
        try {

            Date data_inicio_conferencia = new SimpleDateFormat("dd-MM-yyyy").parse(conferencia.getData_inicio());
            Date data_fim_conferencia = new SimpleDateFormat("dd-MM-yyyy").parse(conferencia.getData_fim());


            entity = new ConferenciaEntity(
                    conferencia.getNome(),
                    conferencia.getAcronimo(),
                    conferencia.getEdicao(),
                    conferencia.getCidade(),
                    conferencia.getPais(),
                    data_inicio_conferencia,
                    data_fim_conferencia
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
    public String deletar(Conferencia conferencia){
        try {
            repository.excluir(conferencia.getId());
            return "Conferencia removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao remover o registro " + e.getMessage();
        }
    }

}
