package com.natal.biblioteca.infrastructure.repository;

import com.natal.biblioteca.infrastructure.entities.EditoraEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EditoraRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public EditoraRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecadb");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<EditoraEntity> buscaTodos() {
        return this.entityManager.createNamedQuery("buscaTodosEditora").getResultList();
    }


    public void salvar(EditoraEntity editoraEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(editoraEntity);
        this.entityManager.getTransaction().commit();
    }

    public void alterar(EditoraEntity editoraEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(editoraEntity);
        this.entityManager.getTransaction().commit();
    }

    public EditoraEntity getEditora(Long id){
        return this.entityManager.find(EditoraEntity.class, id);
    }

    public void excluir(Long id){
        EditoraEntity editoraEntity = this.getEditora(id);

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(editoraEntity);
        this.entityManager.getTransaction().commit();
    }
}
