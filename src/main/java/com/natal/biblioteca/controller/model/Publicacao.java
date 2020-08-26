package com.natal.biblioteca.controller.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Publicacao {

    private Long id;

    private String titulo;

    private Date data_publicacao;

    private boolean acesso_livre;

    private int doi;

    private Long id_autor;

    public Publicacao() {}

    public Publicacao(Long id, String titulo, Date data_publicacao, boolean acesso_livre, int doi, Long id_autor) {
        this.id = id;
        this.titulo = titulo;
        this.data_publicacao = data_publicacao;
        this.acesso_livre = acesso_livre;
        this.doi = doi;
        this.id_autor = id_autor;
    }

    public Publicacao(String titulo, Date data_publicacao, boolean acesso_livre, int doi, Long id_autor) {
        this.titulo = titulo;
        this.data_publicacao = data_publicacao;
        this.acesso_livre = acesso_livre;
        this.doi = doi;
        this.id_autor = id_autor;
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

    public Date getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(Date data_publicacao) {
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

    public Long getId_autor() {
        return id_autor;
    }

    public void setId_autor(Long id_autor) {
        this.id_autor = id_autor;
    }
}
