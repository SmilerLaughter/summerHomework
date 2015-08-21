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
		
			var $name = $(":text[name='permissionName']") ;
			var $method = $(":text[name='method']");
			
			var method = $method.val();
			var name = $name.val();
			
			method = $.trim(method);
			name = $.trim(name);
			
			$method.val(method);
			$name.val(name);
			
			if(name == ""){
				
				alert("请输入权限名！");
				return false;
			}
			if(method == ""){
				alert("请输入方法！");
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
	<font color = "red">${error }</font>
		<form action="manageServlet?method=updatePermission&permissionId=${permission.permissionId }" method = "post">
			<table cellspacing="20">
				<tr>
					<td>权限名：</td>
					<td><input type = "text" name="permissionName" value = "${requestScope.permission.permissionName }"/></td>
				</tr>
				
				<tr>
					<td>方法：</td>
					<td><input type = "text" name="method" value = "${requestScope.permission.method }"/></td>
				</tr>
				<tr>
					<td>说明：</td>
					<td><textarea rows="5" cols="14" name = "permissionNote" >${requestScope.permission.permissionNote} </textarea></td>
				</tr>
				
				<tr >
					<td colspan = "2"><input type = "submit" value = "确认修改"/></td>
				</tr>
			
			</table>
		
		
		</form>
	
	</center>
</div>
</body>
</html>