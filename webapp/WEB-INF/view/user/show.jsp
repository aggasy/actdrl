<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'show.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

</head>

<body>
	用户名：${userInfo.userName } <br/>
	
	<form action="${pageContext.request.contextPath }/user/addInfo" method="POST">
		用户名：<input type="text" name="userName" value="${userInfo.userName }"/><br /> 
		密码：<input type="text" name="password" value="${userInfo.password }"/><br /> 
		电话：<input type="text" name="phone" value="${userInfo.phone }"/><br />
		email：<input type="text" name="email" values="${userInfo.email }"/><br /> 
		<input type="submit" value="提交" /> <input type="reset" value="重置">
	</form>
</body>
</html>