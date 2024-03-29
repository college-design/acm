<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>友情链接列表</title>
<link rel="stylesheet" type="text/css" href="${contentPath}/static//jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${contentPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${contentPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${contentPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${contentPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="margin:1px">
	<!-- 初始化显示数据 -->
	<table id="dg" class="easyui-datagrid"
		data-options="fitColumns:true,pagination:true,rownumbers:true,
		url:'${contentPath}/admin/linklist.action',fit:true,toolbar:'#tb'">
		<thead>
		   	<tr>
		   		<th field="cb" checkbox="true" align="center"></th>
		   		<th field="id" width="50" align="center" disabled="none">链接号</th>
		   		<th field="name" width="200" align="center">链接名称</th>
				<th field="url" width="200" align="center" formatter="formatURL">链接地址</th>
				<th field="type" width="200" align="center">链接类型</th>
		   	</tr>
	    </thead>
	</table>
	<!-- 初始化按钮添加 -->
	<div id="tb">
		<div>
			<a href="javascript:openLinkAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openLinkModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteLink()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
	<!-- 添加修改 弹出框 -->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px"
		 data-options="closed:true,buttons:'#dlg-buttons'">
		<form id="fm" method="post">
			<table cellspacing="8px">
				<tr>
					<td>友情链接名称：</td>
					<td><input type="text" id="name" name="name" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>友情链接地址：</td>
					<td><input type="text" id="url" name="url" class="easyui-validatebox" validtype="url" required="true"/></td>
				</tr>
				<tr>
					<td>友情链接类型：</td>
					<td><input type="text" id="type" name="type" class="easyui-validatebox" required="true" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加修改弹出框按钮 -->
	<div id="dlg-buttons">
		<a href="javascript:saveLink()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeLinkDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>

	<script type="text/javascript">
		// 格式化表格数据
		function formatURL(val,row){
			return "<a target='_blank' href='"+val+"'>"+val+"</a>"
		}

		var url;
		// 修改
		function openLinkModifyTab(){
			var selectedRows=$("#dg").datagrid("getSelections");
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择一条要编辑的数据！");
				return;
			}
			var row=selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle","编辑友情链接信息");
			$("#fm").form("load",row);
			url="${pageContext.request.contextPath}/admin/addlink.action?id="+row.id;
		}
		// 删除链接
		function deleteLink(){
			var selectedRows=$("#dg").datagrid("getSelections"); // 获取选中元素
			if(selectedRows.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
				return;
			}
			var strIds=[];
			for(var i=0;i<selectedRows.length;i++){
				strIds.push(selectedRows[i].id);
			}
			var ids=strIds.join(",");
			$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
				if(r){
					$.post("${contentPath}/admin/deletelink.action",{ids:ids},function(result){
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
		function openLinkAddDialog(){
			$("#dlg").dialog("open").dialog("setTitle","添加友情链接");
			url="${contentPath}/admin/addlink.action";
		}
		// 保存
		function saveLink(){
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
			$("#name").val("");
			$("#url").val("");
			$("#type").val("");
		}
		function closeLinkDialog(){ // 关闭dialog
			$("#dlg").dialog("close");
			resetValue();
		}
	</script>
</body>
</html>