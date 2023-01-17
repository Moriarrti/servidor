package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;


@WebServlet(name="IntroCeldas", urlPatterns = {"/IntroCeldas"})
public class IntroCeldas extends HttpServlet{
	
	private static final long serialVersionUID = 1L; 
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doGet(request, response);
//		doPost(request, response);
//	}
	
	
	private void dibujaMatriz(int filas, int columnas, String fondo, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
        	out.println("<!DOCTYPE html>");
        	out.println("<html>");
        	out.println("<body>");
			out.println("<h1>INTRODUCE VALORES:</h1>");
			out.println("<form method='post' action='GuardaMatriz'>");
			out.println("<input type='hidden' value='" + filas +"' name='filas'>" );
			out.println("<input type='hidden' value='" + columnas +"' name='columnas'>" );
			for(int i = 1; i <= filas; i++) {
				for(int j = 1; j <= columnas; j++) {
					out.println("<input type='text'"
							+ "name='celda" + i + "-" + j
							+ "' />");	
				}
				out.println("<br>");
			}
			out.println("<br>");
			out.println("<input type='submit'value='Guardar Matriz'/>");
			out.println("<input type='reset' value='Restablecer'/>");			
			
		}
		finally {
			out.println("</form>");
        	out.println("</body>");
        	out.println("</html>");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		request.setCharacterEncoding("utf-8");
		
		
		

		
        if(request.getParameter("filas").isEmpty() || request.getParameter("columnas").isEmpty()) {         
            response.sendRedirect("index.html");        
        }        
        else {  
    		int filas = Integer.parseInt(request.getParameter("filas"));
    		int columnas = Integer.parseInt(request.getParameter("columnas")); 
    		String fondo = request.getParameter("fondo");
    		dibujaMatriz(filas, columnas, fondo, response);        	
        }		
		
	}
	
	

}
