<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 26/11/2019
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
  <body>
  <%
      if(currentUser != null) {
          if (currentUser.getRole() == User.Role.ADMIN) {
              out.println("<h1> Bienvenue Admin " + currentUser.getNom() + "</h1>");
          }
          else {
              out.println("<h1> Bienvenue " + currentUser.getNom() + "</h1>");
          }
      }
  %>

  </body>
</html>
