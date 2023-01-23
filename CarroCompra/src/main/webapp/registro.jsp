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
	<h1>REGISTRO</h1>
	<form method="post" action="ServletRegistro">
		<table>
			<tr>
				<td>Usuario</td>
				<td><input type="text" name="usuario">
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" name="password">
			</tr>
			<tr>
				<td>Domicilio</td>
				<td><input type="text" name="domicilio">
			</tr>
			<tr>
				<td>Zip</td>
				<td><input type="text" name="zip">
			</tr>
			<tr>
				<td>Teléfono</td>
				<td><input type="text" name="telefono">
			</tr>
			<tr>
				<td>E-mail</td>
				<td><input type="text" name="email">
			</tr>
		</table>
		<br>
		<button type="submit" value="registrarse">Registrarse</button>
		<input type="reset" value="Reset">
	</form>
		<c:if test="${param.errorRegistro != null}">
			<p style="color:red">No se pudo registrar usuario</p>
		</c:if>

</body>
</html>