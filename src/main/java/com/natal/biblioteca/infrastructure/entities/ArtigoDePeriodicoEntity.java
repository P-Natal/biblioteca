package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "buscaTodos", query = "select art from ArtigoDePeriodicoEntity art")
public class ArtigoDePeriodicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private PeriodicoEntity periodicoEntity;

    @Column(name = "edicao")
    private int edicao;

    @Column(name = "volume")
    private int volume;

    public ArtigoDePeriodicoEntity(PeriodicoEntity periodicoEntity, int edicao, int volume) {
        this.periodicoEntity = periodicoEntity;
        this.edicao = edicao;
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PeriodicoEntity getPeriodicoEntity() {
        return periodicoEntity;
    }

    public void setPeriodicoEntity(PeriodicoEntity periodicoEntity) {
        this.periodicoEntity = periodicoEntity;
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
