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
</head>
<body>
    <form action="login" method="post">
        <p>
            <label for="pseudo"></label>
            <input type="text" name="login" placeholder="login"  id="inputLogin"/>

            <br />
            <label for="pass"></label>
            <input type="password" name="password" placeholder="mdp" id="inputPassword"/>


            <br />
            <input type="submit" value="Envoyer" />
        </p>
    </form>
</body>
</html>
