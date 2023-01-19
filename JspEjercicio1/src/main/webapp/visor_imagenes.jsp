
<%@page import="beans.Imagen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.IOException" %>

<%!


	private static final String RUTA = "/JspEjercicio1/src/main/webapp/img";
	Imagen im = new Imagen();
	String bien = im.imagenesDeCarpeta("img");

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visor de imágenes</title>
    </head>
    <body>
    <p>Visor de imágenes funciona</p>
  		<form method="post" >
  			<label for="combo">Imagen:</label>
  			<select name="imagenes">
  				
  			</select>
  		</form>
        
 
    </body>
</html>
