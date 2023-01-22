package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Libro;
import dao.GestorBD;

/**
 * Servlet implementation class ServletAutores
 */
@WebServlet("/ServletAutores")
public class ServletAutores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GestorBD bd;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bd = new GestorBD();    
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();		
		session.setAttribute("autoresCompletos", bd.getAutoresCompletos());
		HashMap<String, ArrayList<Libro>> autorLibros = new HashMap<String, ArrayList<Libro>>();
		String errAniadir = "";
		if(request.getParameter("idAutorLibros") != null) {
			int id = Integer.parseInt(request.getParameter("idAutorLibros"));
			String nombre = request.getParameter("nomAutorLibros");
			ArrayList<Libro> librosAutor = bd.librosDelAutor(id);
			autorLibros.put(nombre, librosAutor);
			session.setAttribute("librosAutor", autorLibros);
			
			if(request.getParameter("prestar") != null) {
				int idPrestar = Integer.parseInt(request.getParameter("prestar"));
				bd.prestarLibro(idPrestar);
			}
			
		}
		if(request.getParameter("aniadir") != null) {
			
			String nombre = request.getParameter("nombre");
			String fecha = request.getParameter("fecha");
			String nacionalidad = request.getParameter("nacionalidad");
			if(nombre.equals("") || fecha.equals("") || nacionalidad.equals("")) {
				errAniadir = "Debes completar todos los campos";
			}
			else {
				String msgAniadirAutor = bd.insertarAutor(nombre, fecha, nacionalidad);
				session.setAttribute("msgAniadirAutor", msgAniadirAutor);
			}
			
		}
		response.sendRedirect("autores.jsp");
	}

}
