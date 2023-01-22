package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cuenta;

/**
 * Servlet implementation class ServletNuevaCuenta
 */
@WebServlet("/ServletNuevaCuenta")
public class ServletNuevaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final List<String> CLIENTES_VETADOS = new ArrayList<String>(List.of("Julio", "Vicente")) ;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String errorMsg = "";
		if(request.getParameter("titular") != null && request.getParameter("saldoInicial") != null) {
			String titular = request.getParameter("titular");
			String saldoInicialString = request.getParameter("saldoInicial");
			float saldoInicial;
			if(saldoInicialString.isEmpty()) {
				saldoInicial = 0;
			}
			else {
				saldoInicial = Float.parseFloat(saldoInicialString);
			}
			if(titular.equals("")) {
				errorMsg = "El titular no puede estar vacio";	
				response.sendRedirect("nuevacuenta.jsp?error=" + errorMsg);
			}
			else if(saldoInicial <= 0) {
				errorMsg = "El saldo inicial debe ser mayor que cero";
				response.sendRedirect("nuevacuenta.jsp?error=" + errorMsg);
			}
			else if(CLIENTES_VETADOS.contains(titular)) {
				errorMsg =  titular + " no puede crear cuentas";
				response.sendRedirect("nuevacuenta.jsp?error=" + errorMsg);
			}
			else {
				Cuenta cuentaNueva = new Cuenta();
				cuentaNueva.setTitular(titular);
				cuentaNueva.setSaldo(saldoInicial);
				System.out.println(saldoInicial);
				session.setAttribute("cuentaNueva", cuentaNueva);
				response.sendRedirect("movimientos.jsp");
			}
		}
	
		
	}

}
