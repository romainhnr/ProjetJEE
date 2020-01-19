<%@ page import="model.Seance" %>
<%@ page import="java.util.List" %>
<%@ page import="servlet.InitServlet" %>
<%@ page import="java.util.UUID" %>
<%@ page import="model.Jeux" %><%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 14/01/2020
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<body>
    <%
        if(currentUser != null) {

            if ((request.getAttribute("message_seance")) != null) {
                String message_seance = (String) request.getAttribute("message_seance");
                out.println("<p> " + message_seance + "</p>");
            }

            Seance seanceDetails_to_show = (Seance) request.getServletContext().getAttribute("seanceDetails_to_show");

            if (seanceDetails_to_show.getListUserInscritCertain().size() == 0 && seanceDetails_to_show.getListUserInscritIncertain().size() == 0){
                out.println("<h3> Aucune personne n'est inscrite pour le moment à la séance </h3>");
                out.println("<a href ='seance.jsp' class='button'> Retourner à la liste des séances</a>");
            }
            else {
                if(seanceDetails_to_show.getListUserInscritCertain().size() == 0){
                    out.println("<h3> Aucune personne ne s'est inscrite pour le moment de manière certaine à la séance </h3>");
                }
                else {
                    out.println("<h3>Inscrits certains : </h3>");
                    for (User userCertain : seanceDetails_to_show.getListUserInscritCertain()) {
                        out.println("<div class='jeuxCertains'><h3> Nom : " + userCertain.getNom() + "</h3>");
                        if(!(userCertain.getJeux().size() == 0)) {
                            out.println("<h4> Jeux : </h4>");
                            for (Jeux jeux : userCertain.getJeux()) {
                                out.println("<div class='borderJ'> <h5> Titre : " + jeux.getTitre() + "</h5>");
                                out.println("<h5> Thème : " + jeux.getTheme() + "</h5>");
                                out.println("<h5> Durée : " + jeux.getDuree() + " min.</h5> </div>");
                                out.println("<a class='button' href='askGameServlet?id=" + jeux.getIdJeux() + "'> Demander le jeu </a>");
                            }
                        }
                        out.println("</div>");
                    }
                }
                if(seanceDetails_to_show.getListUserInscritIncertain().size() == 0){
                    out.println("<h3> Aucune personne ne s'est inscrite pour le moment de manière incertaine à la séance </h3>");
                }
                else {
                    out.println("<h3>Inscrits Incertains : </h3>");
                    for (User userIncertain : seanceDetails_to_show.getListUserInscritIncertain()) {
                        out.println("<div class='jeuxCertains'><h3> Nom : " + userIncertain.getNom() + "</h3>");
                        if(!(userIncertain.getJeux().size() == 0)) {
                            out.println("<h4> Jeux : </h4>");
                            for (Jeux jeux : userIncertain.getJeux()) {
                                out.println("<div class='borderJ'> <h5> Titre : " + jeux.getTitre() + "</h5>");
                                out.println("<h5> Thème : " + jeux.getTheme() + "</h5>");
                                out.println("<h5> Durée : " + jeux.getDuree() + " min.</h5> </div>");
                                out.println("<a class='button' href='askGameServlet?id=" + jeux.getIdJeux() + "'> Demander le jeu </a>");
                            }
                        }
                        out.println("</div>");
                    }
                }
                if(seanceDetails_to_show.getSeanceListeJeux().size() == 0){
                    out.println("<h3> Aucun jeu n'est pour le moment demandé pour la séance </h3>");
                }
                else {
                    out.println("<h3>Jeux demandés : </h3>");
                    for (Jeux jeu : seanceDetails_to_show.getSeanceListeJeux()) {
                        out.println("<div class='borderJ'> <h4> Titre : " + jeu.getTitre() + "</h4>");
                        out.println("<h4> Thème : " + jeu.getTheme() + "</h4>");
                        out.println("<h4> Durée : " + jeu.getDuree() + " min.</h4></div>");
                    }
                }
                out.println("<h4>Informations :</h4>");
                out.println("Nombre d'inscrits certains : " + seanceDetails_to_show.getListUserInscritCertain().size() + "<br/>");
                out.println("Nombre d'inscrits incertains : " + seanceDetails_to_show.getListUserInscritIncertain().size() + "<br/>");
                int totalInscrits = seanceDetails_to_show.getListUserInscritCertain().size() + seanceDetails_to_show.getListUserInscritIncertain().size();
                out.println("Nombre d'inscrits au total : " + totalInscrits);
            }
        }
    %>
</body>
</html>
