<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${autoresCompletos == null}">
		<jsp:forward page="ServletAutores"/>
	</c:if>
	<h1>Lista de Autores</h1>
	<table>
		<tr>
			<th>Nombre</th>
			<th>Fecha de nacimiento</th>
			<th>Nacionalidad</th>
			<th>Ver libros</th>
		</tr>
			<c:forEach items="${autoresCompletos}" var="autor">
		<tr>
  			<td>${autor.getNombre()}</td>  
  			<td>
  				<fmt:formatDate value="${autor.getFechanac()}" pattern="yyyy/MM/dd"/>
  			</td>
			<td>${autor.getNacionalidad()}</td>  
			<td><a href="ServletAutores?idAutorLibros=${autor.getIdAutor()}">Ver libros</a></td>
			
		</tr>
			</c:forEach>
		
	</table>
		<h2>Añadir Autor</h2>
		<form action="ServletAutores" method="post">
		Nombre: <input type="text" name="nombre"/><br>
		Fecha de nacimiento: <input type="date" name="fecha"><br>
		Nacionalidad: <input type="text" name="nacionalidad"/><br>
		<button type="submit" value="aniadir" name="aniadir">Añadir</button>
		</form>
		<c:if test="${msgAniadirAutor != null }">
			<p>${msgAniadirAutor }</p>
		</c:if>
		<br><br>
		<c:if test="${sessionScope.librosAutor != null }">
			<c:forEach items="${librosAutor.keySet()}" var="autor">
				<h2>Libros de ${autor}</h2>
				<ul>
					<c:forEach items="${librosAutor.get(autor)}" var="libro" >
						<li><a href="ServletAutores?prestar=${libro.getIdLibro()}&idAutorLibros=${libro.getIdLibro()}">${libro.getTitulo()}</a></li>
					</c:forEach>
				</ul>
			</c:forEach>
		</c:if>
</body>
</html>

<%-- <fmt:formatDate type = "time" 
         value = "${now}" /> --%>