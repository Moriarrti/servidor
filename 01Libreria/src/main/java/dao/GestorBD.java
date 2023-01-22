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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import beans.Autor;
import beans.Libro;

/**
 *
 * @author Amaia
 */
public class GestorBD {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "root";
    private static final String PASS = "";
    private static BasicDataSource dataSource;

    public GestorBD() {
        //Creamos el pool de conexiones
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASS);
        //Indicamos el tamaño del pool de conexiones
        dataSource.setInitialSize(50);
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
    
    public LinkedHashMap<Integer, Autor> getAutoresCompletos(){
    	LinkedHashMap<Integer, Autor> autores = new LinkedHashMap<Integer, Autor>();
    	String sql = "Select * from autor";
    	try {
            Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Autor autor = new Autor();
            	autor.setNombre(rs.getString("nombre"));
            	
            	autor.setFechanac(rs.getDate("fechanac"));
            	autor.setNacionalidad(rs.getString("nacionalidad"));
                autores.put(rs.getInt("id"), autor);
            }
            rs.close();
            st.close();
            con.close();
		} catch (SQLException e) {
			System.err.println("Error en metodo getAutoresCompletos");
		}
    	return autores;
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
            	System.out.println(libro.toString());
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

}
//private int idLibro;
//private String titulo;
//private int paginas;
//private String genero;
//private int idAutor;
