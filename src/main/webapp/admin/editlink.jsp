<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改链接</title>
	<link rel="stylesheet" type="text/css" href="${contentPath}/resources//jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${contentPath}/resources/jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function submitData(){
		$('#form1').submit();
	}
</script>
</head>
<body style="magin:10px">
	<div id="p" class="easyui-panel" data-options="title:'修改链接'" style="padding:10px">
		<form id="form1" action="${contentPath}/admin/editlink.action" method="post">
			<table cellspacing="20px">
				<tr>
					<td width="80px">id:</td>
					<td>
						<input type="text" id="id" name="id" value="${id }"/>
					</td>
				</tr>
				<tr>
					<td>名称:</td>
					<td><input type="text" id="name" name="name" value="${name}"/></td>
				</tr>
				<tr>
					<td>地址:</td>
					<td><input type="text" id="url" name="url" value="${url}"/></td>
				</tr>
				<tr>
					<td>类型:</td>
					<td><input type="text" id="type" name="type" value="${type}"/></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">修改</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>