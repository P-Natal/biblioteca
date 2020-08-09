package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "buscaTodos", query = "select pa from PublicacaoAutorEntity pa")
public class PublicacaoAutorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private AutorEntity autorEntity;

    @ManyToOne
    private PublicacaoEntity publicacaoEntity;

    public PublicacaoAutorEntity(AutorEntity autorEntity, PublicacaoEntity publicacaoEntity) {
        this.autorEntity = autorEntity;
        this.publicacaoEntity = publicacaoEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AutorEntity getAutorEntity() {
        return autorEntity;
    }

    public void setAutorEntity(AutorEntity autorEntity) {
        this.autorEntity = autorEntity;
    }

    public PublicacaoEntity getPublicacaoEntity() {
        return publicacaoEntity;
    }

    public void setPublicacaoEntity(PublicacaoEntity publicacaoEntity) {
        this.publicacaoEntity = publicacaoEntity;
    }
}
