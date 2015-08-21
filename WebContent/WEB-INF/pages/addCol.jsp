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
		
			var $name = $(":text[name='colName']") ;
			var name = $name.val();
			name = $.trim(name);
			$name.val(name);
			
			if(name == ""){
				alert("请输入栏目名！");
				return false;
			}
			return true;
		});
	})
			
</script>			
<body>
<div class = "content">	
	<center>
		<h4>创建根栏目</h4>
		
		<font color = "red">${requestScope.error }</font>
		<form action = "manageServlet?method=addCol" method = "post">
			<table>
				<tr>
					<td>栏目名：</td>
					<td><input type = "text" name = "colName" value = "${requestScope.colName }" /></td>
				</tr>
			</table>
			
			<input type = "submit" value="添加"/>
		
		</form>
	
	</center>
</div>
</body>
</html>