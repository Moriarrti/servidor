
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Imagen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.IOException" %>

<!-- 	final String  RUTA = getServletContext().getRealPath("img"); -->
<%
	final String  RUTA = "img";
	Imagen img = new Imagen();
	ArrayList<Imagen> imagenes = new ArrayList<Imagen>();
	imagenes = img.imagenesDeCarpeta(getServletContext().getRealPath(RUTA));


%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visor de imágenes</title>
    </head>
    <body>
    <h1>Visor de imágenes</h1>
    	<fieldset>
  		<form method="post" action="visor_imagenes.jsp">
  			<label for="combo">Imagen:</label>
  			<select name="imagenEnviada">
  				<%
  			  	String seleccionado = "";
  			  		Imagen imgEnviada = new Imagen();
  			  	if(request.getParameter("imagenEnviada") != null){
  			  		String rutaEnviada = request.getParameter("imagenEnviada"); 
  			  		imgEnviada.setRuta(rutaEnviada);
  			  	}

  				for(Imagen imagen: imagenes){
  					if(imagen.equals(imgEnviada)){
  						seleccionado = "selected";
  					}
  					else {
  						seleccionado = "";
  					}
  					out.println("<option value='" + imagen.getRuta() + "'" + seleccionado + ">");
  					out.println(imagen.getNombre());
  					out.println("</option>");
  				}
  				%>
  			</select>

  			<label>Tamaño:</label>
  			<%
			String marcado300 = "checked", marcado400 = "", marcado500 = "";
  			if(request.getParameter("imagenEnviada") != null){
  				String ancho = request.getParameter("ancho");
  				if(ancho.equals("400")){
  					marcado400 = "checked";
  					marcado300 = "";
  				}
  				else if(ancho.equals("500")){
  					marcado500 = "checked";
  					marcado300 = "";
  				}
  			}
  			%>
  			<input type="radio" name="ancho" value="300" <%= marcado300 %>> 300px
  			<input type="radio" name="ancho" value="400" <%= marcado400 %>> 400px
  			<input type="radio" name="ancho" value="500" <%= marcado500 %>> 500px
  			<input type="submit" value="VER IMAGEN">
  		</form>
  		</fieldset>
  		<br>
  		
        <%
        if(request.getParameter("imagenEnviada") != null){
     		String rutaEnviada = request.getParameter("imagenEnviada"); 
      		String ancho = request.getParameter("ancho");
      		imgEnviada.setRuta(rutaEnviada);
        	int posicionImagenEnArray = imagenes.indexOf(imgEnviada);
        	imgEnviada.setTamanio(imagenes.get(posicionImagenEnArray).getTamanio());
        	String tamanioDesglosado = imgEnviada.tamanioDesglosado(imgEnviada.getTamanio());
			out.println("<p><b>Tamaño</b>   " + tamanioDesglosado + "</p>" );
        	out.println("<img src='" + rutaEnviada + "' width='"+ ancho + "'>");
        }
        %>
 
    </body>
</html>
