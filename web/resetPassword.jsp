<%--
  Created by IntelliJ IDEA.
  User: rayha
  Date: 19/01/2020
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<% if(currentUser != null && currentUser.getRole() == User.Role.ADMIN) { %>
<body>
    <div class="reset">
        <h3>Réinitialiser un mdp :</h3>
        <form action="reset_password" method="post">
            <label>Username utilisateur :</label><br/>
            <input type="text" placeholder="Username utilisateur" id="resetUsername" name="resetUsername" required>
            <br/><br/>

            <label>Nouveau mot de passe :</label><br/>
            <input type="password" placeholder="Password utilisateur" id="resetUserPassword" name="resetUserPassword" required>
            <br/><br/>

            <label>Confirmez le mot de passe :</label><br/>
            <input type="password" placeholder="Password utilisateur" id="confirmUserResetPassword" name="confirmUserResetPassword" required>
            <br/><br/>

            <label>Votre mot de passe admin :</label><br/>
            <input type="password" placeholder="votre mot de passe admin" id="passwordReset" name="passwordReset" required>
            <br/><br/>

            <input class="button" type="submit" value="Réinitialiser">
        </form>
    </div>
<%
    if((request.getAttribute("error_form_password")) != null)
    {
        String error_form_password = (String) request.getAttribute("error_form_password");
        out.println("<p class='errorMsg'> " + error_form_password + "</p>");
    }
%>
</body>
<% } %>
</html>
