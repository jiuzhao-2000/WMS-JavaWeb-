<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
</head>
<link href="../cssFile/allBody.css" rel="stylesheet">
<link href="../cssFile/table.css" rel="stylesheet">
<link href="../cssFile/rightBodyDiv.css" rel="stylesheet">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<body>
<%@ include file="../header/header.jsp" %>
<div id="bodyDiv">
    <%@ include file="../body/leftBodyDiv.jsp" %>
    <div id="rightBodyDiv">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <div id="rightBodyDivHeader">
            <br>
            <form action="${pageContext.request.contextPath}/sys/queryUser.do" method="post">
                <input type="hidden" name="pageNum" value="1">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="queryId">id:</label>
                <input type="text" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)"
                       onblur="this.v();"id="queryId" name="queryId">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="queryCode">昵称:</label>
                <input type="text" id="queryCode" name="queryCode">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <select name="flower">
                    <option value="0"><--请选择--></option>
                    <option value="1">管理员</option>
                    <option value="2">员工</option>
                    <option value="3">浏览者</option>
                </select>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="查询">
                <a href="${pageContext.request.contextPath}/sys/addUser.jsp">添加新用户</a>
            </form>
            <hr>
        </div>
        <div id="rightBodyDivBody">
            <table>
                <tr>
                    <th>id</th>
                    <th>用户名</th>
                    <th>密码</th>
                    <th>权限</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="user" items="${userList}">
                    <tr onmouseover="this.style.backgroundColor='#ff01c4';"
                        onmouseout="this.style.backgroundColor='#ffb700';">
                        <td><span>${user.getId()}</span></td>
                        <td><span>${user.getCode()}</span></td>
                        <td><span>${user.getPwd()}</span></td>
                        <td><span>${user.getPower()}</span></td>
                        <td style="text-align: center">
                            <a href="${pageContext.request.contextPath}/sys/UserOperation.do?method=r&id=${user.getId()}">查看</a>&nbsp;|&nbsp;
                            <a href="${pageContext.request.contextPath}/sys/UserOperation.do?method=d&id=${user.getId()}"
                               onclick="return confirm('您确定要删除${user.getCode()}用户吗？');">删除</a>&nbsp;|&nbsp;
                            <a href="${pageContext.request.contextPath}/sys/UserOperation.do?method=u&go=t&code=${user.getCode()}">修改</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <%--分页--%>
        <div id="rightBodyDivFooter">
            <hr>
            <span id="rightBodyDivFooterLeftSpan">
                <a href="${pageContext.request.contextPath}/sys/queryUser.do?pageNum=1&queryId=${param.queryId}&queryCode=${param.queryCode}&flower=${param.flower}">首页</a>
                &nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/sys/queryUser.do?pageNum=${prePage}&queryId=${param.queryId}&queryCode=${param.queryCode}&flower=${param.flower}">上一页</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                ${param.pageNum}/${lastPage}页
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/sys/queryUser.do?pageNum=${sufPage}&queryId=${param.queryId}&queryCode=${param.queryCode}&flower=${param.flower}">下一页</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/sys/queryUser.do?pageNum=${lastPage}&queryId=${param.queryId}&queryCode=${param.queryCode}&flower=${param.flower}">尾页</a>&nbsp;&nbsp;
            共计${count}条记录
            </span>
            <span id="jumpSpan">跳至&nbsp;<input type="text" id="textPage" style="width: 30px">&nbsp;页</span>
        </div>
    </div>
</div>
<%@ include file="../footer/footer.jsp" %>
</body>
</html>