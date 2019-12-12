<%--
  Created by IntelliJ IDEA.
  User: rayha
  Date: 12/12/2019
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>

<html>
<head>
    <title>Ajout de Jeux</title>
    <link rel="stylesheet" href="css/addJeux.css">
</head>
<body>
<%
    if(currentUser != null) { %>
    <div class="test">
        <form id="formJeux" class="formJeux">
            <label for="titre">Titre du jeux : </label></br>
            <input type="text" id="titre">
            </br></br>
            <label for="thème">Thème : </label></br>
            <input type="text" id="thème">
            </br></br>
            <label for="duree">Durée : </label></br>
            <input type="number" id="duree">
            </br></br>
            <label for="nbmin">Joueurs min (2) : </label></br>
            <input type="number" id="nbmin" min="2" value="2">
            </br></br>
            <label for="nbmax">Joueurs max (10) : </label></br>
            <input type="number" id="nbmax" max = "10">
            </br></br>
            <label for="description">Description : </label></br>
            <textarea name="comment" id="description" form="formJeux" rows="8" cols="60">Enter text here...</textarea>
            </br></br>
            <input type="submit" value="Submit">
        </form>
    </div>
    <% }
    else { %>
        <p align="center"> Vous devez vous connectez ! </p>
    <% } %>


</body>
</html>
