/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

import java.util.Date;

/**
 *
 * @author Amaia
 */
public class Autor implements Serializable{
    private int idAutor;
    private String nombre;
    private Date fechanac;
    private String nacionalidad;

    public Autor(int idAutor, String nombre, Date fechanac, String nacionalidad) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.fechanac = fechanac;
        this.nacionalidad = nacionalidad;
    }

    public Autor() {
    }
    
    


    public int getIdAutor() {
        return idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

	@Override
	public String toString() {
		return "Autor [idAutor=" + idAutor + ", nombre=" + nombre + ", fechanac=" + fechanac + ", nacionalidad="
				+ nacionalidad + "]";
	}
	

    
}

