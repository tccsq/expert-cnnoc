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
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css href="script/assets/main.css">
<LINK rel=stylesheet type=text/css href="script/assets/easyui.css">
<LINK rel=stylesheet type=text/css href="script/assets/icon.css">
<LINK rel=stylesheet type=text/css href="script/assets/prettify.css">
<style type="text/css">
.item-img {
	display: inline-block;
	vertical-align: middle;
	width: 16px;
	height: 16px;
}

.item-text {
	display: inline-block;
	vertical-align: middle;
	padding: 3px 0 3px 3px;
}
</style>
<SCRIPT type=text/javascript src="script/assets/prettify.js"></SCRIPT>
<SCRIPT type=text/javascript src="script/assets/jquery-1.8.0.min.js"></SCRIPT>
<SCRIPT type=text/javascript src="script/assets/jquery.easyui.min.js"></SCRIPT>
<script type="text/javascript">
	function reloadcheckcode(img) {
		img.src = "LoginServlet?method=checkcode&temp=”" + Math.random();
	}
	function login() {
		var username = $('#username').attr('value');
		var pwd = $('#pwd').attr('value');
		var checkcode = $('#checkcode').attr('value');
		$('#errormsg').text("");
		var param = "username=" + username + "&password=" + pwd + "&checkcode="
				+ checkcode;

		if (username != "" && pwd != "" && checkcode != "") {
			$('#errormsg').text("");
			$.ajax({
				type : "POST",
				url : "LoginServlet",
				dataType : 'json',
				data : param,
				async : false,
				success : function(data) {
					if (data.status == "error") {
						$('#errormsg').text("");
						$('#errormsg').text(data.msg);
					} else {
						window.location = "main.jsp"
					}
				},
				error : function(msg) {
					$('#errormsg').text("");
					$('#errormsg').text("程序异常：" + msg.responseText);
				}
			});

		} else
		/* 	if (checkcode == "") {
			$('#errormsg').text("");
			$('#errormsg').text("验证码不能为空！");
		} else  */{
			$('#errormsg').text("");
			$('#errormsg').text("用户名、密码不能为空！");
		}
	}
</script>
</head>
<body>
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: 300px; padding: 10px 30px;" title="用户登录"
		buttons="#dlg-buttons">
		<div style="padding-left: 50px;">
			<h2>专家系统</h2>
		</div>
		<form id="ff" method="post" style="padding: 5px 50px;">
			<table>
				<tr>
					<td>用户名:</td>
					<td><input type="text" name="username" id="username"
						style="width: 150px;" /></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input type="text" name="pwd" id="pwd"
						style="width: 150px;" /></td>
				</tr>
				<tr>
					<td>角色:</td>
					<td><select name="role" id="role">
							<option value="1">普通员工</option>
							<option value="2">管理员</option>
					</select></td>
				</tr>
				<tr>
					<td>验证码:</td>
					<td><input name="checkcode" type="text" id="checkcode"
						maxlength="4" class="chknumber_input" /> <img
						src="LoginServlet?method=checkcode" id="safecode"
						onclick="reloadcheckcode(this)" title="如果看不清，请点击本图片换一张" /></td>

				</tr>
				<tr style="width: 100px;">
					<span id="errormsg" style="color: red"></span>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons" style="padding-right: 30px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="login()">确定</a>
		<button onclick="login()">确定</button>
	</div>
</body>
</html>