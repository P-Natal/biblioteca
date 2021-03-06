package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;

@Entity
@Table(name = "editora")
@NamedQuery(name = "buscaTodosEditora", query = "select e from EditoraEntity e")
public class EditoraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "pais")
    private String pais;

    public EditoraEntity() {
    }

    public EditoraEntity(String nome, String pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public EditoraEntity(Long id, String nome, String pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
