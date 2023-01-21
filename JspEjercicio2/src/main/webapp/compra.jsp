<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<%
	ArrayList<String> listaProductos = (ArrayList<String>)session.getAttribute("listaProductos");
	HashMap<String, Integer> listaProductosSeleccionados = new HashMap<String, Integer>();
	if(request.getParameter("productoEnviado") != null){
		String productoEnviado = request.getParameter("productoEnviado");
		if(session.getAttribute("listaProductosSeleccionados") != null){
			listaProductosSeleccionados = (HashMap<String, Integer>) session.getAttribute("listaProductosSeleccionados");
			if(listaProductosSeleccionados.containsKey(productoEnviado)){
				Integer cantidad = listaProductosSeleccionados.get(productoEnviado);
				cantidad++;
				listaProductosSeleccionados.put(productoEnviado, cantidad);
			}
			else{
				listaProductosSeleccionados.put(productoEnviado, 1);
			} 
			session.setAttribute("listaProductosSeleccionados", listaProductosSeleccionados);
		}
		else{
			listaProductosSeleccionados.put(productoEnviado, 1);
			session.setAttribute("listaProductosSeleccionados", listaProductosSeleccionados);
			
		}
	}
%>
<body>

	<h1>Compras</h1>
	<table>
		<tr>
			<th>PRODUCTO</th>
			<th>PEDIR</th>
		</tr>
	<%
		
	for(String producto: listaProductos){

	%>
		<tr>
			<form method="post" action="compra.jsp" >
				<td><label><%= producto %></label></td>
				<td><button type="submit" value="<%= producto %>" name="productoEnviado" id="">Adquirir unidad</button></td>
			</form>
			
	<%
		if(session.getAttribute("listaProductosSeleccionados") != null){
			listaProductosSeleccionados = (HashMap<String, Integer>) session.getAttribute("listaProductosSeleccionados");
			if(listaProductosSeleccionados.containsKey(producto)){
				int cantidad = listaProductosSeleccionados.get(producto);
				System.out.println(cantidad);
				out.println("<td>" + cantidad + " unidades</td>");
			}
			
		}
	%>
			
		</tr>
		
	<%
	}
	

	
	%>
	</table>
	
	<%@include file="muestracarro.jsp" %>
	
</body>
</html>