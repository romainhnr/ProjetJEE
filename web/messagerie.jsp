<%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 12/12/2019
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<body>
<%
        if(currentUser != null) {
            out.println("<h1>Bienvenue sur votre messagerie, " + currentUser.getNom() + " :</h1>");

        }
%>
</body>
</html>
