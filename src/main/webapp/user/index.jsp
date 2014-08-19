<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/common/js.jsp"%>
<script type="text/javascript">
	function formatDate(value, row, index) {
		if (value == null)
			return "";

		var myDate = new Date(value);
		return formatterDateTime(myDate);
	}
	//新建用户
	//打开新建面板
	function newUser() {
		$('#dlg').dialog('open').dialog('setTitle', '新建用户');
		$('#fm').form('clear');
		url = 'user/addUser.do';
	}
	//提交保存请求
	function saveUser() {
		$('#fm').form('submit', {
			url : 'user/addUser.do',
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.error) {
					$.messager.show({
						title : 'Error',
						msg : result.message
					});
				} else {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			}
		});
	}
</script>
</head>
<body>
	
	<table id="dg" class="easyui-datagrid" title="用户列表"
		style="width: 700px; height: 350px"
		data-options="singleSelect:true,collapsible:true,
		pagination:true,pageSize:10,pageList: [10, 15, 20, 25, 30],
		url:'UserServlet?method=getUserList',method:'get',
		toolbar:'#tb'
		">
		<thead>
			<tr>
				<th data-options="field:'username',width:80">用户名</th>
				<th data-options="field:'empno',width:100">工号</th>
				<th data-options="field:'role',width:80,align:'right'">角色</th>
				<th data-options="field:'lastModifyDate',width:180,align:'right',formatter:formatDate">更新日期</th>
				<th data-options="field:'createDate',width:180,formatter:formatDate">创建日期</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">用户信息:</div>
		<form id="fm" method="post">
			<div class="fitem">
				<label>用户名:</label> <input name="username"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>密码:</label> <input name="password" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>邮箱:</label> <input name="Email" class="easyui-validatebox"
					validType="email">
			</div>
			<div class="fitem">
				<label>QQ:</label> <input name="qq">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveUser()">Save</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>

	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="javascript:newUser()">Add</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"
			onclick="javascript:alert('Cut')">Delete</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-save" plain="true"
			onclick="javascript:alert('Save')">Save</a>
	</div>
</body>
</html>

