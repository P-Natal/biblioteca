package com.natal.biblioteca;

import com.natal.biblioteca.infrastructure.entities.AutorEntity;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;


public class Teste {

    public static void main(String[] args) throws SQLException {
        Logger log = Logger.getLogger(Teste.class.getName());

        EntityManagerFactory emf = null;
        try {
            emf = Persistence.createEntityManagerFactory("bibliotecadb");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        log.info("Criando um Autor...");

        AutorEntity autorEntity = new AutorEntity(
                "João",
                "da Silva",
                "Júnior",
                "afiliacao_teste",
                "teste@teste.com",
                "Braza"
        );

        AutorEntity autorEntity2 = new AutorEntity(
                "João",
                "da Silva",
                "Júnior",
                "afiliacao_teste",
                "teste@teste.com",
                "Braza"
        );

        log.info("Salvando Autor 1");

        et.begin();

        em.persist(autorEntity);
        log.info("Salvando Autor 2");
        em.persist(autorEntity2);
        log.info("Verificando se foi persistido...");
        et.commit();

        Query query = em.createNamedQuery("buscaTodos");
        List<AutorEntity> autores = query.getResultList();

        for (AutorEntity aut : autores){
            log.info("Autor: " + aut.toString());
        }
    }
}
