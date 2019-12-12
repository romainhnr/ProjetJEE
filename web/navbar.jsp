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
                out.println("<a href ='login.jsp'> Connectez-vous </a>");
            }

            else if(currentUser.getRole() == User.Role.ADMIN){
            out.println("<h1> Bienvenue Admin " + currentUser.getNom() + "</h1>");
            }
            else{
                out.println("<h1> Bienvenue " + currentUser.getNom() + "</h1>");
                }
            %>
            <div class="links">
                <ul>
                    <li><a href="seance.jsp">Seance</a></li>
                    <li><a href="#">Messagerie</a></li>
                    <li><a href="#">Profile</a></li>
                    <li><a href="logout">Déconnexion</a></li>
                    <% if(currentUser.getRole() == User.Role.ADMIN) { %>
                    <li> <a href='#'>Gestion Adherents</a> </li>
                    <% } %>
                </ul>
            </div>

    </nav>
</head>
