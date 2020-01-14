<%@ page import="model.Seance" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 03/12/2019
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<body>

<%
    if(currentUser != null) {
        if (currentUser.getRole() == User.Role.ADMIN) {
            out.println("<a class='button' href='seanceAdd.jsp'>Créer une séance</a>");
        }
        if((request.getAttribute("message_seance")) != null)
        {
            String message_seance = (String) request.getAttribute("message_seance");
            out.println("<p> " + message_seance + "</p>");
        }

        List<Seance> listSeance = (List<Seance>)request.getServletContext().getAttribute("listSeance");

        if(listSeance == null || listSeance.isEmpty()){
            out.println("<h2> Aucune séance n'est présente </h2>");
        }
        else {
            for (Seance seance : listSeance) {
                out.println("<div class='divSeance'> <h2> Séance </h2>");
                out.println("<h3> Date : " + seance.getDate() + "</h2>");
                out.println("<h3> Horaire de début : " + seance.getHoraireDebut() + "</h3>");
                out.println("<h3> Horaire de fin : " + seance.getHoraireFin() + "</h3>");
                out.println("<a class='button' href='registration_seance?id=" + seance.getIdSeance() + "'>S'inscrire</a>");
                out.println("<a class='button' href='unregistration_seance?id=" + seance.getIdSeance() + "'>Se désinscrire</a>");
                out.println("<a class='button' href='details_seance?id=" + seance.getIdSeance() + "'>Voir le détail de la séance</a>");
                if (currentUser != null && currentUser.getRole() == User.Role.ADMIN) {
                    out.println("<a class='buttonImportant' href='modify_seance?id=" + seance.getIdSeance() + "'>Modifier la séance</a>");
                    out.println("<a class='buttonImportant' href='add_remove_seance?id=" + seance.getIdSeance() + "'>Supprimer la séance</a></div>");
                }

            }
        }


    }
%>

</body>
</html>
