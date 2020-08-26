package com.natal.biblioteca.controller.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Livro extends Publicacao{

    private Long id;

    private Editora editora;

    private int isbn;

    public Livro() {
    }

    public Livro(Long id, String titulo, String data_publicacao, boolean acesso_livre, int doi, Autor autor, Editora editora, int isbn) {
        super(titulo, data_publicacao, acesso_livre, doi, autor);
        this.id = id;
        this.editora = editora;
        this.isbn = isbn;
    }

    public Livro(String titulo, String data_publicacao, boolean acesso_livre, int doi, Autor autor, Editora editora, int isbn) {
        super(titulo, data_publicacao, acesso_livre, doi, autor);
        this.editora = editora;
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
