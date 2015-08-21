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
		
			var $name = $(":text[name='childColName']") ;
			var name = $name.val();
			name = $.trim(name);
			$name.val(name);
			
			if(name == ""){
				alert("请输入栏目名！");
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
	<h3>添加子栏目</h3>
	<font color = "red"> ${requestScope.error } </font>
	<form action="manageServlet?method=addChild&parentIdOfChild=${param.parentIdOfChild }" method = "post">
	栏目名：<input type = "text" name = "childColName" value = "${requestScope.childName }"/><br>
	<input type = "submit" value = "添加"/>

	</form>

</center>
</div>
</body>
</html>