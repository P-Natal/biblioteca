package com.natal.biblioteca.infrastructure.repository;

import com.natal.biblioteca.infrastructure.entities.ArtigoDePeriodicoEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ArtigoDePeriodicoRepository {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ArtigoDePeriodicoRepository(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecadb");
        this.entityManager = this.entityManagerFactory.createEntityManager();
    }

    @SuppressWarnings("unchecked")
    public List<ArtigoDePeriodicoEntity> buscaTodos() {
        return this.entityManager.createNamedQuery("buscaTodosArtigoDePeriodico").getResultList();
    }


    public void salvar(ArtigoDePeriodicoEntity artigoDePeriodicoEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(artigoDePeriodicoEntity);
        this.entityManager.getTransaction().commit();
    }

    public void alterar(ArtigoDePeriodicoEntity artigoDePeriodicoEntity){
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(artigoDePeriodicoEntity);
        this.entityManager.getTransaction().commit();
    }

    public ArtigoDePeriodicoEntity getArtigo(Long id){
        return this.entityManager.find(ArtigoDePeriodicoEntity.class, id);
    }

    public void excluir(Long id){
        ArtigoDePeriodicoEntity artigo = this.getArtigo(id);

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(artigo);
        this.entityManager.getTransaction().commit();
    }

    public List<ArtigoDePeriodicoEntity> getArtigoPorAutor(String primNome) {
        Query query = entityManager.createNamedQuery("buscarArtPeriodPorAutor");
        query.setParameter("primNome", primNome);
        return query.getResultList();
    }
}
