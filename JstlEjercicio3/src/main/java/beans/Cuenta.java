package beans;

public class Cuenta {
	private String titular;
	private float saldo;
	
	
	public boolean gastar(float cantidad) {
		boolean operacionAceptada = false;
		if(cantidad <= getSaldo()) {
			setSaldo(getSaldo() - cantidad);
			operacionAceptada = true;
		}
		return operacionAceptada;
	}
	
	public void ingresar(float cantidad) {
		setSaldo(getSaldo() + cantidad);	
	}
	
	
	
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	
	
}
