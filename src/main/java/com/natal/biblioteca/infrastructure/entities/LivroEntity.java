package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "livro")
@NamedQueries({
        @NamedQuery(name = "buscaTodosLivro", query = "select l from LivroEntity l"),
        @NamedQuery(name = "buscarLivroPorAutor", query = "select l from LivroEntity l join l.autor a where a.id = :autor_id")
})
public class LivroEntity extends PublicacaoEntity{

    @ManyToOne
    private EditoraEntity editora;

    @Column(name = "isbn")
    private int isbn;

    @OneToMany(mappedBy = "livro")
    private List<CapituloEntity> capituloEntities;

    public LivroEntity() {
        super();
    }

    public LivroEntity(String titulo, Date data_publicacao, boolean acesso_livre, int doi, AutorEntity autorEntity, EditoraEntity editoraEntity, int isbn, List<CapituloEntity> capituloEntities) {
        super(titulo, data_publicacao, acesso_livre, doi, autorEntity);
        this.editora = editoraEntity;
        this.isbn = isbn;
        this.capituloEntities = capituloEntities;
    }

    public LivroEntity(String titulo, Date data_publicacao, boolean acesso_livre, int doi, AutorEntity autorEntity, EditoraEntity editoraEntity, int isbn) {
        super(titulo, data_publicacao, acesso_livre, doi, autorEntity);
        this.editora = editoraEntity;
        this.isbn = isbn;
    }

    public EditoraEntity getEditora() {
        return editora;
    }

    public void setEditora(EditoraEntity editora) {
        this.editora = editora;
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
