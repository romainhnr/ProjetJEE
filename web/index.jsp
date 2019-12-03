<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 26/11/2019
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <%  User currentUser = ((User)(session.getAttribute("user"))); %>
    <title>ProjetJEE</title>
    <nav>
      <div class="logo">
        Logo texte ou image
      </div>
      <div class="links">
        <ul>
          <li><a href="#">Seance</a></li>
          <li><a href="#">Messagerie</a></li>
          <li><a href="#">Profile</a></li>
          <li><a href="logout">Déconnexion</a></li>
          <% if(currentUser.getRole() == User.Role.ADMIN) { out.print("<li> <a href='#'>Gestion Adherents</a> </li>"); }%>
        </ul>
      </div>
    </nav>
  </head>
  <body>
  <%

    if(currentUser == null){
      out.println("<h1> Vous n'êtes pas connecté</h1>");
      out.println("<a href ='login.jsp'> Connectez-vous </a>");
    }
    else if(currentUser.getRole() == User.Role.ADMIN){
      out.println("<h1> Bienvenue Admin </h1>");
      out.println("<h2> Nom : " + currentUser.getNom() + "</h2>");
    }

    else{
      out.println("<h1> Bienvenue Utilisateur </h1>");
      out.println("<h2> Nom : " + currentUser.getNom() + "</h2>");
    }
  %>

  </body>
</html>
