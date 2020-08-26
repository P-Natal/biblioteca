package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.Publicacao;
import com.natal.biblioteca.infrastructure.entities.AutorEntity;
import com.natal.biblioteca.infrastructure.entities.EditoraEntity;
import com.natal.biblioteca.infrastructure.entities.PublicacaoEntity;
import com.natal.biblioteca.infrastructure.repository.AutorRepository;
import com.natal.biblioteca.infrastructure.repository.PublicacaoRepository;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/publicacao-controller")
public class PublicacaoController {

    private PublicacaoRepository repository = new PublicacaoRepository();
    private AutorRepository autorRepository = new AutorRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-todos")
    public List<Publicacao> todosPublicacaoes(){

        List<Publicacao> publicacaoes =  new ArrayList<Publicacao>();

        List<PublicacaoEntity> listaPublicacaoEntity = repository.buscaTodos();

        for (PublicacaoEntity entity : listaPublicacaoEntity) {
            publicacaoes.add(new Publicacao(entity.getId(),
                    entity.getTitulo(),
                    entity.getData_publicacao(),
                    entity.isAcesso_livre(),
                    entity.getDoi(),
                    entity.getAutor().getId())
            );
        }
        return publicacaoes;
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/buscar")
    public Publicacao buscaPublicacao(Long id){
        PublicacaoEntity entity = repository.getPublicacao(id);
        return new Publicacao(
                entity.getId(),
                entity.getTitulo(),
                entity.getData_publicacao(),
                entity.isAcesso_livre(),
                entity.getDoi(),
                entity.getAutor().getId()
        );
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/cadastrar")
    public String cadastrar(Publicacao publicacao){
        PublicacaoEntity entity;
        try {

            AutorEntity autorEntity = autorRepository.getAutor(publicacao.getId_autor());
            if (autorEntity != null){
                entity = new PublicacaoEntity(
                        publicacao.getTitulo(),
                        publicacao.getData_publicacao(),
                        publicacao.isAcesso_livre(),
                        publicacao.getDoi(),
                        autorEntity
                );
                repository.salvar(entity);
                return "Registro cadastrado com sucesso!";
            }
            else {
                throw new NullPointerException();
            }

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
            return "Publicacao removido com sucesso!";
        } catch (Exception e) {
            return "Erro ao remover o registro " + e.getMessage();
        }
    }


}
