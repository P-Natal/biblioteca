package com.natal.biblioteca.servlets;

import com.natal.biblioteca.infrastructure.entities.AutorEntity;
import com.natal.biblioteca.infrastructure.repository.AutorRepository;

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


    private AutorRepository autorRepository = new AutorRepository();

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            response.setContentType("text/html");
            PrintWriter output = response.getWriter();

            persisteAutor(request);

            List<AutorEntity> autores = buscaTodosAutores();

            output.println("<fieldset> <legend>Lista de autores cadastrados</legend>");

            for (AutorEntity aut : autores){
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

        String primNome = (request.getParameter("primNome"));
        String afiliacao = (request.getParameter("afiliacao"));

        if(primNome != null){
            List<AutorEntity> autores = buscarAutorPorNome(primNome);

            output.println("<fieldset> <legend>Lista de autores cadastrados com primeiro nome = " + primNome + "</legend>");

            for (AutorEntity aut : autores){
                output.println("<h2>" + aut.toString() + "</h2>");
            }
            output.println("</fieldset>");
        }
        if (afiliacao != null){
            List<AutorEntity> autores = buscarAutorPorAfiliacao(afiliacao);

            output.println("<fieldset> <legend>Lista de autores cadastrados com afiliacao = " + afiliacao + "</legend>");
            for (AutorEntity aut : autores){
                output.println("<h2>" + aut.toString() + "</h2>");
            }
            output.println("</fieldset>");
        }
        else {
            output.println("<fieldset>Autores cadastrados (Total)<fieldset>");
            for (AutorEntity autorEntity : buscaTodosAutores()){
                output.println("<h3>" + autorEntity.toString() + "</h3>");
            }
            output.println("</fieldset>");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter output = response.getWriter();
        String id = (request.getParameter("id"));
        try{
            deletaAutor(Long.valueOf(id));
            output.println("<h3>Autor removido com sucesso<h3>");
        }
        catch (Exception e){
            output.println("<h3>Falha ao remover autor da base<h3>");
        }
        output.println("<fieldset>Autores cadastrados<fieldset>");
        for (AutorEntity autorEntity : buscaTodosAutores()){
            output.println("<p>" + autorEntity.toString() + "</p>");
        }
    }

    private List<AutorEntity> buscaTodosAutores(){
        return autorRepository.buscaTodos();
    }

    private List<AutorEntity> buscarAutorPorNome(String primeiroNome) {
        return autorRepository.buscarPorPrimeiroNome(primeiroNome);
    }

    private List<AutorEntity> buscarAutorPorAfiliacao(String afiliacao) {
        return autorRepository.buscarPorAfiliacao(afiliacao);
    }

    private AutorEntity persisteAutor(HttpServletRequest request){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecadb");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        AutorEntity autorEntity = new AutorEntity(
                request.getParameter("primNome"),
                request.getParameter("nomeMeio"),
                request.getParameter("ultNome"),
                request.getParameter("afiliacao"),
                request.getParameter("email"),
                request.getParameter("pais")
        );
        et.begin();
        em.persist(autorEntity);
        et.commit();
        return autorEntity;
    }

    private void deletaAutor(Long id) {
        autorRepository.excluir(id);
    }

    private void showResultInPage(HttpServletRequest request, HttpServletResponse response, List<AutorEntity> autores) throws ServletException, IOException {
        request.setAttribute("autores", autores);
        RequestDispatcher rd = request.getRequestDispatcher("/index.html");
        rd.include(request, response);
    }


}
