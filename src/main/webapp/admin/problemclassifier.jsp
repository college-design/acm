<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>题目分类列表</title>
<link rel="stylesheet" type="text/css" href="${contentPath}/static//jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${contentPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${contentPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${contentPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${contentPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	// 修改
	function openClassifierProblemModifyTab(){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑分类-问题信息");
		$("#fm").form("load",row);
		url="${contentPath}/admin/addclassifierProblem.action?cpid="+row.cpid;
	}
	// 删除链接
	function deleteClassifierProblem(){
		var selectedRows=$("#dg").datagrid("getSelections"); // 获取选中元素
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].cpid);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("${contentPath}/admin/deleteClassifierProblem.action",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","数据已成功删除！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","数据删除失败！");
					}
				},"json");
			}
		});
	}
	// 添加
	function openClassifierProblemAddTab(){
		$("#dlg").dialog("open").dialog("setTitle","添加问题-分类");
		url="${contentPath}/admin/addclassifierProblem.action";
	}
	// 保存
	function saveClassifierProblem(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert("系统提示","保存成功！");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert("系统提示","保存失败！");
					return;
				}
			}
		});
	}
	function resetValue(){ // 重置
		$("#pid").val("");
		$("#cid").val("");
	}
	function closeClassifierProblemDialog(){ // 关闭dialog
		$("#dlg").dialog("close");
		resetValue();
	}
</script>
</head>
<body style="margin:1px">
	<!-- 显示数据 -->
	<table id="dg" class="easyui-datagrid"
		data-options="fitColumns:true,pagination:true,rownumbers:true,
		url:'${contentPath}/admin/classifierProblem.action',fit:true,toolbar:'#tb'">
		<thead>
		   	<tr>
		   		<th field="cb" checkbox="true" align="center"></th>
		   		<th field="cpid" width="50" align="center">问题分类关联标识</th>
		   		<th field="pid" width="200" align="center">问题标识</th>
				<th field="cid" width="200" align="center">分类标识</th>
		   	</tr>
	    </thead>
	</table>
	<!-- 按钮添加 -->
	<div id="tb">
		<div>
			<a href="javascript:openClassifierProblemAddTab()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openClassifierProblemModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteClassifierProblem()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>

	<!-- 添加修改 弹出框 -->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:500px;padding:10px 20px"
		 data-options="closed:true,buttons:'#dlg-buttons'">
		<form id="fm" method="post">
			<table cellspacing="8px">
				<tr>
					<td>问题标识：</td>
					<td><input type="text" id="pid" name="pid" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>分类标识：</td>
					<td><input type="text" id="cid" name="cid" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加修改弹出框按钮 -->
	<div id="dlg-buttons">
		<a href="javascript:saveClassifierProblem()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeClassifierProblemDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>