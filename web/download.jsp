<%--
  Created by IntelliJ IDEA.
  User: 3
  Date: 2020/10/10
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" import="vo.download" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>资源下载</title>
    <link rel="stylesheet" type="text/css" href="css/download.css">
</head>
<body>
<h1>资源下载</h1>
<div class="container">

    <c:forEach items="${list}" var="download" varStatus="l">
        <ul>
            <li>
                <p class="name">${download.getName()}</p>
                <div class="pic-txt">
                    <img class="img-area" src="${download.getImage()}" />
                    <p class="txt-area">
                        <span class="">${download.getDescription()}</span>
                        <span class="tit-sub">
									<i class="space">时间：${download.getTime()}</i>
									<i class="space">大小：${download.getSize()}kb</i>
									<i>星级：</i>
									<i class="stars">
										<span style="width:${download.getStar()}/0.05%"></span>
									</i>
								</span>
                    </p>
                </div>
                <a class="dl-btn" href="downloadServlet?id=${download.id}" title="点击下载">下载</a>
            </li>
        </ul>
    </c:forEach>

    <p class="back"><a href="main.jsp">返回首页</a></p>
</div>
</body>

</html>