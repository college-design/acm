<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>比赛列表</title>
<link rel="stylesheet" type="text/css" href="${contentPath}/resources//jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${contentPath}/resources/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	// easyui datagrid dateFormatter
	function formatterdate(val, row) {
		if (val != null) {
			var date = new Date(val);
			return date.getFullYear() + '/' + (date.getMonth() + 1) + '/'
					+ date.getDate();
		}
	}
	function search(){
		$("#dg").datagrid('load',{
			"title":$("#s_title").val()
		});
	}

	var url;
	// 修改
	function openContestModifyTab(){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑比赛信息");
		$("#fm").form("load",row);
		url="${contentPath}/admin/addConteset.action?cid="+row.cid;
	}
	// 删除链接
	function deleteContest(){
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
				$.post("${contentPath}/admin/deleteContest.action",{ids:ids},function(result){
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
	function openContestAddTab(){
		$("#dlg").dialog("open").dialog("setTitle","添加比赛");
		url="${contentPath}/admin/addConteset.action";
	}
	// 保存
	function saveContest(){
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
		$("#startTime").val("");
		$("#endTime").val("");
		$("#defunct").val("");
		$("#description").val("");
	}
	function closeContestDialog(){ // 关闭dialog
		$("#dlg").dialog("close");
		resetValue();
	}
</script>
</head>
<body style="margin:1px">
	<!-- 显示数据 -->
	<table id="dg" class="easyui-datagrid"
		data-options="fitColumns:true,pagination:true,rownumbers:true,
		url:'${contentPath}/admin/contestlist.action',fit:true,toolbar:'#tb'">
		<thead>
		   	<tr>
		   		<th field="cb" checkbox="true" align="center"></th>
		   		<th field="cid" width="50" align="center">比赛标识</th>
		   		<th field="title" width="200" align="center">比赛标题</th>
				<th field="startTime" width="200" align="center" formatter="formatterdate">开始时间</th>
				<th field="endTime" width="200" align="center" formatter="formatterdate">结束时间</th>
				<th field="defunct" width="200" align="center">是否显示</th>
		   	</tr>
	    </thead>
	</table>
	<!-- 按钮添加 -->
	<div id="tb">
		<div>
			<a href="javascript:openContestAddTab()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openContestModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteContest()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			<!-- 搜索 -->
			<div>
				标题：&nbsp;&nbsp;<input type="text" id="s_title" name="s_title" size="20" onkeydown="if(event.keyCode==13) search()">
				<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
			</div>
		</div>
	</div>

	<!-- 添加修改 弹出框 -->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:500px;padding:10px 20px"
		 data-options="closed:true,buttons:'#dlg-buttons'">
		<form id="fm" method="post">
			<table cellspacing="8px">
				<tr>
					<td>比赛标题：</td>
					<td><input type="text" id="title" name="title" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>开始时间：</td>
					<td><input type="text" id="startTime" name="startTime" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>结束时间：</td>
					<td><input type="text" id="endTime" name="endTime" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>是否显示：</td>
					<td><input type="text" id="defunct" name="defunct" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>比赛描述：</td>
					<td>
						<textarea rows="15" cols="20" id="description" name="description" class="easyui-validatebox" required="true"></textarea>
						<%--<input type="text" id="description" name="description" class="easyui-validatebox" required="true" />--%>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加修改弹出框按钮 -->
	<div id="dlg-buttons">
		<a href="javascript:saveContest()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeContestDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>