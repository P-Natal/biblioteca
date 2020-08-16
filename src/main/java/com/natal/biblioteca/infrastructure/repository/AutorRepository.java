package com.natal.biblioteca.infrastructure.repository;

import com.natal.biblioteca.infrastructure.entities.AutorEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AutorRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public AutorRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecadb");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<AutorEntity> buscaTodos() {
        return this.entityManager.createNamedQuery("buscaTodosAutor").getResultList();
    }


    public void salvar(AutorEntity autorEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(autorEntity);
        this.entityManager.getTransaction().commit();
    }

    public void alterar(AutorEntity autorEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(autorEntity);
        this.entityManager.getTransaction().commit();
    }

    public AutorEntity getAutor(Long id){
        return this.entityManager.find(AutorEntity.class, id);
    }

    public void excluir(Long id){
        AutorEntity autorEntity = this.getAutor(id);

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(autorEntity);
        this.entityManager.getTransaction().commit();
    }
}
