<%--
  Created by IntelliJ IDEA.
  User: rayha
  Date: 12/12/2019
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>

<html>
<head>
    <title>Creation de seance</title>
</head>
<body>
    <p>Formulaire de création de Seance</p> </br>
    <form>
        <label for="dateSeance">Date de la seance :</label>
        <input type="date" id="dateSeance">
        </br></br>
        <label for="heureDebut">Heure de début : </label>
        <input type="time" id="heureDebut">
        </br></br>
        <label for="heureFin">Heure de fin : </label>
        <input type="time" id="heureFin">
        </br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
