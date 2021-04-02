<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<link href="cssFile/login.css" rel="stylesheet">
<body>
<div id="loginDiv">
    <form action="${pageContext.request.contextPath}/login.do" method="post">

        <div id="infoHeader">
            <h1 id="userLoginTitle">Login</h1>
        </div>
        <div id="infoBody">
            <div id="errorInfo">${error}</div>
            <br>
            <div id="idDiv">
                <label for="id">Name</label><input type="text" id="id" name="id">
            </div>
            <br>
            <div id="pwdDiv">
                <label for="pwd">Password</label><input type="password" id="pwd" name="pwd">
            </div>
            <br>
            <div id="buttonDiv">
                <input type="submit" name="提交">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" name="重置">
            </div>
        </div>

    </form>
</div>
</body>
</html>