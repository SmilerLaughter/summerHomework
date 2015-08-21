<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


</head>
<body>
	


	<a href= "manageServlet?method=goToAddPermission">新增权限</a>
	<a href = "manageServlet?method=queryForPermission">显示权限</a>
	
	
	<br><br>
	
	<a href = "manageServlet?method=goToAddRole">创建角色</a>
	<a href = "manageServlet?method=queryForRole"> 显示角色</a>

	<br><br>
	
	<a href = "manageServlet?method=goToAddUser">新增人员</a>
	<a href = "manageServlet?method=queryForUser">查看人员</a>
	
	
	<br><br>
	<a href = "manageServlet?method=goToAddCol">新增根栏目</a>
	<a href = "manageServlet?method=handleCol">操作栏目</a>
	<a href = "manageServlet?method=displayCol">显示所有栏目</a>
</body>
</html>