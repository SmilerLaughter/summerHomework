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
	
	
  	
    body{
        padding: 0;
        margin: 0;
        list-style-type: none;
        font-size: 20px;
    }
    
    ul,li{
    
    list-style-type: none;
    }
    
    .content ul,li{
    	 list-style-type: none;
    }
</style>


<script src ="script/jquery-1.7.2.min.js" ></script>
<script type="text/javascript">
	$(function(){
		$(":submit").click(function(){
		
			var length = $(":radio[name = 'articleCol']:checked").length;
			if(length == 0){
				alert("请选择栏目");
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
	<form action = "manageServlet?method=updateColOfArticle&articleId=${param.articleId }" method = "post">
	<ul>
		<c:forEach items = "${requestScope.cols }" var = "map1">
				<li> <input type = "radio" name = "articleCol" value = "${map1.key }"/>${map1.key }
				
				<c:forEach items = "${map1.value }" var = "listMap1">
					<c:forEach items = "${ listMap1}" var = "map2">
					<ul>
						<li><input type = "radio" name = "articleCol" value = "${map2.key }"/> ${map2.key }
							
								<c:forEach items = "${map2.value }" var = "listMap2">
									<c:forEach items = "${ listMap2 }" var = "map3">
									<ul>
										<li><input type = "radio" name = "articleCol" value = "${map3.key }"/> ${map3.key }
										
											<c:forEach items = "${map3.value }" var = "listMap3">
											<c:forEach items = "${ listMap3 }" var = "map4">
											<ul>
												<li><input type = "radio" name = "articleCol" value = "${map4.key }"/> ${map4.key }
												
													<c:forEach items = "${map4.value }" var = "listMap4">
													<c:forEach items = "${ listMap4 }" var = "map5">
													<ul>
														<li><input type = "radio" name = "articleCol" value = "${map5.key }"/>${map5.key }
														
															<c:forEach items = "${map5.value }" var = "listMap5">
															<c:forEach items = "${ listMap5 }" var = "map6">
															<ul>
																<li><input type = "radio" name = "articleCol" value = "${map6.key }"/> ${map6.key }
																
																	<c:forEach items = "${map6.value }" var = "listMap6">
																	<c:forEach items = "${ listMap6 }" var = "map7">
																	<ul>
																		<li><input type = "radio" name = "articleCol" value = "${map7.key }"/>${map7.key }
																		
																		<c:forEach items = "${map7.value }" var = "listMap7">
																		<c:forEach items = "${ listMap7 }" var = "map8">
																		<ul>
																			<li><input type = "radio" name = "articleCol" value = "${map8.key }"/> ${map8.key }
																			
																			<c:forEach items = "${map8.value }" var = "listMap8">
																			<c:forEach items = "${ listMap8 }" var = "map9">
																			<ul>
																				<li><input type = "radio" name = "articleCol" value = "${map9.key }"/> ${map9.key }</li>
																			</ul>
																			</c:forEach>
																			</c:forEach>
																							
																			
																			</li>
																		</ul>
																		</c:forEach>
																		</c:forEach>
																		
																		</li>
																	</ul>
																	</c:forEach>
																	</c:forEach>
																
																</li>
															</ul>
															</c:forEach>
															</c:forEach>
														
														</li>
													</ul>
													</c:forEach>
													</c:forEach>
												
												
												</li>
											</ul>
											</c:forEach>
											</c:forEach>
												
										
										
										</li>
									</ul>
									</c:forEach>
								</c:forEach>
							
							
							</li>
						</ul>
					</c:forEach>
				</c:forEach>
			 
				 </li>
		</c:forEach>
	</ul>
	
	<input type = "submit" value = "修改"/>

 </form>

</div>
</body>
</html>