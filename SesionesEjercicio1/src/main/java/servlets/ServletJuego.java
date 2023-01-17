package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AlmacenPalabras;

public class ServletJuego extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<Integer, Character> letrasDestapadas;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		char[] letrasPalabra;
		String palabra;
		char letra;
		int posicion;
		int vidas;
		if(session.getAttribute("palabra") == null) {
			String[] listaPalabras = AlmacenPalabras.getPalabras();
			int posicionAleatoria = (int) (Math.random()*listaPalabras.length);
			palabra = listaPalabras[posicionAleatoria];
			session.setAttribute("palabra", palabra);	
		}
		else {
			palabra = (String) session.getAttribute("palabra");
		}
		
		letrasPalabra = new char[palabra.length()];
		for(int i = 0; i < letrasPalabra.length; i++) {
			letrasPalabra[i] = palabra.charAt(i); 
		}
		
		if(request.getParameter("posicion") != null) {
			posicion = Integer.parseInt(request.getParameter("posicion"));
			letra = request.getParameter("letra").charAt(0);
			letrasDestapadas.put(posicion, letra);
			session.setAttribute("letrasDestapadas", letrasDestapadas);
			vidas = (int) session.getAttribute("vidas");
			vidas--;
			session.setAttribute("vidas", vidas);
			if(vidas < 1) {
				
			}
		}
		else {
			letrasDestapadas = new HashMap<Integer, Character>();
			vidas = palabra.length()/2;
			session.setAttribute("vidas", vidas);
		}
		
		try {
	    	out.println("<!DOCTYPE html>");
	    	out.println("<html>");
	    	out.println("<body>");
			out.println("<h1>Juego adivina la palabra</h1>");
			out.println("<p>" + palabra + "</p>");
			out.println("<table border='1' style=\'border-collapse:collapse;\'>");
			out.println("<tr>");
			for(int i = 0; i < letrasPalabra.length; i++) {
				out.println("<td style=\'width:40px;text-align:center\'>");
				if(letrasDestapadas.containsKey(i) && letrasDestapadas.get(i) == letrasPalabra[i]) {
					out.println("<p>" + letrasPalabra[i] +"</p>");					
				}
				else {
					out.println("<a href='ServletJuego?posicion=" + i + "&letra=" + letrasPalabra[i] + "'>Ver</a>");					
				}
				out.println("</td>");
			}
			int v = (int) session.getAttribute("vidas");
			out.println("</tr>");
			out.println("</table>");
			out.println("<ul>");
			out.println("<li>");
			out.println( v + " vidas restantes");
			out.println("</li>");
			out.println("<li>");
			out.println("<form method='post' action='ServletComprobar'>");
			out.println("<label for='palabraProbada'>Tu respuesta</label>");
			out.println("<input type='text' name='palabraProbada'>");
			out.println("<input type='submit' value='Comprobar'>");
			out.println("</form>");
			out.println("</li>");
			out.println("</ul>");
			out.println();
			
		} 
		finally {
        	out.println("</body>");
        	out.println("</html>");
		}
		
		

	}


}
