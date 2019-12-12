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
    if(currentUser.getRole() == User.Role.ADMIN) {
        out.println("<a href='#'>Créer une séance</a>");
    }
    List<Seance> listSeance = (List<Seance>) (session.getAttribute("listSeance"));

    for (Seance seance : listSeance) {
        out.println("<h1> Séance </h1>");
        out.println("<h2> Date : " + seance.date + "</h2>");
        out.println("<h2> Horaire de début : " + seance.horaireDebut + "</h2>");
        out.println("<h2> Horaire de fin : " + seance.horaireFin + "</h2>");
        out.println("<a href='#'>S'inscrire de manière certaine</a>");
        out.println("<a href='#'>S'inscrire de manière incertaine</a>");
        out.println("<a href='#'>Voir la liste des inscrits</a>");
        if(currentUser.getRole() == User.Role.ADMIN) {
            out.println("<a href='#'>Modifier la séance</a>");
            out.println("<a href='#'>Supprimer la séance</a>");
        }

    }
%>

</body>
</html>
