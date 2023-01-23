package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Cliente;
import conex.BDConex;

public class ClienteDAO {
	
	
	public Cliente buscaCliente(String nombre, String password) {
		Cliente cliente = null;
        String sql = "select * from clientes where nombre = '" + nombre +"' and password = '" + password + "';";
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
              cliente = new Cliente(rs.getInt("id"),rs.getString("nombre"), rs.getString("password"), 
            		  rs.getString("domicilio"), rs.getString("codigopostal"), rs.getString("telefono"), rs.getString("email")); 
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
        	System.err.println("Error en metodo buscaCliente(nombre, password)");
        }     
        return cliente;
		
	}
	
	public boolean buscaCliente(String nombre) {
		boolean esCliente = false;
		String sql = "select * from clientes where nombre= '" + nombre + "'";
		try {
			Connection con = BDConex.getDataSource().getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if(rs.next()) {
				esCliente = true;
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.err.println("Error en metodo buscaCliente(String nombre)");
		}
		return esCliente;
	}
	
	public int cuentaClientes() {
		int numClientes = -1;
		String sql = "select count(id) from clientes";
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
              numClientes = rs.getInt("count(id)");
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
        	System.err.println("Error en metodo cuentaClientes");
        }    
		return numClientes;
	}
	
	public boolean guardaCliente(Cliente cliente) {
		int n = -1;
		try {
			Connection con = BDConex.getDataSource().getConnection();
			String sql = "insert into clientes values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cliente.getId());
			pst.setString(2, cliente.getNombre());
			pst.setString(3, cliente.getPassword());
			pst.setString(4, cliente.getDomicilio());
			pst.setString(5, cliente.getCodigopostal());
			pst.setString(6, cliente.getTelefono());
			pst.setString(7, cliente.getEmail());

			n = pst.executeUpdate();

			pst.close();
			con.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.err.println("Error en metodo guardaCliente");
		}
		if(n == -1) {
			return false;
		}
		return true;
	}
	
	public boolean actualizarCliente(Cliente cliente) {
		// prepared statement
		return false;
	}

	
	
	
	
}
