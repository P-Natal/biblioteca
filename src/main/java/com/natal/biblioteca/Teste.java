package com.natal.biblioteca;

import com.natal.biblioteca.infrastructure.entities.Autor;

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

        Autor autor = new Autor(
                "João",
                "da Silva",
                "Júnior",
                "afiliacao_teste",
                "teste@teste.com",
                "Braza"
        );

        Autor autor2 = new Autor(
                "João",
                "da Silva",
                "Júnior",
                "afiliacao_teste",
                "teste@teste.com",
                "Braza"
        );

        log.info("Salvando Autor 1");

        et.begin();

        em.persist(autor);
        log.info("Salvando Autor 2");
        em.persist(autor2);
        log.info("Verificando se foi persistido...");
        et.commit();

        Query query = em.createNamedQuery("buscaTodos");
        List<Autor> autores = query.getResultList();

        for (Autor aut : autores){
            log.info("Autor: " + aut.toString());
        }
    }
}
