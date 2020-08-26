package com.natal.biblioteca.controller.model;

import com.natal.biblioteca.infrastructure.entities.auxiliar.TipoArtigo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class ArtigoDeConferencia extends Publicacao{

    private Long id;

    private Conferencia conferenciaEntity;

    private TipoArtigo tipo;

    public ArtigoDeConferencia() {
    }

    public ArtigoDeConferencia(String titulo, Date data_publicacao, boolean acesso_livre, int doi, Long id_autor, Conferencia conferenciaEntity, TipoArtigo tipo) {
        super(titulo, data_publicacao, acesso_livre, doi, id_autor);
        this.conferenciaEntity = conferenciaEntity;
        this.tipo = tipo;
    }

    public ArtigoDeConferencia(Long id, String titulo, Date data_publicacao, boolean acesso_livre, int doi, Long id_autor, Conferencia conferenciaEntity, TipoArtigo tipo) {
        super(titulo, data_publicacao, acesso_livre, doi, id_autor);
        this.id = id;
        this.conferenciaEntity = conferenciaEntity;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
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
