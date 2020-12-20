<%--
  Created by IntelliJ IDEA.
  User: 3
  Date: 2020/10/10
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>出错啦</title>
    <link href="css/error.css" rel="stylesheet"/>
    <script language="JavaScript" src="js/error.js"></script>
</head>
<body>
<div id="errorDiv">
    <div id="errorHint">
        <%String param = (String)request.getAttribute("errors");
            out.println(param);%>
        <p id="errorInfo">${info}</p>
        <p><span id="leaveTime">10</span>秒后自动返回到登录界面</p>
        <p>不能跳转，请<a href="login.html">点击 这儿</a>
    </div>
</div>
</body>
</html>

