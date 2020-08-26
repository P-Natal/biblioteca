package com.natal.biblioteca.controller.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Conferencia {

    private Long id;

    private String nome;

    private String acronimo;

    private int edicao;

    private String cidade;

    private String pais;

    private String data_inicio;

    private String data_fim;

    public Conferencia() {
    }

    public Conferencia(Long id, String nome, String acronimo, int edicao, String cidade, String pais, String data_inicio, String data_fim) {
        this.id = id;
        this.nome = nome;
        this.acronimo = acronimo;
        this.edicao = edicao;
        this.cidade = cidade;
        this.pais = pais;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

    public Conferencia(String nome, String acronimo, int edicao, String cidade, String pais, String data_inicio, String data_fim) {
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

    public String getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(String data_inicio) {
        this.data_inicio = data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }
}
