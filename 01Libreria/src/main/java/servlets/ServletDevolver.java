package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Libro;
import dao.GestorBD;


@WebServlet("/ServletDevolver")
public class ServletDevolver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestorBD bd;
    private ArrayList<Integer> librosDevolver = new ArrayList<Integer>();
    private HttpSession session;
    
    
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
		session = request.getSession();
		if(session.getAttribute("librosPrestados") == null) {
			session.setAttribute("librosPrestados", bd.obtenerLibrosPrestados());
		}
		
		
		if(session.getAttribute("librosDevolver") == null) {
			session.setAttribute("librosDevolver", new ArrayList<Integer>());
		}
		

		if(request.getParameter("devolverLibro") != null) {
			int libroDev = Integer.parseInt(request.getParameter("devolverLibro"));
			librosDevolver = (ArrayList<Integer>) session.getAttribute("librosDevolver");
			librosDevolver.add(libroDev);
			session.setAttribute("librosDevolver", librosDevolver);
			
		}
		
		if(request.getParameter("revertirLibro") != null) {
			Integer libroRev = Integer.parseInt(request.getParameter("revertirLibro"));
			librosDevolver = (ArrayList<Integer>) session.getAttribute("librosDevolver"); 
			librosDevolver.remove(libroRev);
			session.setAttribute("librosDevolver", librosDevolver);
		}
		if(request.getParameter("grabar") != null) {
			librosDevolver = (ArrayList<Integer>) session.getAttribute("librosDevolver");
			bd.devolverLibros(librosDevolver);
//			session.setAttribute("librosPrestados", bd.obtenerLibrosPrestados());
			session.setAttribute("librosDevolver", new ArrayList<Integer>());
		}
		session.setAttribute("librosPrestados", bd.obtenerLibrosPrestados());
		response.sendRedirect("devoluciones.jsp");
	}

	
	
}
