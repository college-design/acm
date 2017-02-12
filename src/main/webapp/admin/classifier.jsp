<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>题目分类</title>
<link rel="stylesheet" type="text/css" href="${contentPath}/resources//jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${contentPath}/resources/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="margin:1px">
	<!-- 初始化显示数据 -->
	<table id="dg" class="easyui-datagrid"
		data-options="fitColumns:true,pagination:true,rownumbers:true,
		url:'${contentPath}/admin/classifier.action',fit:true,toolbar:'#tb'">
		<thead>
		   	<tr>
		   		<th field="cb" checkbox="true" align="center"></th>
		   		<th field="cid" width="50" align="center" disabled="none">分类标识</th>
		   		<th field="title" width="200" align="center">分类名称</th>
				<th field="createTime" width="200" align="center">分类创建时间</th>
				<th field="modifyTime" width="200" align="center">分类修改时间</th>
		   	</tr>
	    </thead>
	</table>
	<!-- 初始化按钮添加 -->
	<div id="tb">
		<div>
			<a href="javascript:openClassifierAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openClassifierModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteClassifier()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
	<!-- 添加修改 弹出框 -->
	<div id="dlg" class="easyui-dialog" style="width:500px;height:200px;padding:10px 20px"
		 data-options="closed:true,buttons:'#dlg-buttons'">
		<form id="fm" method="post">
			<table cellspacing="8px">
				<tr>
					<td>分类名称：</td>
					<td><input type="text" id="title" name="title" class="easyui-validatebox" required="true" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加修改弹出框按钮 -->
	<div id="dlg-buttons">
		<a href="javascript:saveClassifier()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeClassifierDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>

	<script type="text/javascript">
		var url;
		// 修改
		function openClassifierModifyTab(){
			var selectedRows=$("#dg").datagrid("getSelections");
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择一条要编辑的数据！");
				return;
			}
			var row=selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle","编辑题目分类信息");
			$("#fm").form("load",row);
			url="${contentPath}/admin/addclassifier.action?id="+row.cid;
		}
		// 删除链接
		function deleteClassifier(){
			var selectedRows=$("#dg").datagrid("getSelections"); // 获取选中元素
			if(selectedRows.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
				return;
			}
			var strIds=[];
			for(var i=0;i<selectedRows.length;i++){
				strIds.push(selectedRows[i].cid);
			}
			var ids=strIds.join(",");
			$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
				if(r){
					$.post("${contentPath}/admin/deleteclassifier.action",{ids:ids},function(result){
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
		function openClassifierAddDialog(){
			$("#dlg").dialog("open").dialog("setTitle","添加题目分类");
			url="${contentPath}/admin/addclassifier.action";
		}
		// 保存
		function saveClassifier(){
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
			$("#title").val("");
		}
		function closeClassifierDialog(){ // 关闭dialog
			$("#dlg").dialog("close");
			resetValue();
		}
	</script>
</body>
</html>