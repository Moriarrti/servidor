package beans;

import java.util.Arrays;

public class Apuesta {
	private String apostante;
	private int numAdmin, reintegro;
	private int[] numeros;
	
	public Apuesta(String apostante, int numAdmin, int[] numeros, int reintegro) throws BadBetException  {
		if(numeros.length != 6) {
			throw new BadBetException("Los números no son 6");
		}
		for (int i = 0; i < numeros.length; i++) {
			if(numeros[i]<1 || numeros[i]>49) {
				throw new BadBetException("Los numeros deben estar entre 1 y 49");
			}
			for (int j = i + 1; j < numeros.length -1; j++) {
				if(numeros[i] == numeros[j]) {
					throw new BadBetException("No puede haber numeros repetidos");
				}
			}
		}
		if(reintegro < 1 || reintegro > 49) {
			throw new BadBetException("El reintegro debe estar entre 1 y 49");
		}
		this.apostante = apostante;
		this.numAdmin = numAdmin;
		this.numeros = numeros;
		this.reintegro = reintegro;
		
	}
	
	
	
	
	
	

	@Override
	public String toString() {
		return "Apuesta [apostante=" + apostante + ", numAdmin=" + numAdmin + ", reintegro=" + reintegro + ", numeros="
				+ Arrays.toString(numeros) + "]";
	}







	public String getApostante() {
		return apostante;
	}

	public void setApostante(String apostante) {
		this.apostante = apostante;
	}

	public int getNumAdmin() {
		return numAdmin;
	}

	public void setNumAdmin(int numAdmin) {
		this.numAdmin = numAdmin;
	}

	public int getReintegro() {
		return reintegro;
	}

	public void setReintegro(int reintegro) {
		this.reintegro = reintegro;
	}

	public int[] getNumeros() {
		return numeros;
	}

	public void setNumeros(int[] numeros) {
		this.numeros = numeros;
	}
	
	
	

}
