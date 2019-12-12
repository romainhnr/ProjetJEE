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
    <%  User currentUser = ((User)(session.getAttribute("user"))); %>
    <title>ProjetJEE</title>
    <link rel="stylesheet" href="css/navbar.css">
    <nav class="nav">
        <%
            if(currentUser == null){
                out.println("<div class='errorLog'> <h1>Vous n'êtes pas connecté</h1> </div>");
                out.println("<div class='errorLog'> <a href ='login.jsp'> Connectez-vous </a> </div>");
            }
            else {
            %>
            <div class="links">
                <ul class="menu">
                    <li class="profil"><a href="profil.jsp">Profil</a></li>
                    <li class="seance"><a href="seance.jsp">Séances</a></li>
                    <li class="messagerie"><a href="messagerie">Messagerie</a></li>
                    <% if(currentUser.getRole() == User.Role.ADMIN) { %>
                    <li class="gestion"> <a href='#'>Gestion Adherents</a> </li>
                    <% } %>
                    <li class="logout"><a href="logout">Déconnexion</a></li>
                </ul>
            </div>
            <%
                }
            %>
    </nav>
</head>
