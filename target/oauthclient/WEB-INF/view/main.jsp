<%--
  Created by IntelliJ IDEA.
  User: wxyuan
  Date: 2018/8/4
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
 欢迎您！<%=request.getAttribute("user_name") %><br>
 access_token=<%=request.getAttribute("access_token") %><br>
 token_type=<%=request.getAttribute("token_type") %><br>
 scope=<%=request.getAttribute("scope") %>
</body>
</html>
