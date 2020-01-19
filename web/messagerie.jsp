<%@ page import="model.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.LocalDateTime" %><%--
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
            out.println("<h1>Bienvenue sur votre messagerie, " + currentUser.getNom() + "</h1>");

            List<Message> listMessages = currentUser.getListeMessages();

            if (listMessages == null || listMessages.isEmpty()) {
                out.println("<h2> Aucun message n'est présent </h2>");
            }
            else {
                for (Message message : listMessages) {
                    if (message.getDateTimeMessage().isAfter(LocalDateTime.now().minusDays(10))) {
                        out.println("<h3> --- Message --- </h3>");
                        out.println("<h4> Date : " + message.getDateTimeMessage().getDayOfMonth() + "/" + message.getDateTimeMessage().getMonthValue() + "/" + message.getDateTimeMessage().getYear() + " - " + message.getDateTimeMessage().getHour() + ":" + message.getDateTimeMessage().getMinute() + ":" + message.getDateTimeMessage().getSecond() + " </h4>");
                        out.println("<p> Texte : " + message.getTexteMessage() + "</p>");
                        out.println("<i> Est lu : " + message.getEstLu() + "</i>");
                        if (!(message.getEstLu())) {
                            out.println("<a href='messagerie?id=" + message.getIdMessage() + "'>Marquer comme lu</a>");
                        } else {
                            out.println("<a href='messagerie?id=" + message.getIdMessage() + "'>Marquer comme non lu</a>");
                        }

                    }
                }

            }
        }
%>
</body>
</html>
