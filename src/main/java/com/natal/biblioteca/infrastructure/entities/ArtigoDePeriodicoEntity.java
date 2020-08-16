package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "buscaTodosArtigoDePeriodico", query = "select art from ArtigoDePeriodicoEntity art")
public class ArtigoDePeriodicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private PeriodicoEntity periodico;

    @Column(name = "edicao")
    private int edicao;

    @Column(name = "volume")
    private int volume;

    public ArtigoDePeriodicoEntity(PeriodicoEntity periodicoEntity, int edicao, int volume) {
        this.periodico = periodicoEntity;
        this.edicao = edicao;
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
