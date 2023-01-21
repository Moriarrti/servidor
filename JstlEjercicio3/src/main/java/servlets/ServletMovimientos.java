package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cuenta;

/**
 * Servlet implementation class ServletMovimientos
 */
@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		Cuenta cuenta = (Cuenta) session.getAttribute("cuentaNueva");
		
		if(request.getParameter("ingresar") != null) {
			float ingreso = Float.parseFloat(request.getParameter("ingresosGastos"));
			cuenta.ingresar(ingreso);
			response.sendRedirect("movimientos.jsp");
		}
		else if(request.getParameter("gastar") != null) {
			float gasto = Float.parseFloat(request.getParameter("ingresosGastos"));
			if(cuenta.gastar(gasto)) {
				response.sendRedirect("movimientos.jsp");
			}
			else {
				String errorMsg = "La cuenta solo dispone de " + cuenta.getSaldo();
				response.sendRedirect("movimientos.jsp?error=" + errorMsg);
			}
		}
	}

}
