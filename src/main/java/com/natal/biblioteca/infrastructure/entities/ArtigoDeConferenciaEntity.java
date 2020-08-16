package com.natal.biblioteca.infrastructure.entities;

import com.natal.biblioteca.infrastructure.entities.auxiliar.TipoArtigo;

import javax.persistence.*;

@Entity
@NamedQuery(name = "buscaTodosArtigoDeConferencia", query = "select art from ArtigoDeConferenciaEntity art")
public class ArtigoDeConferenciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @ManyToOne(targetEntity = ConferenciaEntity.class)
    private ConferenciaEntity conferencia;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoArtigo tipo;

    public ArtigoDeConferenciaEntity(Long id, ConferenciaEntity conferenciaEntity, TipoArtigo tipo) {
        Id = id;
        this.conferencia = conferenciaEntity;
        this.tipo = tipo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public ConferenciaEntity getConferencia() {
        return conferencia;
    }

    public void setConferencia(ConferenciaEntity conferencia) {
        this.conferencia = conferencia;
    }

    public TipoArtigo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtigo tipo) {
        this.tipo = tipo;
    }
}
