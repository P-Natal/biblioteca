package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name = "buscaTodosConferencia", query = "select c from ConferenciaEntity c")
public class ConferenciaEntity {

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

    public ConferenciaEntity(String nome, String acronimo, int edicao, String cidade, String pais, Date data_inicio, Date data_fim) {
        this.nome = nome;
        this.acronimo = acronimo;
        this.edicao = edicao;
        this.cidade = cidade;
        this.pais = pais;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
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

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }
}
