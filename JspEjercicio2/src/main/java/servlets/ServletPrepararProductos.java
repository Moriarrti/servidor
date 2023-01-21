package servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletPrepararProductos
 */
//@WebServlet("/ServletPrepararProductos")
public class ServletPrepararProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String FICHERO_PRODUCTOS = "productos.txt"; 
	
	
	
	
	private void introducirProducto(HashMap<String, ArrayList<String>> datos, String categoria, String producto) {
		ArrayList<String> productos = new ArrayList<String>();
		if(datos.containsKey(categoria)) {
			productos = datos.get(categoria);
			productos.add(producto);
			datos.put(categoria, productos);
		}
		else {
			productos.add(producto);
			datos.put(categoria, productos);
		}
	}
	
	private HashMap<String, ArrayList<String>> cargarDatosFichero(){
		HashMap<String, ArrayList<String>> datos = new HashMap<String, ArrayList<String>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(FICHERO_PRODUCTOS));
			String lineaProducto = br.readLine();
			while(lineaProducto != null) {
				String[] partes = lineaProducto.split(";");
				String categoria = partes[0];
				String producto = partes[1];
				introducirProducto(datos, categoria, producto);
				lineaProducto = br.readLine();
			}
			br.close();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datos;
	}
	
	
	private ArrayList<String> listarProductos(String categoria){
		HashMap<String, ArrayList<String>> todosProductos = cargarDatosFichero();
		ArrayList<String> productos = new ArrayList<String>();
		ArrayList<String> productosDeCategoria = new ArrayList<String>();
		if(categoria.equals("todos")) {
			Iterator it = todosProductos.keySet().iterator();
			while(it.hasNext()) {
				String key = (String) it.next();
				productosDeCategoria = todosProductos.get(key);
				productos.addAll(productosDeCategoria);
			}
		}
		else {
			productosDeCategoria = todosProductos.get(categoria);
			productos.addAll(productosDeCategoria);
			
		}
		
		
		return productos;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at Culoooeee: ").append(request.getContextPath());
		request.getSession().invalidate();
		HttpSession session = request.getSession();
		
		HashMap<String, ArrayList<String>> productosPorCategoria = new HashMap<String, ArrayList<String>>();
		ArrayList<String> productosDeLaCategoriaX = new ArrayList<String>();
		if(request.getParameter("categoria") != null) {
			String categoria = request.getParameter("categoria");
			productosDeLaCategoriaX = listarProductos(categoria);
		}
		else {
			productosDeLaCategoriaX = listarProductos("todos");
		}
		
		session.setAttribute("listaProductos", productosDeLaCategoriaX);
		response.sendRedirect("compra.jsp");
		
	}

	

}
