<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.login{
		margin-top:400px;
		margin-left: 1000px;
		
	}	
</style>


<script src ="script/jquery-1.7.2.min.js" ></script>
<script type="text/javascript">
	$(function(){
		$(":submit").click(function(){
		
			var $name = $(":text[name='userId']") ;
			var name = $name.val();
			name = $.trim(name);
			$name.val(name);
			
			if(name == ""){
				
				alert("请输入账号！");
				return false;
			}else{
				return true;
			}
			
			return false;
		});
		
	})
</script>


</head>
<body background="imag/11.jpg">
	

<div class = "login">
	<br><br><br>
	<font color= "red">${requestScope.errors }</font>
	<form action="manageServlet?method=login"  method = "post">
		账号：<input type = "text" name = "userId" ><br><br>
		密码：<input type = "text" name = "userPassword" ><br><br>
		<input type = "submit"  value = "登录">
 	
	</form>
</div>	
</body>
</html>