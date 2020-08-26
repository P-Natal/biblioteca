package com.natal.biblioteca.controller;

import com.natal.biblioteca.controller.model.Autor;
import com.natal.biblioteca.controller.to.ConsultaResponseTO;
import com.natal.biblioteca.infrastructure.entities.ArtigoDeConferenciaEntity;
import com.natal.biblioteca.infrastructure.entities.ArtigoDePeriodicoEntity;
import com.natal.biblioteca.infrastructure.entities.LivroEntity;
import com.natal.biblioteca.infrastructure.repository.ArtigoDeConferenciaRepository;
import com.natal.biblioteca.infrastructure.repository.ArtigoDePeriodicoRepository;
import com.natal.biblioteca.infrastructure.repository.AutorRepository;
import com.natal.biblioteca.infrastructure.repository.LivroRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/consulta")
public class ConsultaController {

    private AutorRepository autorRepository = new AutorRepository();
    private LivroRepository livroRepository = new LivroRepository();
    private ArtigoDePeriodicoRepository artigoDePeriodicoRepository = new ArtigoDePeriodicoRepository();
    private ArtigoDeConferenciaRepository artigoDeConferenciaRepository = new ArtigoDeConferenciaRepository();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/{autor_id}/publicacoes")
    public List<ConsultaResponseTO> todasPublicacoes(@PathParam("autor_id") Long autor_id){
        List<ConsultaResponseTO> consultaResponse = new ArrayList<ConsultaResponseTO>();

        List<LivroEntity> livroEntities = livroRepository.getLivroPorAutor(autor_id);
        List<ArtigoDePeriodicoEntity> artigosPeriod = artigoDePeriodicoRepository.getArtigoPorAutor(autor_id);
        List<ArtigoDeConferenciaEntity> artigosConf = artigoDeConferenciaRepository.getArtigoPorAutor(autor_id);

        for (LivroEntity entity : livroEntities){
            consultaResponse.add(
                    new ConsultaResponseTO(
                            entity.getId(),
                            entity.getTitulo(),
                            entity.getClass().getSimpleName()
                    )
            );
        }
        for (ArtigoDePeriodicoEntity entity : artigosPeriod){
            consultaResponse.add(
                    new ConsultaResponseTO(
                            entity.getId(),
                            entity.getTitulo(),
                            entity.getClass().getSimpleName()
                    )
            );
        }
        for (ArtigoDeConferenciaEntity entity : artigosConf){
            consultaResponse.add(
                    new ConsultaResponseTO(
                            entity.getId(),
                            entity.getTitulo(),
                            entity.getClass().getSimpleName()
                    )
            );
        }
        return consultaResponse;
    }


    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/{autor_id}/publicacoes/{tipo}")
    public List<ConsultaResponseTO> todosAutores(@PathParam("autor_id") Long autor_id, @PathParam("tipo") String tipo){
        List<ConsultaResponseTO> consultaResponse = new ArrayList<ConsultaResponseTO>();

        List<LivroEntity> livroEntities = livroRepository.getLivroPorAutor(autor_id);
        List<ArtigoDePeriodicoEntity> artigosPeriod = artigoDePeriodicoRepository.getArtigoPorAutor(autor_id);
        List<ArtigoDeConferenciaEntity> artigosConf = artigoDeConferenciaRepository.getArtigoPorAutor(autor_id);

        if (tipo.equals("livro")){
            for (LivroEntity entity : livroEntities){
                consultaResponse.add(
                        new ConsultaResponseTO(
                                entity.getId(),
                                entity.getTitulo(),
                                entity.getClass().getSimpleName()
                        )
                );
            }
        }
        else if (tipo.equals("artigo-conferencia")){
            for (ArtigoDeConferenciaEntity entity : artigosConf){
                consultaResponse.add(
                        new ConsultaResponseTO(
                                entity.getId(),
                                entity.getTitulo(),
                                entity.getClass().getSimpleName()
                        )
                );
            }
        }
        else if(tipo.equals("artigo-periodico")){
            for (ArtigoDePeriodicoEntity entity : artigosPeriod){
                consultaResponse.add(
                        new ConsultaResponseTO(
                                entity.getId(),
                                entity.getTitulo(),
                                entity.getClass().getSimpleName()
                        )
                );
            }
        }
        return consultaResponse;
    }

}
