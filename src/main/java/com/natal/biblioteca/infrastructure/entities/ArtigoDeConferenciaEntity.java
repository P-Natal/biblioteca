package com.natal.biblioteca.infrastructure.entities;

import com.natal.biblioteca.infrastructure.entities.auxiliar.TipoArtigo;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name = "buscaTodosArtigoDeConferencia", query = "select art from ArtigoDeConferenciaEntity art")
public class ArtigoDeConferenciaEntity extends PublicacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @ManyToOne(targetEntity = ConferenciaEntity.class)
    private ConferenciaEntity conferencia;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoArtigo tipo;

    public ArtigoDeConferenciaEntity(String titulo, Date data_publicacao, boolean acesso_livre, int doi, AutorEntity autorEntity, ConferenciaEntity conferenciaEntity, TipoArtigo tipo) {
        super(titulo, data_publicacao, acesso_livre, doi, autorEntity);
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
