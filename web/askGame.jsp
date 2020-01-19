<%--
  Created by IntelliJ IDEA.
  User: rayha
  Date: 12/12/2019
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<body>
<%
    if(currentUser != null) { %>
<div class="test">
    <form id="formJeux" class="formJeux" action="askGameServlet" method="post">
        <label for="thème">Thème : </label><br/>
        <select name="choixTheme" id="thème">
            <option value="Jeu de role">Jeu de rôle</option>
            <option value="Jeu ambiance">Jeu d'ambiance</option>
            <option value="Jeu de strategie">Jeu de stratégie</option>
        </select>
        <br/><br/>
        <label for="titre">Titre du jeu : </label><br/>
        <input type="text" id="titre" name="titre">
        <br/><br/>
        <label for="duree">Durée : </label><br/>
        <input type="number" id="duree" name="duree" min="1">
        <br/><br/>
        <label for="nbmin">Joueurs min. (2) : </label><br/>
        <input type="number" id="nbmin" min="2" value="2" name="nbmin">
        <br/><br/>
        <label for="nbmax">Joueurs max. (10) : </label><br/>
        <input type="number" id="nbmax" max = "10" name="nbmax">
        <br/><br/>
        <label for="description">Description : </label><br/>
        <textarea id="description" form="formJeux" rows="5" cols="30" name="description">Entrez une description brève du jeu</textarea>
        <br/><br/>
        <input type="submit" class='button' value="Demander le jeu">
    </form>
    <% if((request.getAttribute("message_jeu")) != null)
    {
        String error_message = (String) request.getAttribute("message_jeu");
        out.println("<p class='errorMsg'> " + error_message + "</p>");
    }

    %>
</div>
<% } %>


</body>
</html>
