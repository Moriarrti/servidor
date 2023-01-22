<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	  	<table cellpadding="3">
	        <tr bgcolor="cccccc">
				<th colspan="12">PRIMITIVA</th>
			</tr>
			<form method="post" action="ServletCheckFecha">
	            <tr bgcolor="cccccc">
	                <c:choose>
	                	<c:when test="${sessionScope.primitiva != null }">
	                		<c:set var="dia" scope="page">${sessionScope.primitiva.getFecha().getDia() }</c:set>
	                		<c:set var="mes" scope="page">${sessionScope.primitiva.getFecha().getMes() }</c:set>
	                		<c:set var="anio" scope="page">${sessionScope.primitiva.getFecha().getAnio() }</c:set>
			                <td colspan="1"><label for="dia">Dia</label></td>
	               			<td colspan="3"><input type="number" name="dia" placeholder="${dia}"></td>
			                <td colspan="1"><label for="mes">Mes</label></td>
	               			<td colspan="3"><input type="number" name="mes" placeholder="${mes}"></td>
			                <td colspan="1"><label for="anio">Año</label></td>
	               			<td colspan="3"><input type="number" name="anio" placeholder="${anio}"></td>
	                	</c:when>
	                	<c:otherwise>
			                <td colspan="1"><label for="dia">Dia</label></td>
	               			<td colspan="3"><input type="number" name="dia"></td>	                	
			                <td colspan="1"><label for="mes">Mes</label></td>
			                <td colspan="3"><input type="number" name="mes"></td>
			        
			                <td colspan="1"><label for="anio">Año</label></td>
			                <td colspan="3"><input type="number" name="anio"></td>
	                	</c:otherwise>
	                </c:choose>
	
	            </tr>
	            <c:if test="${sessionScope.primitiva != null }">
	            	<tr bgcolor="cccccc" style="text-align:center">
		            	<td colspan="12">
		            		<b>Números: </b>
		            		<c:forEach items="${sessionScope.primitiva.getNumerosGanadores()}" var="numero">
		            			<span>${numero}</span>
		            		</c:forEach>
		            		<b>Reintegro: </b>
		            		<c:out value="${sessionScope.primitiva.getReintegroGanador()}"></c:out>
		            	</td>
	            	</tr>
	            	<tr bgcolor="cccccc" style="text-align:center">
		            	<td colspan="12">
		            		<a href='check_apuestas.jsp'>COMPROBAR APUESTAS</a>
		            	</td>
		            </tr>
	            </c:if>

	            
	            <tr bgcolor="#222222">
	                <td colspan="12" align="center">
	                	<input type="submit" value="Crear Primitiva">
	                </td>
	            </tr>
			</form>
  
        </table>

		<c:if test="${param.error != null}" >
			<p style="color:red"><c:out value="${param.error}"></c:out></p>
		</c:if >
</body>
</html>