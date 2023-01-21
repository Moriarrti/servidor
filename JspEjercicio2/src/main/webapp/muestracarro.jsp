<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.HashMap"%>



<%
	if(session.getAttribute("listaProductosSeleccionados") != null){
		HashMap<String, Integer> listaCarro = (HashMap<String, Integer>)session.getAttribute("listaProductosSeleccionados");
%>		
		
	<h2>TU CARRO</h2>
		<ul>
<%
		for(String producto: listaCarro.keySet()){
%>		
			
			<li><b><%= producto %>:</b> <%= listaCarro.get(producto) %> unidades</li>
	
<%
		}

	}



%>
		</ul>
<div>
	


</div>