package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
public class Capitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(targetEntity = Livro.class)
    private Livro livro;

}
