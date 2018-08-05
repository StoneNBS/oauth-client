<%--
  Created by IntelliJ IDEA.
  User: wxyuan
  Date: 2018/8/4
  Describe:登陆页面，点击超链接，访问认证服务器，发出授权请求
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="login" method="get">
    <h3>第三方应用授权</h3>
    <%-- 点击超链接，访问认证服务器，发出授权请求，对应授权码模式中的步骤（A） --%>
    <a href="http://localhost:8080/authorize?response_type=code&client_id=githubClientId&redirect_uri=http://localhost:8001/main">QQ登陆</a>
</form>
</body>
</html>
