<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>CMS 后台管理工作平台</title>
		<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}

.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}

.STYLE6 {
	color: #000000;
	font-size: 12;
}

.STYLE10 {
	color: #000000;
	font-size: 12px;
}

.STYLE19 {
	color: #344b50;
	font-size: 12px;
}

.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}

.STYLE22 {
	font-size: 12px;
	color: #295568;
}

A:active,A:visited,A:link {
	COLOR: #0629FD;
	TEXT-DECORATION: none
}

A:hover {
	COLOR: #FF6600;
	TEXT-DECORATION: none
}

A.relatelink:active,A.relatelink:visited,A.relatelink:link {
	color: white;
	TEXT-DECORATION: none
}

A.relatelink:hover {
	COLOR: #FF6600;
	TEXT-DECORATION: none
}

td {
	font-size: 12px;
	color: #003366;
	height: 24px
}

.STYLE1 a {
	COLOR: white;
}

.STYLE1 A:active,.STYLE1 A:visited,.STYLE1 A:link {
	COLOR: white;
	TEXT-DECORATION: none
}

.STYLE1 a:hover {
	COLOR: red;
}
-->
</style>
		<script type="text/javascript">
function selectAll(){
	var check = document.getElementById("checkbox");
	var ids = document.getElementsByName("id");
	if(check.checked){
		for(var i=0;i<ids.length;i++){
			ids[i].checked = true;
		}
	}else{
		for(var i=0;i<ids.length;i++){
			ids[i].checked = false;
		}
	}
}

function delAll(){
	var url = "ArticleServlet?delArticle"; 
	var ids = document.getElementsByName("id");
	var checkIds = [];
	
	for(var i = 0; i<ids.length;i++){
		if(ids[i].checked){
			checkIds[checkIds.length] = ids[i].value;
		}
	}
	for(var j = 0;j<checkIds.length;j++){
		url = url + "&id="+checkIds[j];
	}
	window.location = url;
	
}

</script>
	</head>
	<body>
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="30">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="24" bgcolor="#353c44">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="6%" height="19" valign="bottom">
														<div align="center">
															<img src="images/tb.gif" width="14" height="14" />
														</div>
													</td>
													<td width="94%" valign="bottom">
														<span class="STYLE1"> 网站文章信息列表</span>
													</td>
												</tr>
											</table>
										</td>
										<td>
											<div align="right">
												<span class="STYLE1"> &nbsp;&nbsp;<img src="images/add.gif" width="10" height="10" /> <a href="article/add_article.jsp">添加</a> &nbsp;&nbsp;
												<img src="images/edit.gif" width="10" height="10" /> 
												<a href="#">发布</a> &nbsp; <img src="images/del.gif" width="10" height="10" /> <a href="javascript:delAll()">删除</a>&nbsp;&nbsp; &nbsp; </span><span class="STYLE1"> &nbsp;</span>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<form action="ArticleServlet" method="post">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="right">
									文章标题：
								</td>
								<td width="160px">
									<input type="text" name="title" value="${param.title }">
								</td>
								<td align="right">
									创建日期：
								</td>
								<td width="160px">
									<input type="text" name="createTime" value="${param.createTime }">
								</td>
								<td>
									<input type="submit" name="submit" value="查询">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						bgcolor="#a8c7ce">
						<tr>
							<td width="4%" height="20" bgcolor="d3eaef" class="STYLE10">
								<div align="center">
									<input type="checkbox" name="checkboxHeader" id="checkbox"
										onclick="selectAll()" />
								</div>
							</td>
							<td width="100" height="20" bgcolor="d3eaef" class="STYLE6">
								<div align="center">
									<span class="STYLE10">文章标题</span>
								</div>
							</td>
							<td width="50" height="20" bgcolor="d3eaef" class="STYLE6">
								<div align="center">
									<span class="STYLE10">文章来源</span>
								</div>
							</td>
							<td width="50" height="20" bgcolor="d3eaef" class="STYLE6">
								<div align="center">
									<span class="STYLE10">点击量</span>
								</div>
							</td>
							<td width="50" height="20" bgcolor="d3eaef" class="STYLE6">
								<div align="center">
									<span class="STYLE10">留言数</span>
								</div>
							</td>
							<td width="70" height="20" bgcolor="d3eaef" class="STYLE6">
								<div align="center">
									<span class="STYLE10">创建时间</span>
								</div>
							</td>
							<td width="70" height="20" bgcolor="d3eaef" class="STYLE6">
								<div align="center">
									<span class="STYLE10">更新时间</span>
								</div>
							</td>
							<td width="70" height="20" bgcolor="d3eaef" class="STYLE6">
								<div align="center">
									<span class="STYLE10">发布时间</span>
								</div>
							</td>
							<td width="100" height="20" bgcolor="d3eaef" class="STYLE6">
								<div align="center">
									<span class="STYLE10">基本操作</span>
								</div>
							</td>
						</tr>
						<c:if test="${not empty pv.datas}">
							<c:forEach items="${pv.datas}" var="a">
								<tr>
									<td height="20" bgcolor="#FFFFFF">
										<div align="center">
											<input type="checkbox" name="id" value="${a.id}" />
										</div>
									</td>
									<td height="20" bgcolor="#FFFFFF" class="STYLE19">
										<div align="center">
											<a href="ArticleServlet?method=loadArticle&id=${a.id}" title="点击查看和编辑文章">${a.title }</a>
										</div>
									</td>
									<td height="20" bgcolor="#FFFFFF" class="STYLE19">
										<div align="center">
											http://www.leadfar.org
										</div>
									</td>
									<td height="20" bgcolor="#FFFFFF" class="STYLE19">
										<div align="center">
											20
										</div>
									</td>
									<td height="20" bgcolor="#FFFFFF" class="STYLE19">
										<div align="center">
											5
										</div>
									</td>
									<td height="20" bgcolor="#FFFFFF" class="STYLE19">
										<div align="center">
											${a.createTime }
										</div>
									</td>
									<td height="20" bgcolor="#FFFFFF" class="STYLE19">
										<div align="center">
											${a.updateTime }
										</div>
									</td>
									<td height="20" bgcolor="#FFFFFF" class="STYLE19">
										<div align="center">
											${a.deployTime }
										</div>
									</td>
									<td height="20" bgcolor="#FFFFFF">
										<div align="center" class="STYLE21">
											<a href="#" title="点击发布文章">发布</a> |
											<a href="ArticleServlet?method=delArticle&id=${a.id}" title="点击删除文章">删除</a>
											|
											<a href="ArticleServlet?method=loadArticle&id=${a.id}" title="点击编辑文章">编辑</a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty pv.datas}">
							<tr>
								<td height="20" colspan="9" bgcolor="#FFFFFF" class="STYLE19">
									<div align="center">
										没有文章可以显示
									</div>
								</td>
							</tr>
						</c:if>
					</table>
					
				</td>
			</tr>
			<tr>
				<td height="30">
					
					<jsp:include page="/backend/common/pager.jsp">
						<jsp:param name="url" value="ArticleServlet"/>
						<jsp:param name="params" value="title,createTime"/>
					</jsp:include>
					
				</td>
			</tr>
		</table>
	</body>
</html>

