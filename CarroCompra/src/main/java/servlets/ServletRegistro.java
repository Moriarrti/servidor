package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cliente;
import dao.ClienteDAO;
import dao.KeysDAO;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ClienteDAO cdao = new ClienteDAO();
    KeysDAO kdao = new KeysDAO();
    public ServletRegistro() {
    	super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		int idCliente = kdao.siguienteId("clientes");
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String domicilio = request.getParameter("domicilio");
		String zip = request.getParameter("zip");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		
//		int maxClientes = Integer.parseInt(getInitParameter("maxClientes"));
		if(cdao.buscaCliente(usuario) || cdao.cuentaClientes() == 10) { // como da null, pongo el valor de la variable de web.xml
			response.sendRedirect("registro.jsp?errorRegistro=true");
		}
		else {
			Cliente cliente = new Cliente(idCliente, usuario, password, domicilio, zip, telefono, email);
			if(cdao.guardaCliente(cliente)) {
				response.sendRedirect("login.jsp?registroCorrecto=true");
			}
		}

//		int id, String nombre, String password, String domicilio, String codigopostal, String telefono,
//		String email
	}

}
