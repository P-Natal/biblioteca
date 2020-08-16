package com.natal.biblioteca.infrastructure.repository;

import com.natal.biblioteca.infrastructure.entities.PeriodicoEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PeriodicoRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public PeriodicoRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecadb");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<PeriodicoEntity> buscaTodos() {
        return this.entityManager.createNamedQuery("buscaTodosPeriodico").getResultList();
    }


    public void salvar(PeriodicoEntity periodicoEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(periodicoEntity);
        this.entityManager.getTransaction().commit();
    }

    public void alterar(PeriodicoEntity periodicoEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(periodicoEntity);
        this.entityManager.getTransaction().commit();
    }

    public PeriodicoEntity getPeriodico(Long id){
        return this.entityManager.find(PeriodicoEntity.class, id);
    }

    public void excluir(Long id){
        PeriodicoEntity periodicoEntity = this.getPeriodico(id);

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(periodicoEntity);
        this.entityManager.getTransaction().commit();
    }
}
