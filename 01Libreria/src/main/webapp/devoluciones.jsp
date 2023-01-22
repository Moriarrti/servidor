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
	<c:if test="${librosPrestados == null }">
		<jsp:forward page="ServletDevolver"></jsp:forward>
	</c:if>
	<table>
		<c:set var="i" scope="page">1</c:set>
		<c:forEach items="${librosPrestados.keySet()}" var="libro" begin="1">
			<tr>
				<td>${i}.-</td>
				<td>${libro.getTitulo()}, ${librosPrestados.get(libro) }</td>
				<c:choose>
					<c:when test="${librosDevolver.contains(libro.getIdLibro())}">
						<td><a href="ServletDevolver?revertirLibro=${libro.getIdLibro()}" style="color:red">REVERTIR DEVOLUCION</a></td>
					</c:when>
					<c:otherwise>
						<td><a href="ServletDevolver?devolverLibro=${libro.getIdLibro()}">MARCAR DEVOLUCION</a></td>
					</c:otherwise>
				</c:choose>
			
			</tr>
			<c:set var="i" scope="page">${i + 1}</c:set>
		</c:forEach>
	</table>
	<c:if test="${librosDevolver.size() > 0}">
		<br><br>
		<a href="ServletDevolver?grabar=true">GRABAR DEVOLUCIONES</a><span>(${librosDevolver.size()} libros )</span>
	</c:if>
</body>
</html>