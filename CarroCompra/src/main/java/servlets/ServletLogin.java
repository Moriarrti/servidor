package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.FetchProfile.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cliente;

import dao.ClienteDAO;
import dao.PedidoDAO;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClienteDAO cdao = new ClienteDAO();
    private PedidoDAO pdao = new PedidoDAO();
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("errorLogin", null);
		String usuario = request.getParameter("usuario");
		String contrasenia = request.getParameter("contrasenia");
		Cliente cliente = cdao.buscaCliente(usuario, contrasenia);
		if(cdao.buscaCliente(usuario, contrasenia) != null) {
			ArrayList<beans.Item> listaItems = pdao.todosItems();
			session.setAttribute("listaItems", listaItems);
			response.sendRedirect("tienda.jsp");
		}
		else {
			String msgError = "ERROR: Alguno de los campos no es correcto";
			session.setAttribute("errorLogin", msgError);
			response.sendRedirect("login.jsp");
		}
		
		
	}

}
