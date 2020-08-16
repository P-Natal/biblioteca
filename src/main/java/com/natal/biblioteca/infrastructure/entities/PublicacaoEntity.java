package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name = "buscaTodosPublicacao", query = "select p from PublicacaoEntity p")
public class PublicacaoEntity {

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
    private List<PublicacaoAutorEntity> publicacaoAutorEntity;

    public PublicacaoEntity(String titulo, Date data_publicacao, boolean acesso_livre, int doi, List<PublicacaoAutorEntity> publicacaoAutorEntity) {
        this.titulo = titulo;
        this.data_publicacao = data_publicacao;
        this.acesso_livre = acesso_livre;
        this.doi = doi;
        this.publicacaoAutorEntity = publicacaoAutorEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(Date data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public boolean isAcesso_livre() {
        return acesso_livre;
    }

    public void setAcesso_livre(boolean acesso_livre) {
        this.acesso_livre = acesso_livre;
    }

    public int getDoi() {
        return doi;
    }

    public void setDoi(int doi) {
        this.doi = doi;
    }

    public List<PublicacaoAutorEntity> getPublicacaoAutorEntity() {
        return publicacaoAutorEntity;
    }

    public void setPublicacaoAutorEntity(List<PublicacaoAutorEntity> publicacaoAutorEntity) {
        this.publicacaoAutorEntity = publicacaoAutorEntity;
    }
}
