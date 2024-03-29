<%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 14/01/2020
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<% if(currentUser != null) { %>
<html>
<head>
    <title>Inscription séance</title>
</head>
<body>
    <div class="seanceRegister">
        <form id="formSeanceRegister" action="registration_seance" method="post">
            <label for="status">Status : </label><br/>
            <select name="choixStatus" id="status" onchange="test()">
                <option value="Certain">Certain</option>
                <option value="Incertain">Incertain</option>
            </select><br/><br/>

            <div id="mlk" style="visibility: hidden">
                <label for="joueurMin">Nb joueur voulu : </label><br/>
                <input type="number" id="joueurMin" min="2" value="2" max="12" name="joueurMin">
                <br/><br/>
            </div>

            <input type="submit" class='button' value="S'inscrire">
        </form>
    </div>
</body>
<script type="text/javascript">
    let status = document.getElementById("status").value;
    status.options[status.selectedIndex].value;

function test(){
    if(status === "Incertain"){
        document.getElementById("mlk").style.visibility = "hidden";
        status = "Certain";
    }
    else if(status === "Certain"){
        document.getElementById("mlk").style.visibility = "visible";
        status = "Incertain";
    }
}
</script>
</html>
<% } %>
