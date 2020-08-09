package com.natal.biblioteca.controller.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Capitulo {

    private Long id;

    private Livro livroEntity;

    public Capitulo(Long id, Livro livroEntity) {
        this.id = id;
        this.livroEntity = livroEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivroEntity() {
        return livroEntity;
    }

    public void setLivroEntity(Livro livroEntity) {
        this.livroEntity = livroEntity;
    }
}
