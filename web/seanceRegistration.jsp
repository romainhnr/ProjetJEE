<%--
  Created by IntelliJ IDEA.
  User: romai
  Date: 14/01/2020
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<html>
<head>
    <title>Inscription séance</title>
</head>
<body>
    <div class="seanceRegister">
        <form id="formSeanceRegister" action="registration_seance" method="post">
            <label for="status">Status : </label><br/>
            <select name="choixStatus" id="status" onchange="test()">
                <option value="1">Certain</option>
                <option value="0">Incertain</option>
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
    if(status === "0"){
        document.getElementById("mlk").style.visibility = "hidden";
        status = "1";
    }
    else if(status === "1"){
        document.getElementById("mlk").style.visibility = "visible";
        status = "0";
    }
}
</script>
</html>
