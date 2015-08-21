<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class = "head"></div>
	<div class = "left">
		<div id="menu">
			<ul>
			<c:forEach items = "${navigationBars}" var  = "navigationBar">
				
				
				<c:if test = "${navigationBar.key == 1 }">
					<li>权限管理
					   <ul>
						<c:forEach items = "${navigationBar.value }" var = "permission">
					   		<li><a href = "manageServlet?method=${permission.method }"> ${permission.permissionName }  </a></li>
						</c:forEach>
					   </ul>
					</li>
				
				</c:if>
				
				
				
				<c:if test = "${navigationBar.key == 2 }">
				
					<li>角色管理
					   <ul>
						<c:forEach items = "${navigationBar.value }" var = "permission">
					   		<li><a href = "manageServlet?method=${permission.method }"> ${permission.permissionName }  </a></li>
						</c:forEach>
					   </ul>
					</li>
				
				</c:if>
				
				
				
				<c:if test = "${navigationBar.key == 3 }">
				
					<li>人员管理
					   <ul>
						<c:forEach items = "${navigationBar.value }" var = "permission">
					   		<li><a href = "manageServlet?method=${permission.method }"> ${permission.permissionName }  </a></li>
						</c:forEach>
					   </ul>
					</li>
				
				</c:if>
				
				
				
				<c:if test = "${navigationBar.key == 4 }">
					<li>栏目管理
					   <ul>
						<c:forEach items = "${navigationBar.value }" var = "permission">
					   		<li><a href = "manageServlet?method=${permission.method }"> ${permission.permissionName }  </a></li>
						</c:forEach>
					   </ul>
					</li>
				
				</c:if>
				
				
				
				<c:if test = "${navigationBar.key == 5 }">
					<li>测试
					   <ul>
						<c:forEach items = "${navigationBar.value }" var = "permission">
					   		<li><a href = "manageServlet?method=${permission.method }"> ${permission.permissionName }  </a></li>
						</c:forEach>
					   </ul>
					</li>
				
				</c:if>
				
			</c:forEach>
			
			</ul>

		</div>

	</div>
	

</body>
</html>