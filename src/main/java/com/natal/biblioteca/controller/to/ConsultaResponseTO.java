package com.natal.biblioteca.controller.to;

public class ConsultaResponseTO {

    private Long id;

    private String descricao;

    private String tipo_publicacao;

    public ConsultaResponseTO(Long id, String descricao, String tipo_publicacao) {
        this.id = id;
        this.descricao = descricao;
        this.tipo_publicacao = tipo_publicacao;
    }

    public ConsultaResponseTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo_publicacao() {
        return tipo_publicacao;
    }

    public void setTipo_publicacao(String tipo_publicacao) {
        this.tipo_publicacao = tipo_publicacao;
    }
}
