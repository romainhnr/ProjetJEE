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
    <title>ProjetJEE</title>
  </head>
  <body>
  <%
    User currentUser = ((User)(session.getAttribute("user")));

    if(currentUser == null){
      out.println("<h1> Vous n'êtes pas connecté</h1>");
      out.println("<a href ='login.jsp'> Connectez-vous </a>");
    }
    else if(currentUser.getRole() == User.Role.ADMIN){
      out.println("<h1> Bienvenue Admin </h1>");
      out.println("<h2> Nom : " + currentUser.getNom() + "</h2>");
      out.println("<a href = 'logout'> Se déconnecter </a>");
    }

    else{
      out.println("<h1> Bienvenue Utilisateur </h1>");
      out.println("<h2> Nom : " + currentUser.getNom() + "</h2>");
      out.println("<a href = 'logout'> Se déconnecter </a>");
    }
  %>

  </body>
</html>
