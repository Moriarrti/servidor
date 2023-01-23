package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CarroCompra;

/**
 * Servlet implementation class SevletAgregarLineaPedido
 */
@WebServlet("/SevletAgregarLineaPedido")
public class SevletAgregarLineaPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		
		if(session.getAttribute("carroCompra") == null) {
			
			
			session.setAttribute("carroCompra", new CarroCompra());
		}
		else {
			
		}
	}

}
