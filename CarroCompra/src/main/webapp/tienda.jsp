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
<h1>ENTRA TIENDA</h1>
	<h2>LÁMINAS</h2>
	<form method="post" action="ServletAgregarLineaPedido">
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Nombre</th>
				<th>Precio</th>
				<th>Cantidad</th>
				<th>Añadir</th>
			</tr>
			<c:forEach items="${listaItems}" var="item">
				<tr>
					<td>${item.getId()}</td>
					<td>${item.getNombre()}</td>
					<td>${item.getPrecio()}</td>
					<td><input type="number" name="${item.getId()}"></td>
					<td><button type="submit" value="${item.getId()}" name="aniadirAlCarro">Añadir al carro</button></td>
				</tr>
			</c:forEach>
	
			
		
		</table>
	</form>
	
	<form method="post" action="listar_cesta.jsp">
		<button type="submit" value="" name="verCesta">Ver cesta</button>
	</form>
	<form method="post" action="pedir.jsp">
		<button type="submit" value="" name="hacerPedido">Hacer pedido</button>
	</form>
	<form method="post" action="ServletListarPedidos">
		<button type="submit" value="" name="misPedidos">Mis pedidos</button>
	</form>

</body>
</html>


<!-- listaItems -->