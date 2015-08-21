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
		$("#pageNo").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			
			//1. 校验 val 是否为数字 1, 2, 而不是 a12, b
			
			var reg = /^\d+$/g;
		
			if(!reg.test(val)){
			
				alert("输入的页面不合法");
				$(this).val("");
				return ;
			}
				//2. 校验 val 在一个合法的范围内
				var pageNo = parseInt(val);
				if(pageNo < 1 || pageNo > parseInt("${page.totalPageNumber}")){
					alert("输入的不是合法的页码");
					$(this).val("");
					return ;
				}
				
				
				//3. 页面跳转
				var href = "manageServlet?method=handleCol&pageNo=" + pageNo ;
				
				window.location.href = href;

		});
		

		$(".delete").click(function(){
			var $tr = $(this).parent().parent();
			var name = $.trim($tr.find("td:first").text());
			var flag = confirm("删除后栏目的文章一并删除,确定要删除" + name + "的信息吗？");
			if(flag){
				return true;
			}
			return false;
			
			
		});
		
	})
</script>

<body>
<div class = "content">
	<br><br>
<center>
	<font color = "red"> ${requestScope.error  } ${requestScope.succe  } ${requestScope.noFiles }</font>
	<table cellspacing="20">
		<tr>
			<th>栏目名</th>
			<th>栏目级别</th>
			<th>上一级栏目</th>
			<th>文章数</th>
			<th colspan = "4">操作</th>
		</tr>
		
		<c:forEach items = "${ requestScope.page.pageList}" var = "col">
		<tr>
			<td>${col.colName }</td>
			<td>${col.colLevel }</td>
			<td>${col.parentName }</td>
			<td><a href = "manageServlet?method=goToHandleArticle&colId=${col.colId }&pageNo=${page.pageNo}"> ${col.articleNum }</a></td>
			<td><a href = "manageServlet?method=goToaddChildCol&parentIdOfChild=${col.colId }">添加子栏目</a></td>
			<td ><a href = "manageServlet?method=deleteCol&pageNo=${page.pageNo }&colId=${col.colId}" class = "delete">删除</a></td>
			<td><a href = "manageServlet?method=goToAddArticle&colId=${col.colId }">添加文章</a></td>
		
		</tr>
		</c:forEach>
	
	
	</table>

	
			<br><br>
			
			
			共 ${page.totalPageNumber } 页  &nbsp; &nbsp;
			当前第${page.pageNo }页 &nbsp;&nbsp;
			<c:if test="${page.hasPrev }">
				<a href= "manageServlet?method=handleCol&pageNo=1">首页</a>&nbsp;&nbsp;
				<a href = "manageServlet?method=handleCol&pageNo=${page.prePage }">上一页</a>&nbsp;&nbsp;
			</c:if>
			
			<c:if test="${page.hasNext }">
				<a href = "manageServlet?method=handleCol&pageNo=${ page.nextPage}">下一页</a>&nbsp;&nbsp;
				<a href = "manageServlet?method=handleCol&pageNo=${page.totalPageNumber }">末页</a>&nbsp;&nbsp;
			
			</c:if>&nbsp;&nbsp;
			转到<input type = "text" size = "1" id = "pageNo" name = "pageNo"/> 页		
	
	
</center>
</div>

</body>
</html>