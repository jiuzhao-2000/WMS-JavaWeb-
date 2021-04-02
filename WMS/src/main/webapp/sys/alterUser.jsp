<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户</title>
</head>
<link href="../cssFile/allBody.css" rel="stylesheet">
<link href="../cssFile/table.css" rel="stylesheet">
<body>
<%@ include file="../header/header.jsp" %>
<div id="bodyDiv">
    <%@ include file="../body/leftBodyDiv.jsp" %>
    <div id="rightBodyDiv">
        <form action="${pageContext.request.contextPath}/sys/UserOperation.do?method=u" method="post">
            <input type="hidden" name="id" value="${id}">
            <table>
                <caption>修改用户</caption>
                <tr onmouseover="this.style.backgroundColor='#ff01c4';"
                    onmouseout="this.style.backgroundColor='#ffb700';">
                    <td><label for="code">code:</label></td>
                    <td><input type="text" id="code" name="code" value="${param.code}"></td>
                    <td>
                        <text id="codeInfo"></text>
                    </td>
                </tr>
                <tr onmouseover="this.style.backgroundColor='#ff01c4';"
                    onmouseout="this.style.backgroundColor='#ffb700';">
                    <td><label for="pwd">pwd：</label></td>
                    <td><input type="text" id="pwd" name="pwd" value="${pwd}"></td>
                    <td>
                        <text id="pwdInfo"></text>
                    </td>
                </tr>
                <tr>
                    <td colspan="3"><input type="submit" name="提交">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" name="清空"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%@ include file="../footer/footer.jsp" %>
</body>
</html>
