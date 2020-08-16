package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.Editora;
import com.natal.biblioteca.controller.model.Periodico;
import com.natal.biblioteca.infrastructure.entities.EditoraEntity;
import com.natal.biblioteca.infrastructure.entities.PeriodicoEntity;
import com.natal.biblioteca.infrastructure.repository.EditoraRepository;
import com.natal.biblioteca.infrastructure.repository.PeriodicoRepository;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/periodico-controller")
public class PeriodicoController {

    private PeriodicoRepository repository = new PeriodicoRepository();
    private EditoraRepository editoraRepository = new EditoraRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-periodicos")
    public List<Periodico> todosPeriodicoes(){

        List<Periodico> periodicos =  new ArrayList<Periodico>();

        List<PeriodicoEntity> listaPeriodicoEntity = repository.buscaTodos();

        for (PeriodicoEntity entity : listaPeriodicoEntity) {
            periodicos.add(
                    new Periodico(
                            entity.getId(),
                            new Editora(
                                    entity.getEditora().getId(),
                                    entity.getEditora().getNome(),
                                    entity.getEditora().getPais()
                            ),
                            entity.getTitulo(),
                            entity.getAcronimo(),
                            entity.getIssn()
                    )
            );
        }
        return periodicos;
    }

    @GET
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/deletar")
    public Periodico buscaPeriodico(Long id){
        PeriodicoEntity entity = repository.getPeriodico(id);

        return new Periodico(
                entity.getId(),
                new Editora(
                        entity.getEditora().getId(),
                        entity.getEditora().getNome(),
                        entity.getEditora().getPais()
                ),
                entity.getTitulo(),
                entity.getAcronimo(),
                entity.getIssn()
        );
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(Periodico periodico){
        PeriodicoEntity entity;
        try {

            EditoraEntity editoraEntity = editoraRepository.getEditora(periodico.getEditora().getId());

            if (editoraEntity == null){
                editoraEntity = new EditoraEntity(
                        periodico.getEditora().getNome(),
                        periodico.getEditora().getPais()
                );
            }

            entity = new PeriodicoEntity(
                    editoraEntity,
                    periodico.getTitulo(),
                    periodico.getAcronimo(),
                    periodico.getIssn()
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
    public String deletar(Periodico periodico){
        try {
            repository.excluir(periodico.getId());
            return "Periodico removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao remover o registro " + e.getMessage();
        }
    }

}
