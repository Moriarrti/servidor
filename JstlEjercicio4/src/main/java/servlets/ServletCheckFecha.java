package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Fecha;
import beans.Primitiva;

/**
 * Servlet implementation class ServletCheckFecha
 */
@WebServlet("/ServletCheckFecha")
public class ServletCheckFecha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession() != null) {
			request.getSession().invalidate();
		}
		HttpSession session = request.getSession();
		String msgError = "";

		if(request.getParameter("dia").equals("") || request.getParameter("mes").equals("") || request.getParameter("mes").equals("")) {
			msgError = "Fecha incorrecta, no has introducido todos los campos";
			response.sendRedirect("crear_primitiva.jsp?error=" + msgError);
		}
		else {
			String dia = request.getParameter("dia");
			String mes = request.getParameter("mes");
			String anio = request.getParameter("anio");
			
			Fecha fecha = new Fecha(Integer.parseInt(dia),Integer.parseInt(mes),Integer.parseInt(anio));
			if(fecha.correcta()) {
				Primitiva primitiva = new Primitiva(fecha);
				session.setAttribute("primitiva", primitiva);
				response.sendRedirect("crear_primitiva.jsp");
			}
			else {
				msgError = "Fecha incorrecta, algun campo es incorrecto";
				response.sendRedirect("crear_primitiva.jsp?error=" + msgError);
			}			
		}
		
		
		
	}

}
