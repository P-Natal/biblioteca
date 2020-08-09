package com.natal.biblioteca.controller.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Livro {

    private Long id;

    private Editora editora;

    private int isbn;

    public Livro(Long id, Editora editoraEntity, int isbn) {
        this.id = id;
        this.editora = editoraEntity;
        this.isbn = isbn;
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

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

}
