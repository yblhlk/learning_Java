<%--
  Created by IntelliJ IDEA.
  User: wang'ya'lin
  Date: 2024/6/21
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>历史AQI数据管理</title>
</head>
<body>
    <h2>数据</h2>
    <form action="/add" method="post">
        <p>日期<input name="date"/></p>
        <p>质量等级<input name="lv"/></p>
        <p>AQI指数<input name="AQI"/></p>
        <p>当天AQI排名<input name="sort"/></p>
        <p>PM2.5<input name="PM25"/></p>
        <p>PM10<input name="PM10"/></p>
        <p>So2<input name="so2"/></p>
        <p>No2<input name="no2"/></p>
        <p>Co<input name="co"/></p>
        <p>O3<input name="o3"/></p>
        <button>新增</button>
    </form>
</body>
</html>
