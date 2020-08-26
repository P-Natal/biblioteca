package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.ArtigoDeConferencia;
import com.natal.biblioteca.controller.model.Conferencia;
import com.natal.biblioteca.infrastructure.entities.ArtigoDeConferenciaEntity;
import com.natal.biblioteca.infrastructure.entities.AutorEntity;
import com.natal.biblioteca.infrastructure.entities.ConferenciaEntity;
import com.natal.biblioteca.infrastructure.repository.ArtigoDeConferenciaRepository;
import com.natal.biblioteca.infrastructure.repository.AutorRepository;
import com.natal.biblioteca.infrastructure.repository.ConferenciaRepository;

import javax.ws.rs.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/artigo-conferencia-controller")
public class ArtigoDeConferenciaController {

    private ArtigoDeConferenciaRepository repository = new ArtigoDeConferenciaRepository();
    private ConferenciaRepository conferenciaRepository = new ConferenciaRepository();
    private AutorRepository autorRepository = new AutorRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-todos")
    public List<ArtigoDeConferencia> todosArtigos(){

        List<ArtigoDeConferencia> artigoDeConferencias =  new ArrayList<ArtigoDeConferencia>();

        List<ArtigoDeConferenciaEntity> listatArtigoDeConferenciaEntities = repository.buscaTodos();

        for (ArtigoDeConferenciaEntity entity : listatArtigoDeConferenciaEntities) {
            artigoDeConferencias.add(new ArtigoDeConferencia(
                        entity.getId(),
                        new Conferencia(
                                entity.getConferencia().getId(),
                                entity.getConferencia().getNome(),
                                entity.getConferencia().getAcronimo(),
                                entity.getConferencia().getEdicao(),
                                entity.getConferencia().getCidade(),
                                entity.getConferencia().getPais(),
                                String.valueOf(entity.getConferencia().getData_inicio()),
                                String.valueOf(entity.getConferencia().getData_fim())
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
                        String.valueOf(entity.getConferencia().getData_inicio()),
                        String.valueOf(entity.getConferencia().getData_fim())
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
            ConferenciaEntity conferenciaEntity = conferenciaRepository.getConferencia(artigo.getConferencia().getId());
            AutorEntity autorEntity = autorRepository.getAutor(artigo.getAutor().getId());

            Date data_publicacao = new SimpleDateFormat("dd-MM-yyyy").parse(artigo.getData_publicacao());

            if (conferenciaEntity != null && autorEntity != null){

                entity = new ArtigoDeConferenciaEntity(
                        artigo.getTitulo(),
                        data_publicacao,
                        artigo.isAcesso_livre(),
                        artigo.getDoi(),
                        autorEntity,
                        conferenciaEntity,
                        artigo.getTipo()
                );
                repository.salvar(entity);
                return "Registro cadastrado com sucesso!";
            }
            else {
                throw new NullPointerException("Autor e Conferencia nao podem ser nulos!");
            }
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
