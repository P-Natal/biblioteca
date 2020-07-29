package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "pais")
    private String pais;
}
