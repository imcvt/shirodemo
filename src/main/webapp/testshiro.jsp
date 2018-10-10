<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>testshiro</title>
</head>
<body>

    <shiro:hasPermission name="user:manage">
        <a href="manageUsers.jsp">
            点击进入管理界面
        </a>
        aaa
    </shiro:hasPermission>
    <shiro:lacksPermission name="user:manage">
        用户[<shiro:principal />]没有管理权限
    </shiro:lacksPermission>

    <shiro:hasAnyRoles name="manager,admin,user">
        用户[<shiro:principal/>]拥有角色manager或admin或user<br/>
    </shiro:hasAnyRoles>

    <shiro:lacksRole name="abc">
        用户[<shiro:principal/>]没有角色abc<br/>
    </shiro:lacksRole>

    <shiro:notAuthenticated>
        未身份验证（包括记住我）
    </shiro:notAuthenticated>

    <shiro:authenticated>
        用户[<shiro:principal/>]已身份验证通过
    </shiro:authenticated>
</body>
</html>
