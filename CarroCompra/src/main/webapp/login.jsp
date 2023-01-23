<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>LOGIN</h1>
	<form method="post" action="ServletLogin">
		Usuario:    <input type="text" name="usuario"><br><br>
		Contraseña: <input type="password" name="contrasenia"><br><br>
		<button type="submit" value="login">Login</button>
		<input type="reset" value="Reset">
		<a href="registro.jsp">REGISTRARSE</a>	
	</form>
	
	<c:if test="${errorLogin != null }">
		<script type="text/javascript">
			alert("<%=request.getSession().getAttribute("errorLogin") %>");
		</script>
	</c:if>	
	<c:if test="${param.registroCorrecto != null}">
		<p style="color:green">Cliente registrado</p>
	</c:if>
</body>
</html>