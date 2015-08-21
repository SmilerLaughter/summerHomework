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
	
		$(".delete").click(function(){
			var $tr = $(this).parent().parent();
			var name = $.trim($tr.find("td:first").text());
			var flag = confirm("确定要删除" + name + "的信息吗？");
			if(flag){
				return true;
			}
			return false;
			
			
		});
		
	})
</script>

<body>

<div class = "content">
	<center>
	
	<font color = "red"> ${requestScope.sucess }</font>
	<table cellspacing=" 20">
		<tr>
			<th>文章名<th>
			<th colspan = "3">操作<th>
		</tr>
	
	
		<c:forEach items = "${requestScope.articles }" var = "article">
		<tr>
			<td><a href = "manageServlet?method=displayArticle&articleId=${article.articleId }">${article.articleName }</a></td>
			<td><a href = "manageServlet?method=goToUpdateArticle&articleId=${article.articleId }">修改</a></td>
			<td><a href = "manageServlet?method=goToUpdateColOfArticle&articleId=${article.articleId }&colId=${article.colId}">更改所属栏目</a></td>
			<td><a href = "manageServlet?method=deleteArticle&articleId=${article.articleId }&colId=${article.colId}" class = "delete">删除</a></td>
		</tr>
		</c:forEach>
	</table>
	</center>
</div>	
</body>
</html>