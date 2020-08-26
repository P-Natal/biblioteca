package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
@Table(name = "capitulo")
@NamedQuery(name = "buscaTodosCapitulos", query = "select cap from CapituloEntity cap")
public class CapituloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(targetEntity = LivroEntity.class)
    private LivroEntity livro;

    public CapituloEntity() {
    }

    public CapituloEntity(LivroEntity livroEntity) {
        this.livro = livroEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LivroEntity getLivro() {
        return livro;
    }

    public void setLivro(LivroEntity livro) {
        this.livro = livro;
    }
}
