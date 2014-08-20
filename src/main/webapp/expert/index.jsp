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

	function formateDesc(value, row, index) {
		var button = '<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" plain=\"true\" onclick=\"javascript:openDesc('+row.id+')\">查看简历</a>';
		return button;
	}

	var url;
	//新建用户
	//打开新建面板
	function newExpert() {	
		$('#dlg').dialog('open').dialog('setTitle', '新建专家');
		$('#fm').form('clear');
		url = 'ExpertServlet?method=addExpert';
	}

	function editExpert() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑专家信息');
			$('#fm').form('load', row);
			//给性别下拉框赋值
			var genderVal;
			if (row.gender == "MALE")
				genderVal = 0;
			else if (row.gender == "FEMALE")
				genderVal = 1;
			var data = $('#gender').combobox('getData');
			$('#gender').combobox("select", data[genderVal].value);

			url = 'ExpertServlet?method=updateExpert';
		} else {
			alert("请选择要编辑的专家");
		}
	}

	function delExpert() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认', '确认删除这个专家信息吗?', function(r) {
				if (r) {
					//url = 'UserServlet?method=delUser';
					$.ajax({
							url : "ExpertServlet?method=delExpert&id="+ row.id,
							type : "post",
							success : function(data, status, response) {
								var result = eval('('
										+ response.responseText + ')');
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
	function saveExpert() {
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

	function openDesc(data) {
		$('#dlg-content').dialog('open').dialog('setTitle', '专家简历查看');
		$.ajax({
			url : "ExpertServlet?method=loadExpert&id="+ data,
			type : "post",
			success : function(data, status, response) {
				var result = eval('('
						+ response.responseText + ')');
				if (result.status == "error") {
					$.messager.show({
						title : 'Error',
						msg : result.msg
					});
				} else {
					if(result.expertDesc == null || result.expertDesc == "")
						$('#descContent').text("暂无简历信息");
					else
						$('#descContent').text(result.expertDesc);
				}
				
			}
		});
	}
</script>
</head>
<body>

	<table id="dg" class="easyui-datagrid" title="专家列表" fit="true"
		data-options="singleSelect:true,collapsible:true,
		pagination:true,pageSize:10,pageList: [10, 15, 20, 25, 30],
		url:'ExpertServlet?method=getExpertList',method:'get',
		toolbar:'#tb'
		">
		<thead>
			<tr>
				<th data-options="field:'expertName',width:100">专家姓名</th>
				<th data-options="field:'idNumber',width:80">专家证件号</th>
				<th data-options="field:'gender',width:80">专家性别</th>
				<th data-options="field:'major',width:80">专家专业</th>
				<th data-options="field:'expertDesc',width:65,formatter:formateDesc">专家简历</th>
				<th
					data-options="field:'lastModifyDate',width:180,align:'right',formatter:formatDate">更新日期</th>
				<th data-options="field:'createDate',width:180,formatter:formatDate">创建日期</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: 480px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">专家信息</div>
		<form id="fm" method="post">
			<input name="id" type="hidden" value="">
			<div class="fitem">
				<label>专家姓名:</label> <input name="expertName"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>专家证件号:</label> <input name="idNumber">
			</div>
			<div class="fitem">
				<label>专家性别:</label> <select id="gender" class="easyui-combobox"
					name="gender" style="width: 50px;">
					<option value="0">男</option>
					<option value="1">女</option>
				</select>
			</div>
			<div class="fitem">
				<label>专家专业:</label> <input name="major">
			</div>
			<div class="fitem">
				<label>专家简历:</label>
				<textarea name="expertDesc" style="height: 200px; width: 280px"></textarea>

			</div>
		</form>
	</div>
	<div id="dlg-content" class="easyui-dialog"
		style="width: 500px; height: 480px; padding: 10px 20px" closed="true">
		<div class="ftitle">专家简历</div>
		<div id="descContent"></div>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveExpert()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>

	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="javascript:newExpert()">新建</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="javascript:editExpert()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cut" plain="true" onclick="javascript:delExpert()">删除</a>
	</div>
</body>
</html>

