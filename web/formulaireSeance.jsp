<%--
  Created by IntelliJ IDEA.
  User: rayha
  Date: 12/12/2019
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<body>
<%
if(currentUser != null && currentUser.getRole() == User.Role.ADMIN) {

%>
    <h2>Formulaire de création de séances</h2> </br>
    <form action="addServlet" method="post">
        <label for="dateSeance">Date de la séance :</label>
        <input type="date" id="dateSeance" name="dateSeance">
        </br></br>
        <label for="heureDebut">Heure de début : </label>
        <input type="time" id="heureDebut" name="heureDebut">
        </br></br>
        <label for="heureFin">Heure de fin : </label>
        <input type="time" id="heureFin" name="heureFin">
        </br></br>
        <input type="submit" value="Créer la séance">
    </form>
<%
}
%>
</body>
</html>

