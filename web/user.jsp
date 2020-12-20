<%--
  Created by IntelliJ IDEA.
  User: 3
  Date: 2020/10/11
  Time: 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>系统主页</title>
    <link href="css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="container">
    <div id="header">
        <div id="rightTop">
            当前用户：<span>${chrName}</span> &nbsp;[<a href="login.html">安全退出</a>]
        </div>
        <div id="menu">
            <ul>
                <li><a href="main.jsp">首页</a></li>
                <li class="menuDiv"></li>
                <li><a href="download.jsp">下载</a></li>
                <li class="menuDiv"></li>
                <li><a href="userManage.jsp">用户管理</a></li>
                <li class="menuDiv"></li>
                <li><a href="downloadManage.jsp">资源管理</a></li>
                <li class="menuDiv"></li>
                <li><a href="user.jsp">个人中心</a></li>
            </ul>
        </div>
        <div id="banner">
        </div>
        <br><br>
        <p>建设中</p>
    </div>
</div>
</body>

</html>
