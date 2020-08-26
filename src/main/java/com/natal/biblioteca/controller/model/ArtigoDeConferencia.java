package com.natal.biblioteca.controller.model;

import com.natal.biblioteca.infrastructure.entities.auxiliar.TipoArtigo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArtigoDeConferencia extends Publicacao{

    private Conferencia conferencia;

    private TipoArtigo tipo;

    public ArtigoDeConferencia() {
    }

    public ArtigoDeConferencia(Long id, Conferencia conferencia, TipoArtigo tipo) {
        this.setId(id);
        this.conferencia = conferencia;
        this.tipo = tipo;
    }

    public ArtigoDeConferencia(String titulo, String data_publicacao, boolean acesso_livre, int doi, Autor autor, Conferencia conferencia, TipoArtigo tipo) {
        super(titulo, data_publicacao, acesso_livre, doi, autor);
        this.conferencia = conferencia;
        this.tipo = tipo;
    }

    public ArtigoDeConferencia(Long id, String titulo, String data_publicacao, boolean acesso_livre, int doi, Autor autor, Conferencia conferencia, TipoArtigo tipo) {
        super(titulo, data_publicacao, acesso_livre, doi, autor);
        this.setId(id);
        this.conferencia = conferencia;
        this.tipo = tipo;
    }

    public Conferencia getConferencia() {
        return conferencia;
    }

    public void setConferencia(Conferencia conferencia) {
        this.conferencia = conferencia;
    }

    public TipoArtigo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtigo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ArtigoDeConferencia{" +
                "conferencia=" + conferencia +
                ", tipo=" + tipo +
                '}';
    }
}
