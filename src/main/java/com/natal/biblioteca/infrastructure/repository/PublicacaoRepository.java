package com.natal.biblioteca.infrastructure.repository;

import com.natal.biblioteca.infrastructure.entities.PublicacaoEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PublicacaoRepository {

        private final EntityManagerFactory entityManagerFactory;
        private final EntityManager entityManager;

        public PublicacaoRepository(){
            this.entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecadb");
            this.entityManager = this.entityManagerFactory.createEntityManager();
        }

        @SuppressWarnings("unchecked")
        public List<PublicacaoEntity> buscaTodos() {
            return this.entityManager.createNamedQuery("buscaTodosPublicacao").getResultList();
        }


        public void salvar(PublicacaoEntity publicacaoEntity){
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(publicacaoEntity);
            this.entityManager.getTransaction().commit();
        }

        public void alterar(PublicacaoEntity publicacaoEntity){
            this.entityManager.getTransaction().begin();
            this.entityManager.merge(publicacaoEntity);
            this.entityManager.getTransaction().commit();
        }

        public PublicacaoEntity getPublicacao(Long id){
            return this.entityManager.find(PublicacaoEntity.class, id);
        }

        public void excluir(Long id){
            PublicacaoEntity publicacaoEntity = this.getPublicacao(id);
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(publicacaoEntity);
            this.entityManager.getTransaction().commit();
        }
}
