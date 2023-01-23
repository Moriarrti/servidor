/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import beans.Autor;
import beans.Libro;


/**
 *
 * @author Amaia
 */
public class GestorBD {

    private static DataSource dataSource;

	public GestorBD() {
		try {
			InitialContext ctx = new InitialContext();
			Context env = (Context) ctx.lookup("java:comp/env");
			// nombre del recurso en el context.xml
			dataSource = (DataSource) env.lookup("jdbc/poolBibliotecaDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
    

    
    public ArrayList<Libro> libros(){
        ArrayList<Libro> libros = new ArrayList<Libro>();
        String sql = "SELECT * FROM libro";
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Libro libro = new Libro(rs.getInt("id"), rs.getString("titulo"),
                                        rs.getInt("paginas"), rs.getString("genero"), 
                                        rs.getInt("idAutor"));
                libros.add(libro);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo libros: " + ex);
        }
        return libros;
    }
    
    public LinkedHashMap<Integer, String> autores(){
        LinkedHashMap<Integer, String> autores = new LinkedHashMap<Integer, String>();
        String sql = "SELECT id, nombre FROM autor";
        Connection con;
        try {
            con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                autores.put(rs.getInt("id"), rs.getString("nombre"));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return autores;
    }
    
    public boolean existeLibro(Libro libro){
        boolean existe = false;
        String sql = "SELECT id FROM libro WHERE titulo = '" + libro.getTitulo() +
                "' AND idautor = " + libro.getIdAutor();
        try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                existe = true;
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }
    
    public int insertarLibro(Libro libro){
        int id = -1;
        String sql = "INSERT INTO libro(titulo, paginas, genero, idautor) "
                + " VALUES(?, ?, ?, ?)";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, libro.getTitulo());
            st.setInt(2, libro.getPaginas());
            st.setString(3, libro.getGenero());
            st.setInt(4, libro.getIdAutor());
            
            st.executeUpdate();
            
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo insertarLibro: " + ex);
        }
        
        return id;
    }
    
    public ArrayList< Autor> getAutoresCompletos(){
    	ArrayList< Autor> autores = new ArrayList<Autor>();
    	String sql = "Select * from autor";
    	try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Autor autor = new Autor();
            	autor.setIdAutor(rs.getInt("id"));
            	autor.setNombre(rs.getString("nombre"));
            	autor.setFechanac(rs.getDate("fechanac"));
            	autor.setNacionalidad(rs.getString("nacionalidad"));
                autores.add(autor);
            }
            rs.close();
            st.close();
            con.close();
		} catch (SQLException e) {
			System.err.println("Error en metodo getAutoresCompletos");
		}
    	return autores;
    }
    
    public String getNombreAutor(int id) {
    	String nombre = "";
    	String sql = "select nombre from autor where id=" + id;
    	try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
            	nombre = rs.getString("nombre");
            }
            rs.close();
            st.close();
            con.close();       
		} catch (SQLException e) {
			System.err.println("Error en metodo getNombreAutor " + id);
		}
    	return nombre;
    }
    
    public ArrayList<Libro> librosDelAutor(int id){
    	ArrayList<Libro> libros = new ArrayList<Libro>();
    	String sql = "Select * from libro where idautor=" + id;
    	try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Libro libro = new Libro();
            	String titulo = rs.getString("titulo");
            	libro.setTitulo(titulo);
            	libro.setIdLibro(id);
            	libros.add(libro);
            }
            rs.close();
            st.close();
            con.close();       
		} catch (SQLException e) {
			System.err.println("Error en metodo librosDelAutor " + id);
		}
    	return libros;
    }
    
    
    public int prestamoMasAlto() {
    	String sql = "select id from prestamo where id = (select max(id) from prestamo);";
    	int numPrestamo = -1;
    	try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	numPrestamo = rs.getInt("id");
            }
            rs.close();
            st.close();
            con.close();
		} catch (SQLException e) {
			System.err.println("Error en metodo prestamoMasAlto ");
		}
    	return numPrestamo;
    }
    
    public void prestarLibro(int idLibro) {
    	int idPrestamo = prestamoMasAlto() + 1;
    	
    	
    	String sql = "Insert into prestamo values(" + idPrestamo + ", sysdate()," + idLibro + ");" ;
    	try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate(sql);
  
     
            st.close();
            con.close();
		} catch (SQLException e) {
			System.err.println("Error en metodo prestarLibro " + idLibro);
		}
    }
    
    private boolean comprobarAutor(String nombre) {
    	String sql = "select * from autor where nombre='" + nombre + "';" ;
    	try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	return false;
            }
            rs.close();
            st.close();
            con.close();
		} catch (SQLException e) {
			System.err.println("Error en metodo comprobarAutor");
		}
    	return true;
    }

    
    public String insertarAutor(String nombre, String fecha, String nacionalidad) {
    	String mensaje = "";
    	String sql = "Insert into autor values(null,'" + nombre + "','" + fecha + "','" + nacionalidad + "');" ;
    	try {
    		if(comprobarAutor(nombre)) {
    			Connection con = dataSource.getConnection();
    			Statement st = con.createStatement();
    			st.executeUpdate(sql);
    			
    			st.close();
    			con.close();    
    			mensaje = "Se ha anadido correctamente el autor " +  nombre;
    		}
    		else {
    			mensaje = "El autor " + nombre + " ya existe";
    		}
		} catch (SQLException e) {
			System.err.println("Error en metodo insertarAutor");
		}
    	return mensaje;
    }
    
    
    private String getDiasPrestamo(Date fechaPrestamo) {
    	String diasPrestado = "";
    	
    	Date diaHoy = new Date();
 
        long diff = diaHoy.getTime() - fechaPrestamo.getTime();

        TimeUnit time = TimeUnit.DAYS; 
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
        diasPrestado = diffrence + " dias prestado";
    	return diasPrestado;
    }
    
    
    public LinkedHashMap<Libro, String> obtenerLibrosPrestados(){
    	LinkedHashMap<Libro, String> prestamos = new LinkedHashMap<Libro, String>();
    	String sql = "select idlibro, fecha, titulo from prestamo inner join libro on idlibro = libro.id group by idlibro order by fecha;";
    	try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Libro libro = new Libro();
            	String titulo = rs.getString("titulo");
            	int idLibro = rs.getInt("idlibro");
            	libro.setTitulo(titulo);
            	libro.setIdLibro(idLibro);
            	Date fecha = rs.getDate("fecha");
            	String diasPrestado = getDiasPrestamo(fecha);
            	prestamos.put(libro, diasPrestado);
            }
            rs.close();
            st.close();
            con.close();       
		} catch (SQLException e) {
			System.err.println("Error en metodo obtenerLibrosPrestados" );
		}
    	return prestamos;
    }

    

    public void devolverLibros(ArrayList<Integer> listaLibros) {
    	String sql = "delete from prestamo where idlibro=?" ;
    	try {
    		for(Integer id: listaLibros) {
    			Connection con = dataSource.getConnection();
    			PreparedStatement st = con.prepareStatement(sql);
    			st.setInt(1, id);
    			st.executeUpdate();
    			st.close();
    			con.close();
    		}
    		
    	} catch (SQLException e) {
    		System.err.println("Error en metodo devolverLibros" );
    	}
    }

}


//private int idLibro;
//private String titulo;
//private int paginas;
//private String genero;
//private int idAutor;
