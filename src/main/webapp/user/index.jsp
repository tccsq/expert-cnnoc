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
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
<script type="text/javascript">
	function formatDate(value, row, index) {
		if (value == null)
			return "";
		var myDate = new Date(value);
		return formatterDateTime(myDate);
	}

	var url;
	//新建用户
	//打开新建面板
	function newUser() {
		$('#dlg').dialog('open').dialog('setTitle', '新建用户');
		$('#fm').form('clear');
		url = 'UserServlet?method=addUser';
	}

	function editUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑用户信息');
			$('#fm').form('load', row);
			//给角色下拉框赋值
			var roleVal;
			if(row.role == "USER")
				roleVal = 0;
			else if(row.role =="ADMIN")
				roleVal = 1;
			var data = $('#role').combobox('getData');
			$('#role').combobox("select", data[roleVal].value);
			
			url = 'UserServlet?method=updateUser';
		} else {
			alert("请选择要编辑的用户");
		}
	}

	function delUser() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认', '确认删除这个用户信息吗?', function(r) {
				if (r) {
					//url = 'UserServlet?method=delUser';
					$.ajax({
						url : "UserServlet?method=delUser&id="+row.id,
						type: "post",
						success : function(data,status,response) {
							var result = eval('(' + response.responseText + ')');
							if (result.status == "error") {
								$.messager.show({
									title : 'Error',
									msg : result.msg
								});
							} else {
								$.messager.show({
									title : 'Success',
									msg : result.msg
								});
								$('#dg').datagrid('reload'); 
							} 
						}
					});
				}
			});
		}
	}
	//提交保存请求
	function saveUser() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.status == "error") {
					$.messager.show({
						title : 'Error',
						msg : result.msg
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
		fit="true"
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
				<th
					data-options="field:'lastModifyDate',width:180,align:'right',formatter:formatDate">更新日期</th>
				<th data-options="field:'createDate',width:180,formatter:formatDate">创建日期</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">用户信息:</div>
		<form id="fm" method="post">
			<input name="id" type="hidden" value="">
			<div class="fitem">
				<label>用户名:</label> <input name="username"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>密码:</label> <input name="password" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>工号:</label> <input name="empno" class="easyui-validatebox"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>角色:</label> 
				<select id="role" name="role" class="easyui-combobox">
					<option value="0" selected="selected">普通用户</option>
					<option value="1">管理员</option>
				</select>
			</div>
			<div class="fitem">
				<span id="errormsg" style="color: red"></span>
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveUser()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>

	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="javascript:newUser()">新建</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="javascript:editUser()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cut" plain="true" onclick="javascript:delUser()">删除</a>
	</div>
</body>
</html>

