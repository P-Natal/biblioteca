package com.natal.biblioteca.controller.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArtigoDePeriodico extends Publicacao{

    private Long id;

    private Periodico periodico;

    private int edicao;

    private int volume;

    public ArtigoDePeriodico() {
    }

    public ArtigoDePeriodico(Long id, String titulo, String data_publicacao, boolean acesso_livre, int doi, Autor autor, Periodico periodico, int edicao, int volume) {
        super(titulo, data_publicacao, acesso_livre, doi, autor);
        this.id = id;
        this.periodico = periodico;
        this.edicao = edicao;
        this.volume = volume;
    }

    public ArtigoDePeriodico(String titulo, String data_publicacao, boolean acesso_livre, int doi, Autor autor, Periodico periodico, int edicao, int volume) {
        super(titulo, data_publicacao, acesso_livre, doi, autor);
        this.periodico = periodico;
        this.edicao = edicao;
        this.volume = volume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
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

    @Override
    public String toString() {
        return "ArtigoDePeriodico{" +
                "id=" + id +
                ", periodico=" + periodico +
                ", edicao=" + edicao +
                ", volume=" + volume +
                '}';
    }
}
