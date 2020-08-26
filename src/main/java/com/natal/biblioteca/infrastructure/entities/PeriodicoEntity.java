package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name = "buscaTodosPeriodico", query = "select p from PeriodicoEntity p")
public class PeriodicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(targetEntity = EditoraEntity.class)
    private EditoraEntity editora;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "acronimo")
    private String acronimo;

    @Column(name = "issn")
    private int issn;

    public PeriodicoEntity(EditoraEntity editoraEntity, String titulo, String acronimo, int issn) {
        this.editora = editoraEntity;
        this.titulo = titulo;
        this.acronimo = acronimo;
        this.issn = issn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EditoraEntity getEditora() {
        return editora;
    }

    public void setEditora(EditoraEntity editora) {
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public int getIssn() {
        return issn;
    }

    public void setIssn(int issn) {
        this.issn = issn;
    }
}
