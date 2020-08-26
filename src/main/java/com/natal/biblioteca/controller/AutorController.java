package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.to.BuscaPublicacoesResponse;
import com.natal.biblioteca.controller.model.*;
import com.natal.biblioteca.infrastructure.entities.*;
import com.natal.biblioteca.infrastructure.repository.*;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

@Path("/autor")
public class AutorController {

    private AutorRepository repository = new AutorRepository();
    private LivroRepository livroRepository = new LivroRepository();
    private CapituloRepository capituloRepository = new CapituloRepository();
    private ArtigoDeConferenciaRepository artigoDeConferenciaRepository = new ArtigoDeConferenciaRepository();
    private ArtigoDePeriodicoRepository artigoDePeriodicoRepository  = new ArtigoDePeriodicoRepository();

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
    @Produces("application/json; charset=UTF-8")
    @Path("/buscar")
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

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/busca-publicacoes")
    public BuscaPublicacoesResponse buscaTodasPublicacoesPorPrimNomeAutor(String primNome){
        AutorEntity autorEntity = repository.buscarPorPrimeiroNome(primNome).get(0);
        List<LivroEntity> livros = livroRepository.buscarPorAutor(autorEntity);
        List<CapituloEntity> capitulos = capituloRepository.buscarPorAutor(autorEntity);
        List<ArtigoDeConferenciaEntity> artigosDeConferencia = artigoDeConferenciaRepository.buscarPorAutor(autorEntity);
        List<ArtigoDePeriodicoEntity> artigosDePeriodico = artigoDePeriodicoRepository.buscarPorAutor(autorEntity);

        try{
            BuscaPublicacoesResponse response = new BuscaPublicacoesResponse(livros, capitulos, artigosDeConferencia, artigosDePeriodico);
            return response;
        }
        catch (Exception e){
            throw new RuntimeException("Falha ao gerar objeto de resposta");
        }
    }

}
