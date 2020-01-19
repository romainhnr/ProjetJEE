<%--
  Created by IntelliJ IDEA.
  User: rayha
  Date: 19/01/2020
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>

<body>
    <div class="test">
        <form action="password_change" method="post">

            <label for="mdp">Mot de passe actuel :</label><br/>
            <input type="password" placeholder="Votre password" id="mdp" name="mdp" required>
            <br/><br/>

            <label>Nouveau mot de passe :</label><br/>
            <input type="password" placeholder="Nouveau password" id="new_password" name="new_password" required>
            <br/><br/>

            <label>Confirmer le mot de passe :</label><br/>
            <input type="password" placeholder="Confirm password" id="confirm_password" name="confirm_password" required>
            <br/><br/>

            <input class="button"  type="submit" value="Sauvegarder">
        </form>
    </div>

    <% if((request.getAttribute("message_error_password")) != null)
    {
        String error_message = (String) request.getAttribute("message_error_password");
        out.println("<p class='errorMsg'> " + error_message + "</p>");
    }
    if((request.getAttribute("message_error_equalsPasswords")) != null)
    {
        String error_message = (String) request.getAttribute("message_error_equalsPasswords");
        out.println("<p class='errorMsg'> " + error_message + "</p>");
    }
    %>

</body>

</html>
