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

/**
 * Servlet implementation class ServletDevolver
 */
@WebServlet("/ServletDevolver")
public class ServletDevolver extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestorBD bd;
    private ArrayList<Integer> librosDevolver = new ArrayList<Integer>();
  
    HttpSession session;
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
		
		LinkedHashMap<Libro, String> i = bd.obtenerLibrosPrestados();
		for(Libro lib: i.keySet()) {
			System.out.println(lib);
			System.out.println(i.get(lib));
			System.out.println("-----------");
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
			session.invalidate();
			session.setAttribute("librosDevolver", new ArrayList<Integer>());
		}
		session.setAttribute("librosPrestados", bd.obtenerLibrosPrestados());
		response.sendRedirect("devoluciones.jsp");
	}

	
	
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		try {
//				InitialContext ctx = new InitialContext();
//				Context env = (Context) ctx.lookup("java:comp/env");
//				// nombre del recurso en el context.xml
//				ds = (DataSource) env.lookup("jdbc/poolSubastasDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
}
