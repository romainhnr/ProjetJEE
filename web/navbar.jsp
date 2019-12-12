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
    <nav>
        <div class="logo">
            <%
            if(currentUser == null){
                out.println("<h1>Vous n'êtes pas connecté</h1>");
                out.println("<a href ='login.jsp'> Connectez-vous </a>");
            }

            else{
            %>
            <div class="links">
                <ul>
                    <li><a href="seance.jsp">Séance</a></li>
                    <li><a href="#">Messagerie</a></li>
                    <li><a href="profil.jsp">Profil</a></li>
                    <% if(currentUser.getRole() == User.Role.ADMIN) { %>
                    <li> <a href='#'>Gestion Adherents</a> </li>
                    <% } %>
                    <li><a href="logout">Déconnexion</a></li>
                </ul>
            </div>
            <%
                }
            %>

    </nav>
</head>
