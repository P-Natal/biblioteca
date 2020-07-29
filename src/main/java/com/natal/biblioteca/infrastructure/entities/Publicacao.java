package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data_publicacao")
    private Date data_publicacao;

    @Column(name = "acesso_livre")
    private boolean acesso_livre;

    @Column(name = "doi")
    private int doi;

    @OneToMany(mappedBy = "publicacao")
    private List<PublicacaoAutor> publicacaoAutor;

}
