package beans;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Imagen {
	
	private String ruta, nombre;
	private int tamanio;
	
	public Imagen() {
		
	}
	
	public Imagen(String ruta) {
		this.ruta = ruta;
	}
	
	public ArrayList<Imagen> imagenesDeCarpeta(String nomcarpeta) {
		ArrayList<Imagen> listaImagenes = new ArrayList<Imagen>();
		File carpetaImagenes = new File(nomcarpeta);
		String[] arrRutas = carpetaImagenes.list();
		String[] partesCarpeta = nomcarpeta.split("\\\\");
		String carpeta = partesCarpeta[partesCarpeta.length -1];
		for(String ruta: arrRutas) {
			Imagen img = new Imagen();
			String[] partes = ruta.split("\\.");
			String nombre = partes[0];
			String rutaImagen = carpeta + "\\" + ruta;
			File f = new File(nomcarpeta + "\\" + ruta);
			img.setNombre(nombre);
			img.setRuta(rutaImagen);
			int tamanio = (int) f.length();
			img.setTamanio(tamanio);
			listaImagenes.add(img);
		}
		return listaImagenes;
	}
	
	
	public String tamanioDesglosado(int tamanioBytes) {
		String tamanio = "";
		int megabytes = tamanioBytes / (1024 * 1024);
		int kilobytes = tamanioBytes % (1024 * 1024) / 1024;
		int bytes = tamanioBytes % (1024 * 1024) % 1024;
		tamanio = megabytes + " Mb " + kilobytes + " Kb " + bytes + " bytes ";
		return tamanio;
	}
	
	
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTamanio() {
		return tamanio;
	}
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}


	@Override
	public String toString() {
		return "Imagen [ruta=" + ruta + ", nombre=" + nombre + ", tamanio=" + tamanio + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(ruta);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imagen other = (Imagen) obj;
		return Objects.equals(ruta, other.ruta);
	}



	
	

	
	


	
}