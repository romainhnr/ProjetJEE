<%--
  Created by IntelliJ IDEA.
  User: rayha
  Date: 03/12/2019
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="login">
        <form action="login" method="post">
                <label for="inputLogin">Login :</label></br>
                <input type="text" name="login" placeholder="login"  id="inputLogin" class="test "/>
                </br></br>
                <label for="inputPassword">Mot de passe: </label></br>
                <input type="password" name="password" placeholder="mot de passe" id="inputPassword"/>
                </br></br>
                <input type="submit" value="Envoyer" />

            <% if((request.getAttribute("message")) != null)
            {
                String error_message = (String) request.getAttribute("message");
                out.println(error_message);
            }

            %>
        </form>
    </div>
</body>
</html>
