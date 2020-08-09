<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>Gerência de Autores</title>
    </head>

    <body>
        <%@ page import="com.natal.biblioteca.infrastructure.entities.Autor" %>


        <fieldset> <legend>Cadastro de autores</legend>
            <form action="autor" method="post">
                <label>Primeiro Nome</label> <input type="text" name="primNome"/> <br>
                <label>Nome do meio</label> <input type="text" name="nomeMeio"/> <br>
                <label>Último nome</label> <input type="text" name="ultNome"/> <br>
                <label>Afiliação</label> <input type="text" name="afiliacao"/> <br>
                <label>email</label> <input type="email" name="email"/> <br>
                <label>País</label> <input type="text" name="pais"/> <br>
                <br>
                <button>Cadastrar</button>
            </form>
        </fieldset>

        <br>

        <fieldset> <legend>Remoção de autores</legend>
            <form action="autor" method="delete">
                <label>ID do Autor</label> <input type="number" />
                <button>Remover</button>
            </form>
        </fieldset>

        <fieldset> <legend>Autores cadastrados</legend>
        <%
            for(Autor autor : ${autoresCadastrados}){
        %>
             <form>
                 <label>ID do Autor</label> <%autor.getId()%>
                 <label>Nome do Autor</label> <%autor.getPrimeiroNome()+autor.getNomeDoMeio()+autor.getUltimoNome()%>
             </form>
        <%
            }
        %>
        </fieldset>

        <br>

    </body>
</html>