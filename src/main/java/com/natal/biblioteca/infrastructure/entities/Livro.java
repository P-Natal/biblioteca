package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Editora editora;

    @Column(name = "isbn")
    private int isbn;

    @OneToMany(mappedBy = "livro")
    private List<Capitulo> capitulos;

}
