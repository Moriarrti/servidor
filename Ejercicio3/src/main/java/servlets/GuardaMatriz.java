package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="GuardaMatriz", urlPatterns = {"/GuardaMatriz"})
public class GuardaMatriz extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int filas = Integer.parseInt(request.getParameter("filas"));
		int columnas = Integer.parseInt(request.getParameter("columnas"));
		int[][] matriz = new int [filas][columnas];
		try {
        	out.println("<!DOCTYPE html>");
        	out.println("<html>");
        	out.println("<body>");
			out.println("Funciona GuardaMatriz");
			out.println("filas:" + filas);
			out.println("columnas:" + columnas);
			for(int i = 1; i < matriz.length; i++) {
				for(int j = 1; j < matriz[i].length; j++) {
					String celda = "celda" + i + "-" + j;
					matriz[i-1][j-1] = Integer.parseInt(request.getParameter(celda));
				}
			}
				
		} 
		catch (NumberFormatException e) {
			out.println("Debes rellenar correctamente la matriz");
//			response.sendRedirect("IntroCeldas");
		}
		finally {
        	out.println("</body>");
        	out.println("</html>");
		}
		

		
	}

}
