<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示用户</title>
</head>
<link href="../cssFile/allBody.css" rel="stylesheet">
<link href="../cssFile/table.css" rel="stylesheet">
<body>
<%@ include file="../header/header.jsp" %>
<div id="bodyDiv">
    <%@ include file="../body/leftBodyDiv.jsp" %>
    <div id="rightBodyDiv">
        <table>
            <caption>${code}</caption>
            <tr onmouseover="this.style.backgroundColor='#ff01c4';"
                onmouseout="this.style.backgroundColor='#ffb700';">
                <td>id:</td>
                <td>${id}</td>
            </tr>
            <tr onmouseover="this.style.backgroundColor='#ff01c4';"
                onmouseout="this.style.backgroundColor='#ffb700';">
                <td>pwd:</td>
                <td>${pwd}</td>
            </tr>
            <tr onmouseover="this.style.backgroundColor='#ff01c4';"
                onmouseout="this.style.backgroundColor='#ffb700';">
                <td>power:</td>
                <td>${power}</td>
            </tr>
        </table>
    </div>
</div>
<%@ include file="../footer/footer.jsp" %>
</body>
</html>