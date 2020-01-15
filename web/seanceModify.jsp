<%@ page import="model.Seance" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.sql.Time" %><%--
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
        Seance seance_to_modify = (Seance) request.getServletContext().getAttribute("seance_to_modify");
            if(seance_to_modify != null){

%>
<% if((request.getAttribute("message_seance")) != null)
{
    String message_seance = (String) request.getAttribute("message_seance");
    out.println("<p class='errorMsg'>" + message_seance + "</p>");
}

%>
    <h2>Formulaire de modification de la séance du : <% out.println(seance_to_modify.getDate().toString());%></h2> </br>
    <form action="modify_seance" method="post">
        <label for="heureDebut">Heure de début : </label>
        <input type="time" id="heureDebut" name="heureDebut" value=<% out.println(seance_to_modify.getHoraireDebut().toString());%>>
        </br></br>
        <label for="heureFin">Heure de fin : </label>
        <input type="time" id="heureFin" name="heureFin" value=<% out.println(seance_to_modify.getHoraireFin().toString());%>>
        </br></br>
        <input type="submit" value="Modifier la séance">
    </form>

<%
    }

}
%>
</body>
</html>

