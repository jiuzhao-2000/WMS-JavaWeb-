<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品进出库信息查询</title>
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
            <br>
            <form action="${pageContext.request.contextPath}/sys/QueryWarehouseLog.do" method="post">
                <input type="hidden" name="pageNum" value="1">
                <label for="name">名称：</label>
                <input type="text" id="name" name="name">
                <select name="isAdd">
                    <option value="null"><---请选择进出库---></option>
                    <option value="t">增加</option>
                    <option value="f">减少</option>
                </select>
                <label>起始时间：</label>
                <input type="date" name="beginTime">
                <label>结束时间：</label>
                <input type="date" name="lastTime">
                <input type="submit" name="查询">
                <hr>
            </form>
        </div>
        <div id="rightBodyDivBody">
            <table>
                <caption>仓库日志信息</caption>
                <tr>
                    <th>id</th>
                    <th>名称</th>
                    <th>时间</th>
                    <th>增加</th>
                    <th>数量</th>
                </tr>
                <c:forEach var="log" items="${LogsList}">
                    <tr onmouseover="this.style.backgroundColor='#ff01c4';"
                        onmouseout="this.style.backgroundColor='#ffb700';">
                        <td><span>${log.getId()}</span></td>
                        <td><span>${log.getName()}</span></td>
                        <td><span>${log.getTime()}</span></td>
                        <td><span>${log.isAdd()}</span></td>
                        <td><span>${log.getNumber()}</span></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="rightBodyDivFooter">
            <hr>
            <span id="rightBodyDivFooterLeftSpan">
                <a href="${pageContext.request.contextPath}/sys/QueryWarehouseLog.do?pageNum=1&name=${param.name}&isAdd=${param.isAdd}&beginTime=${param.beginTime}&lastTime=${param.lastTime}">首页</a>
                &nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/sys/QueryWarehouseLog.do?pageNum=${prePage}&name=${param.name}&isAdd=${param.isAdd}&beginTime=${param.beginTime}&lastTime=${param.lastTime}">上一页</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                ${param.pageNum}/${lastPage}页
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/sys/QueryWarehouseLog.do?pageNum=${sufPage}&name=${param.name}&isAdd=${param.isAdd}&beginTime=${param.beginTime}&lastTime=${param.lastTime}">下一页</a>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/sys/QueryWarehouseLog.do?pageNum=${lastPage}&name=${param.name}&isAdd=${param.isAdd}&beginTime=${param.beginTime}&lastTime=${param.lastTime}">尾页</a>&nbsp;&nbsp;
            共计${count}条记录
            </span>
            <span id="jumpSpan">跳至&nbsp;<input type="text" id="textPage" style="width: 30px">&nbsp;页</span>
        </div>
    </div>
</div>
<%@ include file="../footer/footer.jsp" %>
</body>
</html>
