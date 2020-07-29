package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class PublicacaoAutor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Publicacao publicacao;

}
