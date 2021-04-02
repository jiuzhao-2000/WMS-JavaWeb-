<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询</title>
</head>
<link href="../cssFile/allBody.css" rel="stylesheet">
<link href="../cssFile/table.css" rel="stylesheet">
<link href="../cssFile/rightBodyDiv.css" rel="stylesheet">
<body>
<%@ include file="../header/header.jsp" %>
<div id="bodyDiv">
    <%@ include file="../body/leftBodyDiv.jsp" %>
    <div id="rightBodyDiv">
        <div id="rightBodyDivHeader">
            <form action="${pageContext.request.contextPath}/sys/QueryWarehouseInfo.do" method="post">
                <br>
                <input type="hidden" name="pageNum" value="1">
                <label for="name">名称：</label>
                <input type="text" id="name" name="name">
                <label for="classInfo">类别：</label>
                <select name="classInfo" id="classInfo">
                    <option value="-1"><---请选择----></option>
                    <c:forEach var="c" items="${classes}">
                        <option value="${c.getId()}">${c.getName()}</option>
                    </c:forEach>
                </select>
                <input type="submit" name="查询">
                <hr>
            </form>
        </div>
        <div id="rightBodyDivBody">
            <table>
                <caption>仓库信息</caption>
                <tr>
                    <th>id</th>
                    <th>名称</th>
                    <th>库存</th>
                    <th>类别名称</th>
                </tr>
                <c:forEach var="thing" items="${thingList}">
                    <tr onmouseover="this.style.backgroundColor='#ff01c4';"
                        onmouseout="this.style.backgroundColor='#ffb700';">
                        <td><span>${thing.getId()}</span></td>
                        <td><span>${thing.getName()}</span></td>
                        <td><span>${thing.getNumber()}</span></td>
                        <td><span>${thing.getClassName()}</span></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="rightBodyDivFooter">
            <hr>
            <span id="rightBodyDivFooterLeftSpan">
                <a href="${pageContext.request.contextPath}/sys/QueryWarehouseInfo.do?pageNum=1&name=${param.name}&classInfo=${param.classInfo}">首页</a>
                &nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/sys/QueryWarehouseInfo.do?pageNum=${prePage}&name=${param.name}&classInfo=${param.classInfo}">上一页</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                ${param.pageNum}/${lastPage}页
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/sys/QueryWarehouseInfo.do?pageNum=${sufPage}&name=${param.name}&classInfo=${param.classInfo}">下一页</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/sys/QueryWarehouseInfo.do?pageNum=${lastPage}&name=${param.name}&classInfo=${param.classInfo}">尾页</a>&nbsp;&nbsp;
            共计${count}条记录
            </span>
            <span id="jumpSpan">跳至&nbsp;<input type="text" id="textPage" style="width: 30px">&nbsp;页</span>
        </div>
    </div>
</div>
<%@ include file="../footer/footer.jsp" %>
</body>
</html>