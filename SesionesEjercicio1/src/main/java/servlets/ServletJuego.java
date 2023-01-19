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
		int vidas;

		
		// Si no existe palabra en session, la carga y la mete en session,
		// si existe la coge de session
		if(session.getAttribute("palabra") == null) {
			String[] listaPalabras = AlmacenPalabras.getPalabras();
			int posicionAleatoria = (int) (Math.random()*listaPalabras.length);
			palabra = listaPalabras[posicionAleatoria];
			session.setAttribute("palabra", palabra);	
		}
		else {
			palabra = (String) session.getAttribute("palabra");
		}
		
		// Array con las letras de la palabra
		letrasPalabra = new char[palabra.length()];
		for(int i = 0; i < letrasPalabra.length; i++) {
			letrasPalabra[i] = palabra.charAt(i); 
		}
		

		
		try {
	    	out.println("<!DOCTYPE html>");
	    	out.println("<html>");
	    	out.println("<body>");
			out.println("<h1>Juego adivina la palabra</h1>");
//			out.println("<p>"+ palabra + "</p>");
			
			// Si se termina el juego muestra que se ha ganado y un enlace para reiniciarlo
			if(request.getParameter("juegoTerminado") != null) {
				if(request.getParameter("juegoTerminado").equals("gana")) {
					out.println("<p>" + palabra + "</p>");
					out.println("<p>Has ganado el juego</p>");
					session.invalidate();
					out.println("<a href='ServletJuego'>Volver a jugar</a>");					
				}
				else if(request.getParameter("juegoTerminado").equals("pierde")){
					out.println("<p>" + palabra + "</p>");
					out.println("<p>Has perdido</p>");
					session.invalidate();
					out.println("<a href='ServletJuego'>Volver a jugar</a>");	
				}
			}
			else { 	// Si no se termina el juego muestra la pantalla de juego
				if(session.getAttribute("letrasDestapadas") != null) {
					letrasDestapadas = (HashMap<Integer, Character>) session.getAttribute("letrasDestapadas");					
				}
				else {
					letrasDestapadas = new HashMap<Integer, Character>();
				}
				out.println("<table border='1' style=\'border-collapse:collapse;\'>");
				out.println("<tr>");
				for(int i = 0; i < letrasPalabra.length; i++) {
					out.println("<td style=\'width:40px;text-align:center\'>");
					if(letrasDestapadas.containsKey(i) && letrasDestapadas.get(i) == letrasPalabra[i]) {
						out.println("<p>" + letrasPalabra[i] +"</p>");					
					}
					else {
						out.println("<a href='ServletComprobar?posicion=" + i + "&letra=" + letrasPalabra[i] + "'>Ver</a>");					
					}
					out.println("</td>");
				}
				if(session.getAttribute("vidas") != null) {
					vidas = (int) session.getAttribute("vidas");					
				}
				else {
					vidas = palabra.length()/2;
					session.setAttribute("vidas", vidas);
				}
				out.println("</tr>");
				out.println("</table>");
				out.println("<ul>");
				out.println("<li>");
				out.println( vidas + " vidas restantes");
				out.println("</li>");
				out.println("<li>");
				out.println("<form method='post' action='ServletComprobar'>");
				out.println("<label for='palabraProbada'>Tu respuesta</label>");
				out.println("<input type='text' name='palabraProbada'>");
				out.println("<input type='submit' value='Comprobar'>");
				out.println("</form>");
				out.println("</li>");
				out.println("</ul>");						
			}
			
		} 
		finally {
        	out.println("</body>");
        	out.println("</html>");
		}
		
		

	}


}
