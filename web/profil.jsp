<%@ page import="model.Jeux" %><%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 12/12/2019
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<body>
<%
    if(currentUser != null) {
        out.println("<h1>Bienvenue sur votre profil, " + currentUser.getNom() + " :</h1>");

        if(currentUser.getJeux().isEmpty()){
            out.println("<h2>Vous n'avez pas encore ajouté de jeu à votre profil</h2>");
            out.println("<a href='#'> Ajouter un jeu </a>");

        }
        else {
            out.println("<h2>Voici vos jeux : </h2>");
            for (Jeux jeux : currentUser.getJeux()) {
                out.println("<h2> Jeux </h3>");
                out.println("<h3> Titre : " + jeux.titre + "</h3>");
                out.println("<h3> Description : " + jeux.description + "</h3>");
                out.println("<h3> Thème : " + jeux.theme + "</h3>");
                out.println("<h3> Durée : " + jeux.duree + " min.</h3>");
                out.println("<h3> Nb. joueur min. : " + jeux.nbJoueurMin + "</h3>");
                out.println("<h3> Nb. joueur max. : " + jeux.nbJoueurMax + "</h3>");

            }
            out.println("<a href='#'> Ajouter un nouveau jeu </a>");
        }
    }

%>

</body>
</html>
