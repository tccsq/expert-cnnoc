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
	$(function(){
		$('#dg').datagrid({
			onClickRow:function(index,data){
				$('#dg-comment').datagrid({
					url:'EvaluationServlet?method=getEvaluationList',
					queryParams:{
						projectId:data.id
					}
				});
			}
		});
	});


	function formatAction(value,row,index){
       var btn = '<a iconCls:"icon-edit"  onclick="addcomments(\''+row.id+'\',\''+row.projectName+'\')" href="javascript:void(0)"  >添加专家评价</a>';
       
       return btn;
	}
	function formatCommentAction(value,row,index){
       var btn = '<a iconCls:"icon-edit"  onclick="updateComments(\''+index+
    		   '\')" href="javascript:void(0)">更新</a>';
    		   
       
       return btn;
	}
	
	

	function updateComments(data) {
		$('#dg-comment').datagrid('selectRow',data);
		var obj = $('#dg-comment').datagrid('getSelected');
		
		$('#evaluationId').val(obj.id);
		$('#projectId').val(obj.projectId);
		$('#projectName').val(obj.projectName);
		$('#idNumberDiv').attr("style", "display:display;");
		$('#idNumber').text(obj.idNumber);
		$('#evaluationContent').text(obj.evaluationContent);
		
		$('#dlg-comment').dialog({
		    onClose:function(){
		    	$('#idNumberDiv').attr("style", "display:none;");
		    	$('#idNumber').text("");
		    }
		});
		
		//combox
		$('#dlg-comment').dialog('open').dialog('setTitle', '更新评价');
		 $('#expert').combobox({
			    url:'ExpertServlet?method=getExpertComboxList',
			    valueField:'id',
			    onSelect:function(record){
			    	$('#idNumberDiv').attr("style", "display:display;");
			    	$('#idNumber').text(record.idNumber);
			    },
			    textField:'expertName'
			}); 
		 $('#expert').combobox('setValue',obj.expertId).combobox('setText',obj.expertName); 
	}

	function addcomments(projectId, projectName) {
		$('#projectId').val(projectId);
		$('#projectName').val(projectName);
		//
		$('#dlg-comment').dialog({
			onClose : function() {
				$('#idNumberDiv').attr("style", "display:none;");
				$('#idNumber').text("");
			}
		});
		$('#dlg-comment').dialog('open').dialog('setTitle', '添加评价');

		//给专家的combox赋值
		$('#expert').combobox({
			url : 'ExpertServlet?method=getExpertComboxList',
			valueField : 'id',
			onSelect : function(record) {
				$('#idNumberDiv').attr("style", "display:display;");
				$('#idNumber').text(record.idNumber);
			},
			textField : 'expertName'
		});
	}

	var url;
	//新建项目
	//打开新建面板
	function newProject() {
		$('#dlg').dialog('open').dialog('setTitle', '新建项目');
		$('#fm').form('clear');
		url = 'ProjectServlet?method=addProject';
	}

	function editProject() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$('#dlg').dialog('open').dialog('setTitle', '编辑项目信息');
			$('#fm').form('load', row);
			//给开始时间和结束时间赋值

			$("#startDate").datebox("setValue", row.startDate);
			$("#endDate").datebox("setValue", row.endDate);

			url = 'ProjectServlet?method=updateProject';
		} else {
			alert("请选择要编辑的项目");
		}
	}

	function delProject() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$.messager.confirm('确认', '确认删除这个项目信息吗?', function(r) {
				if (r) {
					$
							.ajax({
								url : "ProjectServlet?method=delProject&id="
										+ row.id,
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
	function saveProject() {
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
	function saveComment() {
		$('#fm-comment').form('submit', {
			url : "EvaluationServlet?method=addEvaluation",
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
					$('#dlg-comment').dialog('close'); // close the dialog
					$('#dg-comment').datagrid('reload'); // reload the user data
				}
			}
		});
	}
</script>
</head>
<body>

	<table id="dg" class="easyui-datagrid" title="项目列表"
		style="width:820px;height:380px"
		data-options="singleSelect:true,collapsible:true,
		pagination:true,pageSize:10,pageList: [10, 15, 20, 25, 30],
		url:'ProjectServlet?method=getProjectList',method:'get',
		toolbar:'#tb'">
		<thead>
			<tr>
				<th data-options="field:'projectName',width:120">项目名称</th>
				<th data-options="field:'projectLocation',width:150">项目地点</th>
				<th data-options="field:'startDate',width:180,align:'right'">开始日期</th>
				<th data-options="field:'endDate',width:180">结束日期</th>
				<th data-options="field:'id',plain:true,width:180,formatter:formatAction">操作</th>
			</tr>
		</thead>
	</table>
	
	<table id="dg-comment" class="easyui-datagrid" title="评价列表"
		style="width:820px;height:240px"
		data-options="singleSelect:true,collapsible:true,nowrap:false,
		pagination:true,pageSize:5,pageList: [5,10, 15, 20, 25, 30],
		url:'EvaluationServlet?method=getEvaluationList',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'projectName',width:120">项目名称</th>
				<th data-options="field:'expertName',width:150">专家姓名</th>
				<th data-options="field:'idNumber',width:180,align:'right'">专家证件号</th>
				<th data-options="field:'evaluationContent',width:180">评价内容</th>
				<th data-options="field:'id',plain:true,width:180,formatter:formatCommentAction">操作</th>
			</tr>
		</thead>
	</table>
	
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">项目信息:</div>
		<form id="fm" method="post">
			<input name="id" type="hidden" value="">
			<div class="fitem">
				<label>项目名称:</label> <input name="projectName"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>项目地点:</label> <input name="projectLocation" class="easyui-validatebox">
			</div>
			<div class="fitem">
				<label>开始日期:</label> <input id="startDate" name="startDate" class="easyui-datebox" >
			</div>
			<div class="fitem">
				<label>结束日期:</label> <input id="endDate" name="endDate" class="easyui-datebox" >
			</div>
			<div class="fitem">
				<span id="errormsg" style="color:red"></span>
			</div>
		</form>
	</div>
	
	<div id="dlg-comment" class="easyui-dialog"
		style="width: 400px; height: 400px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons-comment" >
		<div class="ftitle">评价信息:</div>
		<form id="fm-comment" method="post">
			<input name="projectId" id="projectId" type="hidden" value="">
			<input name="evaluationId" id="evaluationId" type="hidden" value="">
			<div class="fitem">
				<label>项目名称:</label> <input id="projectName"  name="projectName"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>选择专家:</label> 
					<input id="expert" name="expert" value=""/>
			</div>
			<div class="fitem" style="display:none;" id="idNumberDiv">
				<label>专家证件号:</label>
				<span id="idNumber"></span>
			</div>
			<div class="fitem">
				<label>评价内容:</label>
				<textarea id="evaluationContent" name="evaluationContent" style="height: 160px; width: 280px"></textarea>
			</div>
			
			<div class="fitem">
				<span id="errormsg" style="color:red"></span>
			</div>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveProject()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	<div id="dlg-buttons-comment">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveComment()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg-comment').dialog('close')">取消</a>
	</div>

	<div id="tb">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="javascript:newProject()">新建</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="javascript:editProject()">编辑</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cut" plain="true" onclick="javascript:delProject()">删除</a>
	</div>
</body>
</html>

