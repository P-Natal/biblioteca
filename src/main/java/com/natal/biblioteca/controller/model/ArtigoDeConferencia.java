package com.natal.biblioteca.controller.model;

import com.natal.biblioteca.infrastructure.entities.auxiliar.TipoArtigo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArtigoDeConferencia {

    private Long Id;

    private Conferencia conferenciaEntity;

    private TipoArtigo tipo;

    public ArtigoDeConferencia() {
    }

    public ArtigoDeConferencia(Long id, Conferencia conferenciaEntity, TipoArtigo tipo) {
        Id = id;
        this.conferenciaEntity = conferenciaEntity;
        this.tipo = tipo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Conferencia getConferenciaEntity() {
        return conferenciaEntity;
    }

    public void setConferenciaEntity(Conferencia conferenciaEntity) {
        this.conferenciaEntity = conferenciaEntity;
    }

    public TipoArtigo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtigo tipo) {
        this.tipo = tipo;
    }
}
