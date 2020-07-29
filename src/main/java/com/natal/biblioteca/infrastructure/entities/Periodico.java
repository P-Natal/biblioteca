package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
public class Periodico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(targetEntity = Editora.class)
    private Editora editora;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "acronimo")
    private String acronimo;

    @Column(name = "issn")
    private int issn;

}
