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
</head>
<body style="margin:1px">
	<!-- 显示数据 -->
	<table id="dg" class="easyui-datagrid"
		data-options="fitColumns:true,pagination:true,rownumbers:true,
		url:'${contentPath}/admin/contestlist.action',fit:true,toolbar:'#tb'">
		<thead>
		   	<tr>
		   		<th field="cb" checkbox="true" align="center"></th>
		   		<th field="cid" width="50" align="center">比赛id</th>
		   		<th field="title" width="200" align="center">比赛名称</th>
				<th field="startTime" width="200" align="center">比赛开始时间</th>
				<th field="endTime" width="200" align="center">比赛结束时间</th>
		   	</tr>
	    </thead>
	</table>
	<!-- 按钮添加 -->
	<div id="tb">
		<div>
			<a href="javascript:openBlogModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">查看</a>
			<a href="javascript:openBlogModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteBlog()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
</body>
</html>