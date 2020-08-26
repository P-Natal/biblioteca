package com.natal.biblioteca.controller.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class ArtigoDePeriodico extends Publicacao {

    private Long id;

    private Periodico periodico;

    private int edicao;

    private int volume;

    public ArtigoDePeriodico() {
    }

    public ArtigoDePeriodico(Long id, Periodico periodico, int edicao, int volume) {
        this.id = id;
        this.periodico = periodico;
        this.edicao = edicao;
        this.volume = volume;
    }

    public ArtigoDePeriodico(String titulo, Date data_publicacao, boolean acesso_livre, int doi, Long id_autor, Periodico periodico, int edicao, int volume) {
        super(titulo, data_publicacao, acesso_livre, doi, id_autor);
        this.periodico = periodico;
        this.edicao = edicao;
        this.volume = volume;
    }

    public ArtigoDePeriodico(Long id, String titulo, Date data_publicacao, boolean acesso_livre, int doi, Long id_autor, Periodico periodico, int edicao, int volume) {
        super(titulo, data_publicacao, acesso_livre, doi, id_autor);
        this.id = id;
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
}
