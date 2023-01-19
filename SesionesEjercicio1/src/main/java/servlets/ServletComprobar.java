package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletComprobar extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HashMap<Integer, Character> letrasDestapadas;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		int vidas;
		char letra;
		int posicion;
		String palabraProbada;
		
		if(request.getParameter("palabraProbada") != null) {
			palabraProbada = request.getParameter("palabraProbada").toLowerCase();
			String palabraOriginal = (String) session.getAttribute("palabra");
			palabraOriginal = palabraOriginal.toLowerCase();
			if(palabraProbada.equals(palabraOriginal)) {
//				System.out.println(palabraProbada);			
				response.sendRedirect("ServletJuego?juegoTerminado=gana");
			}
			else {
				vidas = (int) session.getAttribute("vidas");
				vidas--;
				session.setAttribute("vidas", vidas);
				response.sendRedirect("ServletJuego");
			}
		}
		
		if(request.getParameter("posicion") != null) {
			posicion = Integer.parseInt(request.getParameter("posicion"));
			letra = request.getParameter("letra").charAt(0);
			if(session.getAttribute("letrasDestapadas") != null) {
				letrasDestapadas = (HashMap<Integer, Character>) session.getAttribute("letrasDestapadas");
			}
			else {
				letrasDestapadas = new HashMap<Integer, Character>();
			}
			letrasDestapadas.put(posicion, letra);
			session.setAttribute("letrasDestapadas", letrasDestapadas);
			vidas = (int) session.getAttribute("vidas");
			vidas--;
			session.setAttribute("vidas", vidas);
			if(vidas < 1) {
				response.sendRedirect("ServletJuego?juegoTerminado=pierde");
			}
			else {
				response.sendRedirect("ServletJuego");				
			}
		}

		
	}
}
