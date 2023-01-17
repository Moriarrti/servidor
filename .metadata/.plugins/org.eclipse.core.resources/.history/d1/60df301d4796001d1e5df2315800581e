package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Catalogo;

public class ServletLibros extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> catalogoLibros = new ArrayList<String>();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(request.getParameter("borraSesion") != null) {
			session.invalidate();
		}
		
		doPost(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		String[] listaLibros = Catalogo.getLibros();
		String libro =  request.getParameter("libros");
		boolean libroRepetido = false;

		
		if(libro != null) {
			session.setAttribute("ultimoLibro", libro);
			if(!catalogoLibros.contains(libro)) {
				catalogoLibros.add(libro);
				session.setAttribute("Libros", catalogoLibros);				
			}
			else {
				libroRepetido = true;
			}
		}
		
		try {
			String ultimoLibro = (String) session.getAttribute("ultimoLibro");
        	out.println("<!DOCTYPE html>");
        	out.println("<html>");
        	out.println("<body>");
			out.println("<h1>Elección de libros</h1>");
			if(libroRepetido == true) {
				out.println("<p>Ya has elegido " + ultimoLibro + "</p>");
			}
			out.println("<form method='post' action='ServletLibros'>");
			out.println("<select name='libros' value='libros'>");
			for(String libroLista: listaLibros) {
				if(libroLista.equals(ultimoLibro)) {
					out.println("<option value='" + libroLista + "' selected>" + libroLista + "</option>");						
				}
				else {
					out.println("<option value='" + libroLista + "'>" + libroLista + "</option>");						
				}
			}
			out.println("</select>");
			out.println("<input type='submit' value='AGREGAR' name='agregar'>");
			out.println("</form>");
			ArrayList<String> listaLibrosElegidos = (ArrayList<String>) session.getAttribute("Libros");
			if(listaLibrosElegidos != null) {
				out.println("<ul>");
				
				for(String libroElegido: listaLibrosElegidos) {
					out.println("<li>" + libroElegido + "</li>");				
				}
				
				out.println("</ul>");
				out.println("<br><form method='get' action='ServletLibros'>");
				out.println("<input type='submit' name='borraSesion' value='REINICIAR' >");
			}
			
			
		} 
		finally {
			out.println("</form>");
        	out.println("</body>");
        	out.println("</html>");
		}
	}
	

}
