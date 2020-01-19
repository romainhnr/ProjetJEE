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
    <% /* On veut parcourir les séances pour savoir la séance sur laquel on est, ensuite on récupère ses listes d'user
        inscrit et on affiche leurs détails.
       */
        List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(InitServlet.CONTEXT_SEANCES);
        String id = request.getParameter("id");
        UUID UUID_id = UUID.fromString(id);
        int certain = 0;
        int incertain = 0;

        for (Seance seance : listSeance){
            if(seance.getIdSeance().equals(UUID_id)){
                List<User> userIncertain = seance.getListUserInscritIncertain();
                List<User> userCertain = seance.getListUserInscritCertain();
                List<Jeux> jeuxDemander = seance.getSeanceListeJeux();
                out.println("<h3>Inscrits certains: </h3>");

                for (User userC : userCertain) {
                    out.println("<div class='jeuxCertains'><h3> Nom : " + userC.getNom() + "</h3>");
                    out.println("<h2> Jeu </h3>");

                    for (Jeux jeux : currentUser.getJeux()) {
                        out.println("<div class='borderJ'> <h3> Titre : " + jeux.getTitre() + "</h3>");
                        out.println("<h3> Thème : " + jeux.getTheme() + "</h3>");
                        out.println("<h3> Durée : " + jeux.getDuree() + " min.</h3> </div>");
                    }
                    out.println( "</div>");
                    certain ++;
                }


                for (User userI : userIncertain){
                    out.println("<h3>Inscrits intercains : </h3>");
                    out.println("<div class='jeuxIncertains'><h3> Nom : " + userI.getNom() + "</h3>");
                    out.println("<h2> Jeu </h3>");

                    for (Jeux jeux : currentUser.getJeux()) {
                        out.println("<div class='borderJ'> <h3> Titre : " + jeux.getTitre() + "</h3>");
                        out.println("<h3> Thème : " + jeux.getTheme() + "</h3>");
                        out.println("<h3> Durée : " + jeux.getDuree() + " min.</h3></div>");
                    }
                    out.println( "</div>");
                    incertain ++;
                }

                out.println("<h3>Jeux demandés : </h3>");
                for (Jeux jeu : jeuxDemander){
                    out.println("<div class='borderJ'> <h3> Titre : " + jeu.getTitre() + "</h3>");
                    out.println("<h3> Thème : " + jeu.getTheme() + "</h3>");
                    out.println("<h3> Durée : " + jeu.getDuree() + " min.</h3></div>");
                }
                out.println("<a class='button' href='askGame.jsp'> Demander un jeu </a><br/>");

                out.println("Nombre des utilisateurs certains :" + certain + "<br/>");
                out.println("Nombre des utilisateurs certains :" + incertain );
            }
        }
    %>
</body>
</html>
