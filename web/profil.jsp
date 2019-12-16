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

        if(currentUser.getRole() == User.Role.ADMIN){
            out.println("<h2>Vous êtes un admin</h2><i>un grand pouvoir implique de grandes responsabilités ...</i>");
        }

        if(currentUser.getJeux().isEmpty()){
            out.println("<h2>Vous n'avez pas encore ajouté de jeu à votre profil</h2>");
            out.println("<a href='addJeux.jsp'> Ajouter un jeu </a>");

        }
        if((request.getAttribute("message_jeu")) != null)
        {
            String message_jeu = (String) request.getAttribute("message_jeu");
            out.println("<p> " + message_jeu + "</p>");
        }
        if (!(currentUser.getJeux().isEmpty())) {
            out.println("<a href='addJeux.jsp'> Ajouter un nouveau jeu </a>");
            out.println("<h2>Voici vos jeux : </h2>");
            for (Jeux jeux : currentUser.getJeux()) {
                out.println("<h2> Jeu </h3>");
                out.println("<h3> Titre : " + jeux.titre + "</h3>");
                out.println("<h3> Description : " + jeux.description + "</h3>");
                out.println("<h3> Thème : " + jeux.theme + "</h3>");
                out.println("<h3> Durée : " + jeux.duree + " min.</h3>");
                out.println("<h3> Nb. joueur min. : " + jeux.nbJoueurMin + "</h3>");
                out.println("<h3> Nb. joueur max. : " + jeux.nbJoueurMax + "</h3>");
                out.println("<a href='jeuxServlet?id=" + jeux.getIdJeux() + "'>Supprimer le jeu</a>");
            }

        }

    }

%>

</body>
</html>
