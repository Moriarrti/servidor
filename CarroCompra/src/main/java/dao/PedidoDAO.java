package dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Item;
import beans.LineaPedido;
import beans.Pedido;
import conex.BDConex;

public class PedidoDAO {

	public ArrayList<Item> todosItems() {
		ArrayList<Item> items = new ArrayList<Item>();
        String sql = "select * from items";
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
            	Item it = new Item(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
            	items.add(it);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo todosItems");
        }
        return items;
	}
	
	public Item buscarItemPorId(int idItem) {
		Item item = new Item();
		
		return item;
	}
	
	public void guardaPedido(Pedido pedido) {
		// prepared statement
	}
	
	public void guardaLineaPedido(LineaPedido linea) {
		// prepared statement
	}
}
