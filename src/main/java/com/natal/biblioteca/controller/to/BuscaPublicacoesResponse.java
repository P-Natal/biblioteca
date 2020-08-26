package com.natal.biblioteca.controller.to;

import com.natal.biblioteca.controller.model.*;
import com.natal.biblioteca.infrastructure.entities.ArtigoDeConferenciaEntity;
import com.natal.biblioteca.infrastructure.entities.ArtigoDePeriodicoEntity;
import com.natal.biblioteca.infrastructure.entities.CapituloEntity;
import com.natal.biblioteca.infrastructure.entities.LivroEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class BuscaPublicacoesResponse {

    List<Livro> livros;
    List<Capitulo> capitulos;
    List<ArtigoDeConferencia> artigosDeConferencia;
    List<ArtigoDePeriodico> artigosDePeriodico;

    public BuscaPublicacoesResponse(List<LivroEntity> livros, List<CapituloEntity> capitulos, List<ArtigoDeConferenciaEntity> artigosDeConferencia, List<ArtigoDePeriodicoEntity> artigosDePeriodico) {
        this.livros = assembleLivro(livros);
        this.capitulos = assembleCapitulos(capitulos);
        this.artigosDeConferencia = assembleArtigosDeConferencia(artigosDeConferencia);
        this.artigosDePeriodico = assembleArtigosDePeriodico(artigosDePeriodico);
    }

    private List<Livro> assembleLivro(List<LivroEntity> listaLivroEntity) {
        List<Livro> livros =  new ArrayList<Livro>();

        for (LivroEntity entity : listaLivroEntity) {
            livros.add(
                    new Livro(
                            entity.getId(),
                            entity.getTitulo(),
                            entity.getData_publicacao(),
                            entity.isAcesso_livre(),
                            entity.getDoi(),
                            entity.getAutor().getId(),
                            new Editora(
                                    entity.getEditora().getId(),
                                    entity.getEditora().getNome(),
                                    entity.getEditora().getPais()
                            ),
                            entity.getIsbn()
                    )
            );
        }
        return livros;
    }

    private List<Capitulo> assembleCapitulos(List<CapituloEntity> listaCapituloEntity) {

        List<Capitulo> capitulos =  new ArrayList<Capitulo>();

        for (CapituloEntity entity : listaCapituloEntity) {
            capitulos.add(
                    new Capitulo(
                            entity.getId(),
                            new Livro(
                                    entity.getLivro().getId(),
                                    new Editora(
                                            entity.getLivro().getEditora().getId(),
                                            entity.getLivro().getEditora().getNome(),
                                            entity.getLivro().getEditora().getPais()
                                    ),
                                    entity.getLivro().getIsbn()
                            )
                    )
            );
        }
        return capitulos;
    }

    private List<ArtigoDeConferencia> assembleArtigosDeConferencia(List<ArtigoDeConferenciaEntity> listaArtigoDeConferenciaEntities) {

        List<ArtigoDeConferencia> artigoDeConferencias =  new ArrayList<ArtigoDeConferencia>();

        for (ArtigoDeConferenciaEntity entity : listaArtigoDeConferenciaEntities) {
            artigoDeConferencias.add(new ArtigoDeConferencia(
                    entity.getId(),
                    entity.getTitulo(),
                    entity.getData_publicacao(),
                    entity.isAcesso_livre(),
                    entity.getDoi(),
                    entity.getAutor().getId(),
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

    private List<ArtigoDePeriodico> assembleArtigosDePeriodico(List<ArtigoDePeriodicoEntity> listaArtigoDePeriodicoEntity) {

        List<ArtigoDePeriodico> artigos =  new ArrayList<ArtigoDePeriodico>();

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

}
