<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<link href="../cssFile/allBody.css" rel="stylesheet">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<link href="../cssFile/alterPwd.css" rel="stylesheet">
<body>
<%@ include file="../header/header.jsp" %>
<div id="bodyDiv">
    <%@ include file="../body/leftBodyDiv.jsp" %>
    <div id="rightBodyDiv">
        <form action="${pageContext.request.contextPath}/sys/alterPwd.do" onsubmit="return onClickSubmit()"
              method="post">
            <div id="pwdDiv">
                <input type="hidden" name="method" value="alterPwd">
                <label for="oldPwd">原密码：</label><input type="password" name="oldPwd" id="oldPwd">
                <text></text>
                <hr>
                <label for="newPwd">新密码：</label><input type="password" name="newPwd" id="newPwd">
                <text></text>
                <hr>
                <label for="again">再一次：</label><input type="password" name="again" id="again">
                <text></text>
                <hr>
                <input type="submit" value="修改">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置">
            </div>
        </form>
    </div>
</div>
<%@ include file="../footer/footer.jsp" %>
<script src="../js/alterPwd.js"></script>
</body>
</html>