package com.natal.biblioteca.controller.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Livro extends Publicacao {

    private Long id;

    private Editora editora;

    private int isbn;

    public Livro(Long id, Editora editoraEntity, int isbn) {
        this.id = id;
        this.editora = editoraEntity;
        this.isbn = isbn;
    }

    public Livro(String titulo, Date data_publicacao, boolean acesso_livre, int doi, Long id_autor, Editora editoraEntity, int isbn) {
        super(titulo, data_publicacao, acesso_livre, doi, id_autor);
        this.editora = editoraEntity;
        this.isbn = isbn;
    }

    public Livro(Long id, String titulo, Date data_publicacao, boolean acesso_livre, int doi, Long id_autor, Editora editoraEntity, int isbn) {
        super(titulo, data_publicacao, acesso_livre, doi, id_autor);
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
