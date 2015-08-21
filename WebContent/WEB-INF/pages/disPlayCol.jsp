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


<body>
<div class = "content">
<h4> 共有 ${requestScope.allLevle } 级栏目</h4>

	<ul>
		<c:forEach items = "${requestScope.cols }" var = "map1">
				<li>- ${map1.key }
				
				<c:forEach items = "${map1.value }" var = "listMap1">
					<c:forEach items = "${ listMap1}" var = "map2">
					<ul>
						<li>- ${map2.key }
							
								<c:forEach items = "${map2.value }" var = "listMap2">
									<c:forEach items = "${ listMap2 }" var = "map3">
									<ul>
										<li>- ${map3.key }
										
											<c:forEach items = "${map3.value }" var = "listMap3">
											<c:forEach items = "${ listMap3 }" var = "map4">
											<ul>
												<li>- ${map4.key }
												
													<c:forEach items = "${map4.value }" var = "listMap4">
													<c:forEach items = "${ listMap4 }" var = "map5">
													<ul>
														<li>- ${map5.key }
														
															<c:forEach items = "${map5.value }" var = "listMap5">
															<c:forEach items = "${ listMap5 }" var = "map6">
															<ul>
																<li>- ${map6.key }
																
																	<c:forEach items = "${map6.value }" var = "listMap6">
																	<c:forEach items = "${ listMap6 }" var = "map7">
																	<ul>
																		<li>- ${map7.key }
																		
																		<c:forEach items = "${map7.value }" var = "listMap7">
																		<c:forEach items = "${ listMap7 }" var = "map8">
																		<ul>
																			<li>- ${map8.key }
																			
																			<c:forEach items = "${map8.value }" var = "listMap8">
																			<c:forEach items = "${ listMap8 }" var = "map9">
																			<ul>
																				<li>- ${map9.key }</li>
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
	

</div>
</body>
</html>