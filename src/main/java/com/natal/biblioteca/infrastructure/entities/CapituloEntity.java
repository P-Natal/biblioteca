package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "buscaTodos", query = "select cap from CapituloEntity cap")
public class CapituloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(targetEntity = LivroEntity.class)
    private LivroEntity livroEntity;

    public CapituloEntity(LivroEntity livroEntity) {
        this.livroEntity = livroEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LivroEntity getLivroEntity() {
        return livroEntity;
    }

    public void setLivroEntity(LivroEntity livroEntity) {
        this.livroEntity = livroEntity;
    }
}
