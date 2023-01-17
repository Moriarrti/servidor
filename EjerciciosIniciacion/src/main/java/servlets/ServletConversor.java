package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ConversionCF;


//@WebServlet(name="ServletConversor", urlPatterns = {"/conversion/*", "*.convertirtemperatura"})


public class ServletConversor extends HttpServlet{
	

	private static final long serialVersionUID = 1L; 
	

	private static HashSet <Locale> regiones = new HashSet<Locale>();
	
	
	private String comprobarError(String tipoGrados, String cel, String fah) {
		String mensaje = "";
		if(tipoGrados.equals("c")) {
			if(cel.equals("")) {
				mensaje = "Debes indicar los grados Celsius";
			}
		}
		else {
			if(fah.equals("")) {
				mensaje = "Debes indicar los grados Fahrenheit";
			}
		}
		return mensaje;
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         
        PrintWriter out = response.getWriter();
        try {

        	Locale local = request.getLocale();
        	regiones.add(local);

        	
        	String celToFah = request.getParameter("Cel-Fah");
        	String fahToCel = request.getParameter("Fah-Cel");
        	String tipo;
        	String mensajeError = "";

        	
        	String cel = request.getParameter("celsius");
        	String fah = request.getParameter("fahrenheit");
        	float fahrenheit = 0;
        	float celsius = 0;
        	
        	if(cel.equals("") && fah.equals("")) {
        		mensajeError = "Debes introducir algún dato";
        	}
        	else if(cel.equals("")) {
        		fahrenheit = Float.parseFloat(fah);   
        	}
        	else if (fah.equals("")) {
        		celsius = Float.parseFloat(cel);   
        	}
        	
            if(celToFah == null) {
            	tipo = "f";
            	ConversionCF conversor = new ConversionCF(tipo, fahrenheit);
            	celsius = conversor.convertir(conversor.getTipo(), conversor.getTemperatura());     			
            }
            else {
            	tipo = "c";
            	ConversionCF conversor = new ConversionCF(tipo, celsius);
            	fahrenheit = conversor.convertir(conversor.getTipo(), conversor.getTemperatura());
            }
            
            if(mensajeError.equals("")){
            	mensajeError = comprobarError(tipo, cel, fah);            	
            }
        	
        	
        	response.setContentType("text/html;charset=UTF-8");
        	out.println("<!DOCTYPE html>");
        	out.println("<html>");
        	out.println("<body>");
        	
        	if(mensajeError.equals("")) {
        		out.println("<h2>Resultado de la conversión:</h2>");
	        	out.println("<p> <b>Valor en Celsius:</b> " + celsius + "</p>");
	        	out.println("<p> <b>Valor en Fahrenheit:</b> " + fahrenheit + "</p>");
        	}
        	else {
        		out.println("<p> <b>ERROR:</b> " + mensajeError + "</p>");
        		
        	}
        	
        	out.println("<a href='conversorCF.html'>Enlace para volver al formulario</a>");
        	out.println("<p>Se han establecido conexiones desde " + regiones.size() +" distintos locale</p>");

        }
        finally {
        	out.println("</body>");
        	out.println("</html>");
		}
    }

}
