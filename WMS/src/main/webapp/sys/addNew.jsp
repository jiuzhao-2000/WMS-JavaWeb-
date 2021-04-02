<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新商品入库管理</title>
</head>
<link href="../cssFile/allBody.css" rel="stylesheet">
<link href="../cssFile/addTable.css" rel="stylesheet">
<body>
<%@ include file="../header/header.jsp" %>
<div id="bodyDiv">
    <%@ include file="../body/leftBodyDiv.jsp" %>
    <div id="rightBodyDiv">
        <form action="${pageContext.request.contextPath}/sys/AddNewGoods.do" method="post"  onsubmit="return onClickSubmit()">
            <table>
                <caption><h2>添加新商品</h2></caption>
                <tr>
                    <td><span><label>名称：</label></span></td>
                    <td><span><input type="text" name="name"></span></td>
                    <td><span><label id="nameShow"></label></span></td>
                </tr>
                <tr>
                    <td><span><label>数量：</label></span></td>
                    <td><span><input type="number" name="number" min="0"></span></td>
                    <td><span><label></label></span></td>
                </tr>
                <tr>
                    <td><span><label>类别：</label></span></td>
                    <td>
                        <span>
                            <select name="classInfo" id="classInfo">
                                <option value="-1"><---请选择----></option>
                                <c:forEach var="c" items="${classes}">
                                    <option value="${c.getId()}">${c.getName()}</option>
                                </c:forEach>
                            </select>
                        </span>
                    </td>
                    <td><span><label></label></span></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <span><input type="submit" name="添加"></span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%@ include file="../footer/footer.jsp" %>
</body>
</html>