package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.ArtigoDePeriodico;
import com.natal.biblioteca.controller.model.Autor;
import com.natal.biblioteca.controller.model.Editora;
import com.natal.biblioteca.controller.model.Periodico;
import com.natal.biblioteca.infrastructure.entities.ArtigoDePeriodicoEntity;
import com.natal.biblioteca.infrastructure.entities.AutorEntity;
import com.natal.biblioteca.infrastructure.entities.EditoraEntity;
import com.natal.biblioteca.infrastructure.entities.PeriodicoEntity;
import com.natal.biblioteca.infrastructure.repository.ArtigoDePeriodicoRepository;
import com.natal.biblioteca.infrastructure.repository.AutorRepository;
import com.natal.biblioteca.infrastructure.repository.EditoraRepository;
import com.natal.biblioteca.infrastructure.repository.PeriodicoRepository;

import javax.ws.rs.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/artigo-periodico-controller")
public class ArtigoDePeriodicoController {

    private ArtigoDePeriodicoRepository repository = new ArtigoDePeriodicoRepository();
    private PeriodicoRepository periodicoRepository = new PeriodicoRepository();
    private EditoraRepository editoraoRepository = new EditoraRepository();
    private AutorRepository autorRepository = new AutorRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-artigos")
    public List<ArtigoDePeriodico> todosArtigoDePeriodicoes(){

        List<ArtigoDePeriodico> artigos =  new ArrayList<ArtigoDePeriodico>();

        List<ArtigoDePeriodicoEntity> listaArtigoDePeriodicoEntity = repository.buscaTodos();

        for (ArtigoDePeriodicoEntity entity : listaArtigoDePeriodicoEntity) {
            artigos.add(new ArtigoDePeriodico(
                    entity.getId(),
                    entity.getTitulo(),
                    String.valueOf(entity.getData_publicacao()),
                    entity.isAcesso_livre(),
                    entity.getDoi(),
                    new Autor(
                            entity.getId(),
                            entity.getAutor().getPrimeiro_nome(),
                            entity.getAutor().getNome_do_meio(),
                            entity.getAutor().getUltimo_nome(),
                            entity.getAutor().getAfiliacao(),
                            entity.getAutor().getEmail(),
                            entity.getAutor().getPais()
                    ),
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
                entity.getTitulo(),
                String.valueOf(entity.getData_publicacao()),
                entity.isAcesso_livre(),
                entity.getDoi(),
                new Autor(
                        entity.getId(),
                        entity.getAutor().getPrimeiro_nome(),
                        entity.getAutor().getNome_do_meio(),
                        entity.getAutor().getUltimo_nome(),
                        entity.getAutor().getAfiliacao(),
                        entity.getAutor().getEmail(),
                        entity.getAutor().getPais()
                ),
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
            EditoraEntity editoraEntity = editoraoRepository.getEditora(artigo.getPeriodico().getEditora().getId());
            AutorEntity autorEntity = autorRepository.getAutor(artigo.getAutor().getId());
            Date data_publicacao = new SimpleDateFormat("dd-MM-yyyy").parse(artigo.getData_publicacao());

            if (editoraEntity != null && autorEntity != null){
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
                        artigo.getTitulo(),
                        data_publicacao,
                        artigo.isAcesso_livre(),
                        artigo.getDoi(),
                        autorEntity,
                        periodicoEntity,
                        artigo.getEdicao(),
                        artigo.getVolume()
                );
                repository.salvar(entity);
                return "Registro cadastrado com sucesso!";
            }
            else {
                throw new NullPointerException("Autor e editora nao podem ser nulos!");
            }

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
