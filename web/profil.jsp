<%@ page import="model.Jeux" %><%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 12/12/2019
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<body>
<%
    if(currentUser != null) {

        Jeux jeux1 = new Jeux("Loup garou", "jeux de loup garou", Jeux.Theme.STRATEGIE, 20, 2, 10);
        Jeux jeux2 = new Jeux("Monopoly", "jeux de monopoly", Jeux.Theme.AMBIANCE, 30, 2, 5);

        currentUser.listeJeux.add(jeux1);
        currentUser.listeJeux.add(jeux2);

        out.println("<h1>Voici vos jeux : </h1>");
        for (Jeux jeux : currentUser.getJeux()) {
            out.println("<h1> Jeux </h1>");
            out.println("<h2> Titre : " + jeux.titre + "</h2>");
            out.println("<h2> Description : " + jeux.description + "</h2>");
            out.println("<h2> Thème : " + jeux.theme + "</h2>");
            out.println("<h2> Durée : " + jeux.duree + " min.</h2>");
            out.println("<h2> Nb. joueur min. : " + jeux.nbJoueurMin + "</h2>");
            out.println("<h2> Nb. joueur max. : " + jeux.nbJoueurMax + "</h2>");

        }
    }

%>

</body>
</html>
