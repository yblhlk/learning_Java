<%@page contentType="text/html; charset=utf-8" %>
<html>
<body>
<h2>欢迎使用历史AQI数据管理系统</h2>
<ul>
    <li><a target="children" href="/show">历史AQI管理</a> </li>
</ul>
<%-- 内联窗口 --%>
<iframe name="children" src="base.jsp" width="650px" height="600px"
        style="position: absolute; left: 170px; top: 50px"></iframe>
</body>
</html>
