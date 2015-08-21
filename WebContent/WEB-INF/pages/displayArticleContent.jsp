<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/navigationBar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<style type="text/css">
	@import url(common/homework.css);
	
	
    body,ul,li{
        padding: 0;
        margin: 0;
        list-style-type: none;
        font-size: 20px;
    }
</style>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class = "content">	
	<center>
		<h4>${requestScope.article.articleName }</h4><br><br>
	</center>
		
		<div style="width: 800px;margin: auto">
		
		${requestScope.article.content }
		
		</div>
	
</div>	
</body>
</html>