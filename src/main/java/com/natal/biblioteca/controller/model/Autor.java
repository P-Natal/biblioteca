package com.natal.biblioteca.controller.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Autor {

    private Long id;

    private String primeiro_nome;

    private String nome_do_meio;

    private String ultimo_nome;

    private String afiliacao;

    private String email;

    private String pais;

    private List<Publicacao> publicacoes;

    public Autor() {}

    public Autor(Long id, String primeiro_nome, String nome_do_meio, String ultimo_nome, String afiliacao, String email, String pais) {
        this.id = id;
        this.primeiro_nome = primeiro_nome;
        this.nome_do_meio = nome_do_meio;
        this.ultimo_nome = ultimo_nome;
        this.afiliacao = afiliacao;
        this.email = email;
        this.pais = pais;
    }

    public Autor(String primeiro_nome, String nome_do_meio, String ultimo_nome, String afiliacao, String email, String pais) {
        this.primeiro_nome = primeiro_nome;
        this.nome_do_meio = nome_do_meio;
        this.ultimo_nome = ultimo_nome;
        this.afiliacao = afiliacao;
        this.email = email;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimeiro_nome() {
        return primeiro_nome;
    }

    public void setPrimeiro_nome(String primeiro_nome) {
        this.primeiro_nome = primeiro_nome;
    }

    public String getNome_do_meio() {
        return nome_do_meio;
    }

    public void setNome_do_meio(String nome_do_meio) {
        this.nome_do_meio = nome_do_meio;
    }

    public String getUltimo_nome() {
        return ultimo_nome;
    }

    public void setUltimo_nome(String ultimo_nome) {
        this.ultimo_nome = ultimo_nome;
    }

    public String getAfiliacao() {
        return afiliacao;
    }

    public void setAfiliacao(String afiliacao) {
        this.afiliacao = afiliacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }
}
