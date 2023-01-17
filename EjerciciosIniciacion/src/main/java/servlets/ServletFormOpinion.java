package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/ServletFormOpinion")
public class ServletFormOpinion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	private ArrayList<String> cargarSecciones() {
		ArrayList<String> secciones = new ArrayList<String>();
		String ruta = getServletContext().getRealPath("secciones.txt");
		File archEntrada = new File(ruta);
		System.out.println(ruta);
		try { 
			BufferedReader br = new BufferedReader(new FileReader(archEntrada));
			String seccion = br.readLine();
			while(seccion != null) {
				secciones.add(seccion);
				seccion = br.readLine();
			}
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return secciones;
	}
	
	private void guardarSeccionesFavoritas(String nombre, String apellido, String[] secciones ) {
		
		File archSalida = new File("C:\\Users\\Mori\\Desktop\\Servidor\\EjerciciosIniciacion\\src\\main\\webapp\\seccionesfavoritas.txt");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(archSalida, true));
			String linea = nombre + " " + apellido + ": ";
			for(int i = 0; i < secciones.length; i++) {
				if(i != secciones.length-1) {
					linea += secciones[i] + ", ";
				}
				else {
					linea += secciones[i] + ".";
				}
			}

			bw.write(linea);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String opinion = request.getParameter("opinion");
		String[] seccionesElegidas = request.getParameterValues("seccionesElegidas");
		ArrayList<String> secciones = cargarSecciones();
		PrintWriter out = response.getWriter();
		try  {
			
			if(nombre != null && apellidos != null &&  opinion != null) {
				if(opinion.equals("B")) {
					guardarSeccionesFavoritas(nombre, apellidos, seccionesElegidas);
				}
			}

		
        	out.println("<!DOCTYPE html>");
        	out.println("<html>");
        	out.println("<body>");
        	out.println("<h1>Ejercicio 2</h1>");
        	
        	out.println("<form method='post' action='/EjerciciosIniciacion/ServletFormOpinion'>");
        	out.println("<label for='nombre'>Nombre:</label>");
        	out.println("<input type='text' name='nombre' required>");
        	out.println("<br><br>");
        	out.println("<label for='apellidos'>Apellidos:</label>");
        	out.println("<input type='text' name='apellidos' required>");
        	out.println("<br><br>");
        	out.println("<label for='opinion'>Opinion que le ha merecido este sitio web:</label>");
        	out.println("<br><br>");
        	out.println("<input type='radio' name='opinion' value='B' required>Buena");
        	out.println("<br>");
        	out.println("<input type='radio' name='opinion' value='R' required>Regular");
        	out.println("<br>");
        	out.println("<input type='radio' name='opinion' value='M' required>Mala");
        	out.println("<br><br>");
        	out.println("<label for='area'>Comentarios</label>");
        	out.println("<br><br>");
        	out.println("<textarea cols='30' rows='10'></textarea><br>");
        	
        	
        	
        	out.println("<p><b>Tus secciones favoritas</b></p>");
        	for(String seccion: secciones) {
				out.println("<input type='checkbox' name='seccionesElegidas' value='" + seccion + "'/>"
						+ "<label for='seccionesElegidas'>" + seccion + "</label><br>");
        	}
        	out.println("<br>");
        	out.println("<input type='submit' value='Enviar opinion' name='enviar_opinion'>");
        	out.println("</form>");
        	
        	out.println("</body>");
        	out.println("</html>");
				
		}
		finally {
			out.close();
		}
	}


}
