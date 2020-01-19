<%--
  Created by IntelliJ IDEA.
  User: rayha
  Date: 19/01/2020
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>

<body>
    <%
        if(currentUser != null && currentUser.getRole() == User.Role.ADMIN) {
            if ((request.getAttribute("message_gestionAdherents")) != null) {
                String message_gestionAdherents = (String) request.getAttribute("message_gestionAdherents");
                out.println("<p> " + message_gestionAdherents + "</p>");
            }

            out.println("<a class='button' href='nouveauAdherents.jsp'>Créer un nouvel utilisateur</a>");
            out.println("<a class='button' href='resetPassword.jsp'>Réinitialiser mot de passe</a>");
        }
    %>


</body>
</html>
