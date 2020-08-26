package com.natal.biblioteca.controller.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Publicacao {

    private Long id;

    private String titulo;

    private String data_publicacao;

    private boolean acesso_livre;

    private int doi;

    private Autor autor;

    public Publicacao() {
    }

    public Publicacao(Long id, String titulo, String data_publicacao, boolean acesso_livre, int doi, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.data_publicacao = data_publicacao;
        this.acesso_livre = acesso_livre;
        this.doi = doi;
        this.autor = autor;
    }

    public Publicacao(String titulo, String data_publicacao, boolean acesso_livre, int doi, Autor autor) {
        this.titulo = titulo;
        this.data_publicacao = data_publicacao;
        this.acesso_livre = acesso_livre;
        this.doi = doi;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(String data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public boolean isAcesso_livre() {
        return acesso_livre;
    }

    public void setAcesso_livre(boolean acesso_livre) {
        this.acesso_livre = acesso_livre;
    }

    public int getDoi() {
        return doi;
    }

    public void setDoi(int doi) {
        this.doi = doi;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
