package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Conferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "acronimo")
    private String acronimo;

    @Column(name = "edicao")
    private int edicao;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "pais")
    private String pais;

    @Column(name = "data_inicio")
    private Date data_inicio;

    @Column(name = "data_fim")
    private Date data_fim;

}
