package com.natal.biblioteca.infrastructure.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name = "buscaTodos", query = "select a from Autor a")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "primeiro_nome")
    private String primeiro_nome;

    @Column(name = "nome_do_meio")
    private String nome_do_meio;

    @Column(name = "ultimo_nome")
    private String ultimo_nome;

    @Column(name = "afiliacao")
    private String afiliacao;

    @Column(name = "email")
    private String email;

    @Column(name = "pais")
    private String pais;

    @OneToMany(mappedBy = "autor")
    private List<PublicacaoAutor> publicacaoAutor;

    public Autor() {
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

    public String getPrimeiro_nome() {
        return primeiro_nome;
    }

    public String getNome_do_meio() {
        return nome_do_meio;
    }

    public String getUltimo_nome() {
        return ultimo_nome;
    }

    public String getAfiliacao() {
        return afiliacao;
    }

    public String getEmail() {
        return email;
    }

    public String getPais() {
        return pais;
    }

    public List<PublicacaoAutor> getPublicacaoAutor() {
        return publicacaoAutor;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", primeiro_nome='" + primeiro_nome + '\'' +
                ", nome_do_meio='" + nome_do_meio + '\'' +
                ", ultimo_nome='" + ultimo_nome + '\'' +
                ", afiliacao='" + afiliacao + '\'' +
                ", email='" + email + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }
}
