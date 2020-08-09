package com.natal.biblioteca.servlets;

import com.natal.biblioteca.infrastructure.entities.Autor;

import javax.persistence.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "autor-service")
public class AutorService extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.setContentType("text/html");
            PrintWriter output = response.getWriter();

            persisteAutor(request);

            List<Autor> autores = buscaTodosAutores();

            output.println("<fieldset> <legend>Lista de autores cadastrados</legend>");

            for (Autor aut : autores){
                    output.println("<h2>" + aut.toString() + "</h2>");
            }

        } catch(Exception e) {
            log("Erro  T_T :" , e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter output = response.getWriter();
        output.println("<fieldset>Autores cadastrados<fieldset>");
        for (Autor autor : buscaTodosAutores()){
            output.println("<h3>" + autor.toString() + "</h3>");
        }
    }

    private Autor persisteAutor(HttpServletRequest request){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecadb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        Autor autor = new Autor(
                request.getParameter("primNome"),
                request.getParameter("nomeMeio"),
                request.getParameter("ultNome"),
                request.getParameter("afiliacao"),
                request.getParameter("email"),
                request.getParameter("pais")
        );
        et.begin();
        em.persist(autor);
        et.commit();
        return autor;
    }

    private List<Autor> buscaTodosAutores(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecadb");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("buscaTodos");
        return query.getResultList();
    }

    private List<Autor> buscarAutorPorNome(String primeiroNome) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecadb");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("buscaAutorPorNome");
        query.setParameter("primeiroNome", primeiroNome);
        return query.getResultList();
    }

    private List<Autor> buscarAutorPorAfiliacao(String afiliacao) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecadb");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("buscaAutorPorAfiliacao");
        query.setParameter("afiliacao", afiliacao);
        return query.getResultList();
    }

    private void showResultInPage(HttpServletRequest request, HttpServletResponse response, List<Autor> autores) throws ServletException, IOException {
        request.setAttribute("autores", autores);
        RequestDispatcher rd = request.getRequestDispatcher("/index.html");
        rd.include(request, response);
    }


}
