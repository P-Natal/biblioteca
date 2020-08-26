package com.natal.biblioteca.infrastructure.repository;

import com.natal.biblioteca.infrastructure.entities.AutorEntity;
import com.natal.biblioteca.infrastructure.entities.CapituloEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class CapituloRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public CapituloRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecadb");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<CapituloEntity> buscaTodos() {
        return this.entityManager.createNamedQuery("buscaTodosCapitulo").getResultList();
    }


    public void salvar(CapituloEntity capituloEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(capituloEntity);
        this.entityManager.getTransaction().commit();
    }

    public void alterar(CapituloEntity capituloEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(capituloEntity);
        this.entityManager.getTransaction().commit();
    }

    public CapituloEntity getCapitulo(Long id){
        return this.entityManager.find(CapituloEntity.class, id);
    }

    public void excluir(Long id){
        CapituloEntity capituloEntity = this.getCapitulo(id);

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(capituloEntity);
        this.entityManager.getTransaction().commit();
    }

    public List<CapituloEntity> buscarPorAutor(AutorEntity autorEntity) {

        return new ArrayList<CapituloEntity>();
    }
}
