<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nueva cuenta</title>
</head>
<body>

<c:if test="${param.error != null}" >
	<p style="color:red"><c:out value="${param.error}"></c:out></p>
</c:if >

	        <table cellpadding="3">
	        <tr bgcolor="cccccc">
				<th colspan="5">NUEVA CUENTA</th>
			</tr>
			<form method="post" action="ServletNuevaCuenta">
	            <tr bgcolor="cccccc">
	                <td colspan="2"><label for="titular">Titular</label></td>
	                <td colspan="3"><input type="text" name="titular"></td>
	            </tr>
	            <tr bgcolor="cccccc">
	                <td colspan="2"><label for="saldoInicial">Saldo inicial</label></td>
	                <td colspan="3"><input type="number" name="saldoInicial"></td>
	            </tr>
	            
	            <tr bgcolor="#222222">
	                <td colspan="5" align="center">
	                	<input type="submit" value="Crear Cuenta Corriente">
	                </td>
	            </tr>
			</form>
  
        </table>

</body>
</html>