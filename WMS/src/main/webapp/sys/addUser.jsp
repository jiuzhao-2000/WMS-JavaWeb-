<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品进出库信息查询</title>
</head>
<link href="../cssFile/allBody.css" rel="stylesheet">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<link href="../cssFile/addUser.css" rel="stylesheet">
<body>
<%@ include file="../header/header.jsp" %>
<div id="bodyDiv">
    <%@ include file="../body/leftBodyDiv.jsp" %>
    <div id="rightBodyDiv">
        <div id="rightBodyDivForm">
            <form action="${pageContext.request.contextPath}/sys/UserOperation.do" method="post"
                  onsubmit="return isSubmitOk()">
                <input type="hidden" name="method" value="c">
                <span>
                    <label for="code">用户名：</label><input type="text" name="code" id="code">
                    <text></text>
                </span>
                <br>
                <br>
                <span>
                    <label for="pwd">密&nbsp;&nbsp;码：</label><input type="password" name="pwd" id="pwd">
                    <text></text>
                </span>
                <br>
                <br>
                <span>权限：</span>
                <span>
                    <input type="checkbox" name="power" id="power1" value="c"><label>增</label>
                    <input type="checkbox" name="power" id="power2" value="d"><label>删</label>
                    <input type="checkbox" name="power" id="power3" value="u"><label>改</label>
                    <input type="checkbox" name="power" id="power4" value="r"><label>查</label>
                </span>
                <br>
                <br>
                <hr>
                <span>
                    <input type="submit" name="创建">&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" name="重置">
                </span>
            </form>
        </div>
    </div>
</div>
<%@ include file="../footer/footer.jsp" %>
<script src="../js/addUser.js"></script>
</body>
</html>