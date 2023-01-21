<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movimientos</title>
</head>
<body>
	
	<table cellpadding="3">
	        <tr bgcolor="cccccc">
				<th colspan="5">MOVIMIENTOS</th>
			</tr>
			<form method="post" action="ServletMovimientos">
	            <tr bgcolor="cccccc">
	                <td colspan="2"><label for="titular">Titular</label></td>
	                <td colspan="3"><c:out value="${sessionScope.cuentaNueva.getTitular()}"/></td>
	            </tr>
	            <tr bgcolor="cccccc">
	                <td colspan="2"><label for="saldoInicial">Saldo inicial</label></td>
	                <td colspan="3"><c:out value="${sessionScope.cuentaNueva.getSaldo() }"/></td>
	            </tr>
	            <tr bgcolor="cccccc">
	                <td colspan="2"><label for="saldoInicial">CANTIDAD</label></td>
	                <td colspan="3"><input type="number" name="ingresosGastos"></td>
	            </tr>
	            
	            <tr bgcolor="#222222">
	                <td colspan="5" align="center">
	                	<input type="submit" value="INGRESAR" name="ingresar">
	                	<input type="submit" value="GASTAR" name="gastar">
	                </td>
	            </tr>
			</form>
    </table>
    
    <c:if test="${param.error != null }">
    	<c:out value="${param.error }" /> &euro;
    </c:if>
</body>
</html>
