<%--
  Created by IntelliJ IDEA.
  User: rayha
  Date: 19/01/2020
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>

<body>
    <div class="newUser">
        <h3>Créer un utilisateur</h3>
        <form action="new_user" method="post">
            <label>Rôle : </label><br/>
            <select name="role" id="role" name="role">
                <option value="ADMIN">Admin</option>
                <option value="USER">User</option>
            </select>
            <br/><br/>

            <Label>Nom :</Label><br/>
            <input type="text" placeholder="Nom" id="nom" name="nom" required>
            <br/><br/>

            <label>Prénom :</label><br/>
            <input type="text" placeholder="Prénom utilisateur" id="prénom" name="prénom" required>
            <br/><br/>

            <label>Username :</label><br/>
            <input type="text" placeholder="Nom de compte" id="username" name="username" required>
            <br/><br/>

            <label>Mot de passe :</label><br/>
            <input type="password" placeholder="Password utilisateur" id="password" name="password" required>
            <br/><br/>

            <label>Confirmez le mdp :</label><br/>
            <input type="password" placeholder="Password utilisateur" id="confirm_password" name="confirm_password" required>
            <br/><br/>

            <input type="submit" value="Créer utilisateur">
        </form>
    </div>

<%
    if((request.getAttribute("newUser_error_equalsPasswords")) != null)
    {
        String error_message = (String) request.getAttribute("newUser_error_equalsPasswords");
        out.println("<p class='errorMsg'> " + error_message + "</p>");
    }
%>
</body>
</html>
