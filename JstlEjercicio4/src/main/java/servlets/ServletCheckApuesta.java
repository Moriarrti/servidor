package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Apuesta;
import beans.BadBetException;
import beans.Primitiva;

/**
 * Servlet implementation class ServletCheckApuesta
 */
@WebServlet("/ServletCheckApuesta")
public class ServletCheckApuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
      


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException {
		HttpSession session = request.getSession();
		String msgAciertos = "";
		
		if(request.getParameter("comprobar") != null) {
			try {
				String apostante = request.getParameter("nombre");
				int numAdmin = Integer.parseInt(request.getParameter("numAdmin"));
				int reintegro = Integer.parseInt(request.getParameter("reintegro"));
				int [] numeros = new int[6];
				for (int i = 1; i <= 6; i++) {
					String num = "num" + i;
					numeros[i-1] = Integer.parseInt(request.getParameter(num));
				}
				
				Apuesta apuesta = new Apuesta(apostante, numAdmin, numeros, reintegro);
				Primitiva primitiva = (Primitiva) session.getAttribute("primitiva");
				msgAciertos = primitiva.resultApuesta(apuesta);
			} catch (BadBetException  e) {
				// TODO Auto-generated catch block
				msgAciertos = e.getMessage();
			} catch(NumberFormatException e) {
				msgAciertos = "Debes rellenar todos los campos"; 
			}
			response.sendRedirect("check_apuestas.jsp?msg="+msgAciertos);
		}
	}

}

//Apuesta(String apostante, int numAdmin, int[] numeros, int reintegro)