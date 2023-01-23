package beans;

import java.util.Date;

public class Pedido {
	
	private int id;
	private double importeTotal;
	private Date fechaPedido;
	private Cliente cliente;
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(float importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}
