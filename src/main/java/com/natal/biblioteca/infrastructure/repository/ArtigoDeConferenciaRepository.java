package com.natal.biblioteca.infrastructure.repository;

import com.natal.biblioteca.controller.model.ArtigoDeConferencia;
import com.natal.biblioteca.infrastructure.entities.ArtigoDeConferenciaEntity;
import com.natal.biblioteca.infrastructure.entities.ArtigoDePeriodicoEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ArtigoDeConferenciaRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ArtigoDeConferenciaRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecadb");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<ArtigoDeConferenciaEntity> buscaTodos() {
        return this.entityManager.createNamedQuery("buscaTodosArtigoDeConferencia").getResultList();
    }


    public void salvar(ArtigoDeConferenciaEntity artigoDeConferenciaEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(artigoDeConferenciaEntity);
        this.entityManager.getTransaction().commit();
    }

    public void alterar(ArtigoDeConferenciaEntity artigoDeConferenciaEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(artigoDeConferenciaEntity);
        this.entityManager.getTransaction().commit();
    }

    public ArtigoDeConferenciaEntity getArtigo(Long id){
        return this.entityManager.find(ArtigoDeConferenciaEntity.class, id);
    }

    public void excluir(Long id){
        ArtigoDeConferenciaEntity artigo = this.getArtigo(id);

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(artigo);
        this.entityManager.getTransaction().commit();
    }

    public List<ArtigoDeConferenciaEntity> getArtigoPorAutor(String primNome) {
        Query query = entityManager.createNamedQuery("buscarArtConfPorAutor");
        query.setParameter("primNome", primNome);
        return query.getResultList();
    }
}
