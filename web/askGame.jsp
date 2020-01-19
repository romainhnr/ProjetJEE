<%@ page import="model.Seance" %>
<%@ page import="java.util.List" %>
<%@ page import="servlet.InitServlet" %>
<%@ page import="java.util.UUID" %>
<%@ page import="model.Jeux" %><%--
  Created by IntelliJ IDEA.
  User: rayha
  Date: 12/12/2019
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<body>
    <%
        List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(InitServlet.CONTEXT_SEANCES);
        String id = request.getParameter("id");

        for (Seance seance : listSeance) {
            List<User> userIncertain = seance.getListUserInscritIncertain();
            List<User> userCertain = seance.getListUserInscritCertain();

            for(User user : userCertain) {
                out.println("<h3>Jeux des utilisateurs certains : </h3>");
                for (Jeux jeux : user.getJeux()) {
                    out.println("<div class='borderJ'> <h3> Titre : " + jeux.getTitre() + "</h3>");
                    out.println("<h3> Thème : " + jeux.getTheme() + "</h3>");
                    out.println("<h3> Durée : " + jeux.getDuree() + " min.</h3> </div>");
                    out.println("<a class='button' href='seance.jsp'> Demander le jeu </a>");
                }
            }

            for(User user : userIncertain) {
                out.println("<h3>Jeux des utilisateurs Incertains : </h3>");
                for (Jeux jeux : user.getJeux()) {
                    out.println("<div class='borderJ'> <h3> Titre : " + jeux.getTitre() + "</h3>");
                    out.println("<h3> Thème : " + jeux.getTheme() + "</h3>");
                    out.println("<h3> Durée : " + jeux.getDuree() + " min.</h3> </div>");
                    out.println("<a class='button' href='seance.jsp'> Demander le jeu </a>");
                }
            }
        }


    %>

</body>
</html>
