package com.natal.biblioteca.infrastructure.repository;

import com.natal.biblioteca.infrastructure.entities.ConferenciaEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ConferenciaRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ConferenciaRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecadb");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<ConferenciaEntity> buscaTodos() {
        return this.entityManager.createNamedQuery("buscaTodos").getResultList();
    }


    public void salvar(ConferenciaEntity conferenciaEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(conferenciaEntity);
        this.entityManager.getTransaction().commit();
    }

    public void alterar(ConferenciaEntity conferenciaEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(conferenciaEntity);
        this.entityManager.getTransaction().commit();
    }

    public ConferenciaEntity getConferencia(Long id){
        return this.entityManager.find(ConferenciaEntity.class, id);
    }

    public void excluir(Long id){
        ConferenciaEntity conferenciaEntity = this.getConferencia(id);

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(conferenciaEntity);
        this.entityManager.getTransaction().commit();
    }
}
