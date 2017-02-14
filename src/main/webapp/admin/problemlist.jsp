<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看题目列表</title>
<link rel="stylesheet" type="text/css" href="${contentPath}/resources//jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${contentPath}/resources/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
</head>
<body style="margin:1px">
	<!-- 显示数据 -->
	<table id="dg" class="easyui-datagrid"
		data-options="fitColumns:true,pagination:true,rownumbers:true,
		url:'${contentPath}/admin/problemlist.action',fit:true,toolbar:'#tb'">
		<thead>
		   	<tr>
		   		<th field="cb" checkbox="true" align="center"></th>
		   		<th field="pid" width="50" align="center">Problem Id</th>
		   		<th field="title" width="200" align="center">题目</th>
				<th field="source" width="200" align="center">来源</th>
				<th field="accepted" width="200" align="center">通过数</th>
				<th field="submit" width="200" align="center">提交数</th>
				<th field="ratio" width="200" align="center">通过率</th>
		   	</tr>
	    </thead>
	</table>
	<!-- 按钮添加 -->
	<div id="tb">
		<div>
			<a href="javascript:openProblemAddTab()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openProblemModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteProblem()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
	<!-- 添加修改 弹出框 -->
	<div id="dlg" class="easyui-dialog" style="width:1000px;padding:10px 20px"
		 data-options="closed:true,buttons:'#dlg-buttons',resizable:true">
		<form id="fm" method="post">
			<table cellspacing="8px">
				<tr>
					<td>标题：</td>
					<td><input type="text" id="title" name="title" class="easyui-validatebox" required="true" /></td>
					<td>描述：</td>
					<td>
						<textarea rows="4" cols="40" form="fm" id="description" name="description" class="easyui-validatebox" required="true">
						</textarea>
					</td>
				</tr>
				<tr>
					<td>输入描述：</td>
					<td>
						<textarea rows="4" cols="40" form="fm"  id="input" name="input" class="easyui-validatebox" required="true">
						</textarea>
					</td>
					<td>输出描述：</td>
					<td>
						<textarea rows="4" cols="40" form="fm"  id="output" name="output" class="easyui-validatebox" required="true">
						</textarea>
					</td>
				</tr>
				<tr>
					<td>输入实例：</td>
					<td>
						<textarea rows="4" cols="40" form="fm"  id="sampleInput" name="sampleInput" class="easyui-validatebox" required="true" >
						</textarea>
					</td>
					<td>输出实例：</td>
					<td>
						<textarea rows="4" cols="40" form="fm"  id="sampleOutput" name="sampleOutput" class="easyui-validatebox" required="true">
						</textarea>
					</td>
				</tr>
				<tr>
					<td>提示：</td>
					<td><input type="text" id="hint" name="hint" class="easyui-validatebox" required="true" /></td>
					<td>来源：</td>
					<td><input type="text" id="source" name="source" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>实例代码：</td>
					<td>
						<textarea rows="5" cols="40" form="fm"  id="sampleCode" name="sampleCode" class="easyui-validatebox" required="true">
						</textarea>
					</td>
					<td>通过数：</td>
					<td><input type="text" id="accepted" name="accepted" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td>提交总数：</td>
					<td><input type="text" id="submit" name="submit" class="easyui-validatebox" required="true"/></td>
					<td>通过率：</td>
					<td><input type="text" id="ratio" name="ratio" class="easyui-validatebox" required="true" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 添加修改弹出框按钮 -->
	<div id="dlg-buttons">
		<a href="javascript:saveProblem()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeProblemDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>

	<script type="text/javascript">
		var url;
		// 修改
		function openProblemModifyTab(){
			var selectedRows=$("#dg").datagrid("getSelections");
			if(selectedRows.length!=1){
				$.messager.alert("系统提示","请选择一条要编辑的数据！");
				return;
			}
			var row=selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle","编辑题目信息");
			$("#fm").form("load",row);
			url="${contentPath}/admin/addProblem.action?pid="+row.pid;
		}
		// 删除链接
		function deleteProblem(){
			var selectedRows=$("#dg").datagrid("getSelections"); // 获取选中元素
			if(selectedRows.length==0){
				$.messager.alert("系统提示","请选择要删除的数据！");
				return;
			}
			var strIds=[];
			for(var i=0;i<selectedRows.length;i++){
				strIds.push(selectedRows[i].pid);
			}
			var ids=strIds.join(",");
			$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
				if(r){
					$.post("${contentPath}/admin/deleteProblem.action",{ids:ids},function(result){
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
		function openProblemAddTab(){
			$("#dlg").dialog("open").dialog("setTitle","添加题目信息");
			url="${contentPath}/admin/addProblem.action";
		}
		// 保存
		function saveProblem(){
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
			$("#description").val("");
			$("#input").val("");
			$("#output").val("");
			$("#sampleInput").val("");
			$("#sampleOutput").val("");
			$("#hint").val("");
			$("#source").val("");
			$("#sampleCode").val("");
			$("#accepted").val("");
			$("#submit").val("");
			$("#ratio").val("");
		}
		function closeProblemDialog(){ // 关闭dialog
			$("#dlg").dialog("close");
			resetValue();
		}
	</script>
</body>
</html>