package beans;

import java.util.ArrayList;
import java.util.HashMap;

public class CarroCompra {
	
	private HashMap<LineaPedido, Integer> carro;
	
	
	public void aniadeLinea(LineaPedido linea) {
		
	}
	
	public void borraLinea(int idItem) {
		
	}
	
	public LineaPedido getLineaPedido(int idItem) {
		LineaPedido linea = new LineaPedido();
		
		return linea;
	}
	
	public ArrayList<LineaPedido> getLineasPedido(){
		ArrayList<LineaPedido> arr = new ArrayList<LineaPedido>();
		
		return arr;
	}
	
	public double total() {
		double total = 0;
		
		return total;
	}
	
	public void removeAll() {
		
	}
	
	public boolean vacio() {
		return true;
	}
}
