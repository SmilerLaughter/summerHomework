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
		
			var $name = $(":text[name='roleName']") ;
			var name = $name.val();
			name = $.trim(name);
			$name.val(name);
			
			if(name == ""){
				alert("请输入角色名！");
				return false;
			}
			
			var length = $(":checkbox[name = 'rolePermissions']:checked").length;
			if(length == 0){
				alert("请选择权限");
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
	
	<font color = "red">${requestScope.error }</font>
		<form action="manageServlet?method=addRole" method = "post">
		
			<table cellspacing="20">
				<tr>
					<td>角色名：</td>
					<td><input type = "text" name="roleName" value = "${requestScope.role.roleName }"/></td>
				</tr>
				<tr>
					<td>说明：</td>
					<td><textarea rows="5" cols="14" name = "roleNote" >${requestScope.role.roleNote} </textarea></td>
				</tr>
				
				<tr>
					<td colspan = "2">赋予权限:</td>
				</tr>
			</table>
			
			<% int i = 1; %>
			<c:forEach items = "${requestScope.permissions }" var = "permission" >
				<c:set var = "flag" value = "false"></c:set>
				<c:forEach items = "${requestScope.rolePermissions }" var = "rolePermission">
					<c:if test="${permission.permissionId == rolePermission }">
						<c:set var = "flag" value = "true"></c:set>
					</c:if>
				</c:forEach>
				
			
				<c:if test="${flag == true }">
					<input type = "checkbox" name = "rolePermissions" value = "${permission.permissionId }" checked = "checked"/>${permission.permissionName } &nbsp;&nbsp;
				</c:if>
				<c:if test="${flag == false }">
					<input type = "checkbox" name = "rolePermissions" value = "${permission.permissionId }"/>${permission.permissionName } &nbsp;&nbsp;
				</c:if>
				
				<% if(i%4 == 0){ out.print("<br>");}  i++; %>
			</c:forEach>
			
			
			<br><br>
			<input type = "submit" value = "确认添加"/>
		</form>
	</center>
	
</div>	
</body>
</html>