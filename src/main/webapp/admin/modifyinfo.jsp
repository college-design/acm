<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改个人信息页面</title>
	<link rel="stylesheet" type="text/css" href="${contentPath}/resources//jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${contentPath}/resources/jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${contentPath}/resources/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		function submitData(){
			var username=$("#username").val();
			var password=$("#password").val();
			var email=$("#email").val();
			var qq=$("#qq").val();

			if(username==null || username==''){
				alert("请输入用户名！");
			}else if(password==null || password==''){
				alert("请输入密码！");
			}else if(email==null || email==''){
				alert("请输入邮箱！");
			}else if(qq==null || qq==''){
				alert("请输入QQ！");
			} else{
				$('#form1').submit();
			}
		}
	</script>
</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="修改个人信息" style="padding: 10px">
	<form id="form1" action="${contentPath}/admin/saveInfo.action" method="post" enctype="multipart/form-data">
	 	<table cellspacing="20px">
	   		<tr>
	   			<td width="80px">用户名：</td>
	   			<td>
	   				<input type="hidden" id="uid" name="uid" value="${session.user.uid}"/>
	   				<input type="text" id="username" name="username" style="width: 200px;" value="${session.user.username}" readonly="readonly"/>
	   			</td>
	   		</tr>
			<tr>
				<td>用户密码：</td>
				<td><input type="password" id="password" name="password"  style="width: 200px;" value="${session.user.password}"/></td>
			</tr>
	   		<tr>
	   			<td>邮箱：</td>
	   			<td><input type="text" id="email" name="email"  style="width: 200px;" value="${session.user.email}"/></td>
	   		</tr>
	   		<tr>
	   			<td>qq：</td>
	   			<td><input type="text" id="qq" name="qq" value="${session.user.qq}" style="width: 400px;"/></td>
	   		</tr>
	   		<tr>
	   			<td></td>
	   			<td>
	   				<a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">提交</a>
	   			</td>
	   		</tr>
	   	</table>
   	</form>
 </div>
</body>
</html>