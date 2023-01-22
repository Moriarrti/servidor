package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Primitiva {
	
	private Fecha fecha;
	private int[] numerosGanadores = new int[6];
	private int reintegroGanador;
	
	public Primitiva(Fecha fecha) {
		if(fecha.correcta()) {
			this.fecha = fecha;			
		}
		HashSet<Integer> numeros = new HashSet<Integer>();
		while(numeros.size() < 6) {
			int numeroAleatorio = (int) (Math.random()*49 + 1);
			numeros.add(numeroAleatorio);
		}
		int i = 0;
		for(Integer num: numeros) {
			numerosGanadores[i] = (int) num;
			i++;
		}
		ordenarNumeros(numerosGanadores);
		reintegroGanador = (int) (Math.random()*49 + 1);
	}
	
	public void ordenarNumeros(int[] numeros) {
		Arrays.sort(numeros);
	}

	private boolean numTocado(int num) {
		for (int i = 0; i < numerosGanadores.length; i++) {
			if(numerosGanadores[i] == num) {
				return true;
			}
		}
		return false;
	}
	
	public String resultApuesta(Apuesta apuesta) {
		ArrayList<Integer> numerosAcertados = new ArrayList<Integer>();
		String mensaje = "";
		int[] numerosApuesta = apuesta.getNumeros();

		for (int i = 0; i < numerosGanadores.length; i++) {
			int numeroGanador = numerosGanadores[i];
			for (int j = 0; j < numerosApuesta.length; j++) {
				int numeroApostado = numerosApuesta[j];
				if(numeroGanador == numeroApostado) {
					numerosAcertados.add(numeroApostado);
				}
			}
		}
		if(numerosAcertados.size() == 0) {
			mensaje = "Sin números acertados";
		}
		else {
			mensaje = "Aciertos: ";
			for(Integer num: numerosAcertados) {
				mensaje += num + " ";
			}
		}
		if(apuesta.getReintegro() == reintegroGanador) {
			mensaje += ". Con reintegro";
		}
		else {
			mensaje += ". Sin reintegro";
		}
		return mensaje;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public int[] getNumerosGanadores() {
		return numerosGanadores;
	}

	public void setNumerosGanadores(int[] numerosGanadores) {
		this.numerosGanadores = numerosGanadores;
	}

	public int getReintegroGanador() {
		return reintegroGanador;
	}

	public void setReintegroGanador(int reintegroGanador) {
		this.reintegroGanador = reintegroGanador;
	}
	
	
	
//	public static void main(String[] args) {
//		Fecha f = new Fecha(0, 0, 0);
//		
//		for (int i = 0; i < 20; i++) {
//			Primitiva p = new Primitiva(f);
//			for (int j = 0; j < p.numerosGanadores.length; j++) {
//				System.out.print(p.numerosGanadores[j] + " ");
//			}
//			System.out.println();
//		}
//	}
}
