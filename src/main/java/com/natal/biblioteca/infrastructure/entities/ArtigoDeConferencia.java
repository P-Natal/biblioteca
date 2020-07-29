package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
public class ArtigoDeConferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @ManyToOne(targetEntity = Conferencia.class)
    private Conferencia conferencia;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoArtigo tipo;

}
