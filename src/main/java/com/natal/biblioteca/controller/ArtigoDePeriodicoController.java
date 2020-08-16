package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.ArtigoDePeriodico;
import com.natal.biblioteca.controller.model.Editora;
import com.natal.biblioteca.controller.model.Periodico;
import com.natal.biblioteca.infrastructure.entities.ArtigoDePeriodicoEntity;
import com.natal.biblioteca.infrastructure.entities.EditoraEntity;
import com.natal.biblioteca.infrastructure.entities.PeriodicoEntity;
import com.natal.biblioteca.infrastructure.repository.ArtigoDePeriodicoRepository;
import com.natal.biblioteca.infrastructure.repository.EditoraRepository;
import com.natal.biblioteca.infrastructure.repository.PeriodicoRepository;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/artigo-periodico-controller")
public class ArtigoDePeriodicoController {

    private ArtigoDePeriodicoRepository repository = new ArtigoDePeriodicoRepository();
    private PeriodicoRepository periodicoRepository = new PeriodicoRepository();
    private EditoraRepository editoraoRepository = new EditoraRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-artigos")
    public List<ArtigoDePeriodico> todosArtigoDePeriodicoes(){

        List<ArtigoDePeriodico> artigos =  new ArrayList<ArtigoDePeriodico>();

        List<ArtigoDePeriodicoEntity> listaArtigoDePeriodicoEntity = repository.buscaTodos();

        for (ArtigoDePeriodicoEntity entity : listaArtigoDePeriodicoEntity) {
            artigos.add(new ArtigoDePeriodico(
                    entity.getId(),
                    new Periodico(
                            entity.getPeriodico().getId(),
                            new Editora(
                                    entity.getPeriodico().getEditora().getId(),
                                    entity.getPeriodico().getEditora().getNome(),
                                    entity.getPeriodico().getEditora().getPais()
                            ),
                            entity.getPeriodico().getTitulo(),
                            entity.getPeriodico().getAcronimo(),
                            entity.getPeriodico().getIssn()
                    ),
                    entity.getEdicao(),
                    entity.getVolume()
            ));
        }
        return artigos;
    }

    @GET
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/deletar")
    public ArtigoDePeriodico buscaArtigoDePeriodico(Long id){
        ArtigoDePeriodicoEntity entity = repository.getArtigo(id);
        return new ArtigoDePeriodico(
                entity.getId(),
                new Periodico(
                        entity.getPeriodico().getId(),
                        new Editora(
                                entity.getPeriodico().getEditora().getId(),
                                entity.getPeriodico().getEditora().getNome(),
                                entity.getPeriodico().getEditora().getPais()
                        ),
                        entity.getPeriodico().getTitulo(),
                        entity.getPeriodico().getAcronimo(),
                        entity.getPeriodico().getIssn()
                ),
                entity.getEdicao(),
                entity.getVolume()
        );
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(ArtigoDePeriodico artigo){
        ArtigoDePeriodicoEntity entity;
        try {
            EditoraEntity editoraEntity = editoraoRepository.getEditora(artigo.getPeriodico().getId());

            if (editoraEntity == null){
                editoraEntity = new EditoraEntity(
                        artigo.getPeriodico().getEditora().getNome(),
                        artigo.getPeriodico().getEditora().getPais()
                );
            }

            PeriodicoEntity periodicoEntity = periodicoRepository.getPeriodico(artigo.getPeriodico().getId());
            if (periodicoEntity == null){
                periodicoEntity = new PeriodicoEntity(
                        editoraEntity,
                        artigo.getPeriodico().getTitulo(),
                        artigo.getPeriodico().getAcronimo(),
                        artigo.getPeriodico().getIssn()
                );
            }

            entity = new ArtigoDePeriodicoEntity(
                    periodicoEntity,
                    artigo.getEdicao(),
                    artigo.getVolume()
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
    public String deletar(ArtigoDePeriodico artigo){
        try {
            repository.excluir(artigo.getId());
            return "ArtigoDePeriodico removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao remover o registro " + e.getMessage();
        }
    }

}
