package beans;

public class Fecha {

	private int dia, mes, anio;
	
	public Fecha(int dia, int mes, int anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}
	
	public boolean correcta(){
		if(dia < 1 || dia > 31 ) {
			return false;
		}
		if(mes < 1 || mes > 12) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return dia + "/" + mes + "/" + anio;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}


	
	
}
