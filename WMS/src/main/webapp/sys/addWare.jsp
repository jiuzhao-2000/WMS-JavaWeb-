<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品入库</title>
</head>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<link href="../cssFile/allBody.css" rel="stylesheet">
<link href="../cssFile/addTable.css" rel="stylesheet">
<body>
<%@ include file="../header/header.jsp" %>
<div id="bodyDiv">
    <%@ include file="../body/leftBodyDiv.jsp" %>
    <div id="rightBodyDiv">
        <form action="${pageContext.request.contextPath}/sys/AddGoods.do" method="post" onsubmit="return onClickSubmit()">
            <table>
                <caption><h2>商品进出库</h2></caption>
                <tr>
                    <td><span><label>名称：</label></span></td>
                    <td><span><input type="text" id="name" name="name"></span></td>
                    <td><span><label id="nameShow"></label></span></td>
                </tr>
                <tr>
                    <td><span><label>数量：</label></span></td>
                    <td><span><input type="number" name="number"></span></td>
                    <td><span><label></label></span></td>
                </tr>
                <tr>
                    <td colspan="3"><span><input type="submit"><label>${error}</label></span></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%@ include file="../footer/footer.jsp" %>
<script src="/js/nameUnique.js"></script>
</body>
</html>