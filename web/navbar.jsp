<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 03/12/2019
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%  //User currentUser = ((User)(session.getAttribute("user")));
        User currentUser = (User)request.getServletContext().getAttribute("currentUser");
    %>
    <title>ProjetJEE</title>
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/addJeux.css">
    <link rel="stylesheet" href="css/profile.css">
    <link rel="stylesheet" href="css/addSeance.css">
    <link rel="stylesheet" href="css/pageSeances.css">
    <link rel="stylesheet" href="css/detailsSeance.css">
    <link rel="stylesheet" href="css/inscriptionSeance.css">

    <nav class="nav">
        <%
            if(currentUser == null){
                out.println("<div class='errorLog'> <h1>Vous n'êtes pas connecté</h1>");
                out.println("<a href ='login.jsp' class='button'> Connectez-vous </a> </div>");
            }
            else {
            %>
            <div class="links">
                <ul class="menu">
                    <li><a href="profil.jsp">Profil</a></li>
                    <li><a href="seance.jsp">Séances</a></li>
                    <li><a href="messagerie">Messagerie</a></li>
                    <% if(currentUser.getRole() == User.Role.ADMIN) { %>
                    <li class="gestion"> <a href='#'>Gestion Adhérents</a> </li>
                    <% } %>
                    <li><a href="logout">Déconnexion</a></li>
                </ul>
            </div>
            <%
                }
            %>
    </nav>
</head>
