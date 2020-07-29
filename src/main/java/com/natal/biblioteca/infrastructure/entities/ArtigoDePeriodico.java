package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
public class ArtigoDePeriodico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Periodico periodico;

    @Column(name = "edicao")
    private int edicao;

    @Column(name = "volume")
    private int volume;

}
