<!-- c标签选下面的包 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: wang'ya'lin
  Date: 2024/6/21
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>历史AQI管理</h2>
    <a href="add.jsp">新增AQI数据</a>
    <table border="1" cellspacing="0"
           cellpadding="10" width="450px">
        <tr>
            <th>日期</th>
            <th>质量等级</th>
            <th>AQI指数</th>
            <th>当天AQI排名</th>
            <th>PM2.5</th>
            <th>PM10</th>
            <th>So2</th>
            <th>No2</th>
            <th>Co</th>
            <th>O3</th>
            <th>操作</th>
        </tr>
        <!--循环，用于在 JSP 页面上迭代集合或数组。-->
        <c:forEach items="${list}" var="p">
            <tr>
                <td>${p.date}</td>
                <td>${p.lv}</td>
                <td>${p.AQI}</td>
                <td>${p.sort}</td>
                <td>${p.PM25}</td>
                <td>${p.PM10}</td>
                <td>${p.so2}</td>
                <td>${p.no2}</td>
                <td>${p.co}</td>
                <td>${p.o3}</td>
                <td><a href="/delete?date=${p.date}">删除</a> </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>


