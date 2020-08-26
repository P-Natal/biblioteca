package com.natal.biblioteca.infrastructure.repository;

import com.natal.biblioteca.controller.model.Livro;
import com.natal.biblioteca.infrastructure.entities.LivroEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class LivroRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public LivroRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecadb");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<LivroEntity> buscaTodos() {
        return this.entityManager.createNamedQuery("buscaTodosLivro").getResultList();
    }


    public void salvar(LivroEntity livroEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(livroEntity);
        this.entityManager.getTransaction().commit();
    }

    public void alterar(LivroEntity livroEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(livroEntity);
        this.entityManager.getTransaction().commit();
    }

    public LivroEntity getLivro(Long id){
        return this.entityManager.find(LivroEntity.class, id);
    }

    public void excluir(Long id){
        LivroEntity livroEntity = this.getLivro(id);

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(livroEntity);
        this.entityManager.getTransaction().commit();
    }

    public List<LivroEntity> getLivroPorAutor(Long autor_id) {
        Query query = entityManager.createNamedQuery("buscarLivroPorAutor");
        query.setParameter("autor_id", autor_id);
        return query.getResultList();
    }
}
