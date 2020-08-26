package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "buscaTodosPublicacaoAutor", query = "select pa from PublicacaoAutorEntity pa")
public class PublicacaoAutorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private AutorEntity autor;

    @ManyToOne
    private PublicacaoEntity publicacao;

    public PublicacaoAutorEntity() {
    }

    public PublicacaoAutorEntity(AutorEntity autorEntity, PublicacaoEntity publicacaoEntity) {
        this.autor = autorEntity;
        this.publicacao = publicacaoEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AutorEntity getAutor() {
        return autor;
    }

    public void setAutor(AutorEntity autor) {
        this.autor = autor;
    }

    public PublicacaoEntity getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(PublicacaoEntity publicacao) {
        this.publicacao = publicacao;
    }
}
