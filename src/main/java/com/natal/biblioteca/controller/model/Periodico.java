package com.natal.biblioteca.controller.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Periodico {

    private Long id;

    private Editora editora;

    private String titulo;

    private String acronimo;

    private int issn;

    public Periodico() {}

    public Periodico(Long id, Editora editora, String titulo, String acronimo, int issn) {
        this.id = id;
        this.editora = editora;
        this.titulo = titulo;
        this.acronimo = acronimo;
        this.issn = issn;
    }

    public Periodico(Editora editora, String titulo, String acronimo, int issn) {
        this.editora = editora;
        this.titulo = titulo;
        this.acronimo = acronimo;
        this.issn = issn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public int getIssn() {
        return issn;
    }

    public void setIssn(int issn) {
        this.issn = issn;
    }

    @Override
    public String toString() {
        return "Periodico{" +
                "id=" + id +
                ", editora=" + editora +
                ", titulo='" + titulo + '\'' +
                ", acronimo='" + acronimo + '\'' +
                ", issn=" + issn +
                '}';
    }
}
