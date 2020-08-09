package com.natal.biblioteca.controller.model;

import com.natal.biblioteca.infrastructure.entities.EditoraEntity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Periodico {

    private Long id;

    private Editora editora;

    private String titulo;

    private String acronimo;

    private int issn;

    public Periodico(Long id, Editora editora, String titulo, String acronimo, int issn) {
        this.id = id;
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

    public void setEditoraEntity(Editora editoraEntity) {
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
}
