package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.ArtigoDeConferencia;
import com.natal.biblioteca.controller.model.Conferencia;
import com.natal.biblioteca.infrastructure.entities.ArtigoDeConferenciaEntity;
import com.natal.biblioteca.infrastructure.entities.ConferenciaEntity;
import com.natal.biblioteca.infrastructure.repository.ArtigoDeConferenciaRepository;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/artigo-conferencia-controller")
public class ArtigoDeConferenciaController {

    private ArtigoDeConferenciaRepository repository = new ArtigoDeConferenciaRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-todos")
    public List<ArtigoDeConferencia> todosArtigos(){

        List<ArtigoDeConferencia> artigoDeConferencias =  new ArrayList<ArtigoDeConferencia>();

        List<ArtigoDeConferenciaEntity> listaartArtigoDeConferenciaEntities = repository.buscaTodos();

        for (ArtigoDeConferenciaEntity entity : listaartArtigoDeConferenciaEntities) {
            artigoDeConferencias.add(new ArtigoDeConferencia(
                        entity.getId(),
                        new Conferencia(
                                entity.getConferencia().getId(),
                                entity.getConferencia().getNome(),
                                entity.getConferencia().getAcronimo(),
                                entity.getConferencia().getEdicao(),
                                entity.getConferencia().getCidade(),
                                entity.getConferencia().getPais(),
                                entity.getConferencia().getData_inicio(),
                                entity.getConferencia().getData_fim()
                        ),
                        entity.getTipo()
            ));
        }
        return artigoDeConferencias;
    }

    @GET
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-artigo")
    public ArtigoDeConferencia buscaArtigo(Long id){
        ArtigoDeConferenciaEntity entity = repository.getArtigo(id);
        return new ArtigoDeConferencia(
                entity.getId(),
                new Conferencia(
                        entity.getConferencia().getId(),
                        entity.getConferencia().getNome(),
                        entity.getConferencia().getAcronimo(),
                        entity.getConferencia().getEdicao(),
                        entity.getConferencia().getCidade(),
                        entity.getConferencia().getPais(),
                        entity.getConferencia().getData_inicio(),
                        entity.getConferencia().getData_fim()
                ),
                entity.getTipo()
        );
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(ArtigoDeConferencia artigo){
        ArtigoDeConferenciaEntity entity;
        try {
            entity = new ArtigoDeConferenciaEntity(
                    artigo.getId(),
                    new ConferenciaEntity(
                            artigo.getConferenciaEntity().getNome(),
                            artigo.getConferenciaEntity().getAcronimo(),
                            artigo.getConferenciaEntity().getEdicao(),
                            artigo.getConferenciaEntity().getCidade(),
                            artigo.getConferenciaEntity().getPais(),
                            artigo.getConferenciaEntity().getData_inicio(),
                            artigo.getConferenciaEntity().getData_fim()
                    ),
                    artigo.getTipo()
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
    public String deletar(ArtigoDeConferencia artigo){
        try {
            repository.excluir(artigo.getId());
            return "Registro removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao remover o registro " + e.getMessage();
        }
    }

}
