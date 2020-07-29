package com.natal.biblioteca.servlets;

import com.natal.biblioteca.infrastructure.entities.Autor;

import javax.persistence.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "autor-service")
public class AutorService extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.setContentType("text/html");
            PrintWriter output = response.getWriter();
            EntityManagerFactory emf = null;

            try {
                emf = Persistence.createEntityManagerFactory("bibliotecadb");
            }
            catch (Exception e){
                output.println("<h2>"+e.getCause().getLocalizedMessage()+"</h2>");
            }
            EntityManager em = emf.createEntityManager();
            EntityTransaction et = em.getTransaction();

            Query queryTeste = em.createNamedQuery("buscaTodos");
            List<Autor> autoresTeste = queryTeste.getResultList();
            output.println("<h2>"+autoresTeste.size()+"</h2>");

            for (Autor aut : autoresTeste){
                output.println("<h2>" + aut.toString() + "</h2>");
            }

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

            Query query = em.createNamedQuery("buscaTodos");
            List<Autor> autores = query.getResultList();

            output.println("<fieldset> <legend>Lista de autores cadastrados</legend>");

            for (Autor aut : autores){
                    output.println("<h2>" + aut.toString() + "</h2>");
            }

        } catch(Exception e) {
            log("Erro  T_T :" , e);
        }

    }

    private void buscarAutorPorNome(HttpServletRequest request, HttpServletResponse response) {

    }

    private void buscarAutorPorAfiliacao(HttpServletRequest request, HttpServletResponse response) {

    }


}
