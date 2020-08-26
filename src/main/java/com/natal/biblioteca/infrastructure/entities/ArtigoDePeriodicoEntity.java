package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "artigo_de_periodico")
@NamedQueries({
        @NamedQuery(name = "buscaTodosArtigoDePeriodico", query = "select art from ArtigoDePeriodicoEntity art"),
        @NamedQuery(name = "buscarArtPeriodPorAutor", query = "select art from ArtigoDePeriodicoEntity art join art.autor a where a.id = :autor_id")
})
public class ArtigoDePeriodicoEntity extends PublicacaoEntity{

    @ManyToOne
    private PeriodicoEntity periodico;

    @Column(name = "edicao")
    private int edicao;

    @Column(name = "volume")
    private int volume;

    public ArtigoDePeriodicoEntity() {
        super();
    }

    public ArtigoDePeriodicoEntity(String titulo, Date data_publicacao, boolean acesso_livre, int doi, AutorEntity autorEntity, PeriodicoEntity periodicoEntity, int edicao, int volume) {
        super(titulo, data_publicacao, acesso_livre, doi, autorEntity);
        this.periodico = periodicoEntity;
        this.edicao = edicao;
        this.volume = volume;
    }

    public ArtigoDePeriodicoEntity(Long id, String titulo, Date data_publicacao, boolean acesso_livre, int doi, AutorEntity autorEntity, PeriodicoEntity periodicoEntity, int edicao, int volume) {
        super(titulo, data_publicacao, acesso_livre, doi, autorEntity);
        this.setId(id);
        this.periodico = periodicoEntity;
        this.edicao = edicao;
        this.volume = volume;
    }

    public PeriodicoEntity getPeriodico() {
        return periodico;
    }

    public void setPeriodico(PeriodicoEntity periodico) {
        this.periodico = periodico;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
