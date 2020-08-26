package com.natal.biblioteca.infrastructure.entities;

import com.natal.biblioteca.infrastructure.entities.auxiliar.TipoArtigo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "artigo_de_conferencia")
@NamedQueries({
        @NamedQuery(name = "buscaTodosArtigoDeConferencia", query = "select art from ArtigoDeConferenciaEntity art"),
        @NamedQuery(name = "buscarArtConfPorAutor", query = "select art from ArtigoDeConferenciaEntity art join art.autor a where a.id = :autor_id")
})
public class ArtigoDeConferenciaEntity extends PublicacaoEntity {

    @ManyToOne(targetEntity = ConferenciaEntity.class)
    private ConferenciaEntity conferencia;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoArtigo tipo;

    public ArtigoDeConferenciaEntity() {
    }

    public ArtigoDeConferenciaEntity(String titulo, Date data_publicacao, boolean acesso_livre, int doi, AutorEntity autorEntity, ConferenciaEntity conferenciaEntity, TipoArtigo tipo) {
        super(titulo, data_publicacao, acesso_livre, doi, autorEntity);
        this.conferencia = conferenciaEntity;
        this.tipo = tipo;
    }

    public ArtigoDeConferenciaEntity(Long id, String titulo, Date data_publicacao, boolean acesso_livre, int doi, AutorEntity autorEntity, ConferenciaEntity conferenciaEntity, TipoArtigo tipo) {
        super(titulo, data_publicacao, acesso_livre, doi, autorEntity);
        this.setId(id);
        this.conferencia = conferenciaEntity;
        this.tipo = tipo;
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

    @Override
    public String toString() {
        return "ArtigoDeConferenciaEntity{" +
                "conferencia=" + conferencia +
                ", tipo=" + tipo +
                '}';
    }
}
