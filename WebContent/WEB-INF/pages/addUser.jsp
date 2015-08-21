<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/navigationBar.jsp" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>


<style type="text/css">
	@import url(common/homework.css);
	
	
    body,ul,li{
        padding: 0;
        margin: 0;
        list-style-type: none;
        font-size: 20px;
    }
</style>

<script src ="script/jquery-1.7.2.min.js" ></script>
<script type="text/javascript">
	$(function(){
		$(":submit").click(function(){
		
			var $name = $(":text[name='userName']") ;
			var $password = $(":password");

			var password = $password.val();
			var name = $name.val();
			
			name = $.trim(name);
			password = $.trim(password);
			
			$password.val(password);
			$name.val(name);
			
			
			if(name == ""){
				
				alert("请输入用户名");
				return false;
			}
			if(password == ""){
				alert("请输入密码");
				return false;
			}
			
			var length = $(":checkbox[name = 'userRole']:checked").length;
			if(length == 0){
				alert("请选择角色");
				return false;
			}
			
			
			else{
				alter("操作成功！");
				return true;
			}
			
			return false;
		});
		
	})

</script>



<body>
<div class = "content">
	<center>
		<br><br>
		
		<form action="manageServlet?method=addUser" method = "post">
			<table cellspacing="20">
				<tr>
					<td>用户名：</td>
					<td><input type = "text" name = "userName" value = "${requestScope.user.userName }"/></td>
				<tr>
				<tr>
					<td>密码：</td>
					<td><input type = "password" name = "userPassword" value = "${requestScope.user.userPassword }"></td>
				<tr>
			<tr>
					<td colspan = "2">赋予角色:</td>
				</tr>
			</table>
			
			<% int i = 1; %>
			<c:forEach items = "${requestScope.roles }" var = "role" >
				<input type = "checkbox"  name = "userRole" value = "${role.roleId }"/>${role.roleName } &nbsp;&nbsp;
				<% if(i%4 == 0){ out.print("<br>");}  i++; %>
			</c:forEach>
			
			
			<br><br>
			<input type = "submit" value = "确认添加"/>
		</form>

	</center>
</div>
</body>
</html>