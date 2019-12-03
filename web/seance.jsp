<%@ page import="model.Seance" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 03/12/2019
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProjetJEE</title>
</head>
<body>

<%
    List<Seance> listSeance = (List<Seance>) (session.getAttribute("listSeance"));

    for (Seance seance : listSeance) {
        out.println("<h1> Seance </h1>");
        out.println("<h2> Date : " + seance.date + "</h2>");
        out.println("<h2> Horaire de début : " + seance.horaireDebut + "</h2>");
        out.println("<h2> Horaire de fin : " + seance.horaireFin + "</h2>");



    }
%>

</body>
</html>
