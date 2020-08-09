package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "buscaTodos", query = "select l from LivroEntity l")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private EditoraEntity editoraEntity;

    @Column(name = "isbn")
    private int isbn;

    @OneToMany(mappedBy = "livro")
    private List<CapituloEntity> capituloEntities;

    public LivroEntity(EditoraEntity editoraEntity, int isbn, List<CapituloEntity> capituloEntities) {
        this.editoraEntity = editoraEntity;
        this.isbn = isbn;
        this.capituloEntities = capituloEntities;
    }

    public LivroEntity(EditoraEntity editoraEntity, int isbn) {
        this.editoraEntity = editoraEntity;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EditoraEntity getEditoraEntity() {
        return editoraEntity;
    }

    public void setEditoraEntity(EditoraEntity editoraEntity) {
        this.editoraEntity = editoraEntity;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public List<CapituloEntity> getCapituloEntities() {
        return capituloEntities;
    }

    public void setCapituloEntities(List<CapituloEntity> capituloEntities) {
        this.capituloEntities = capituloEntities;
    }
}
