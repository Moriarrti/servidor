package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import conex.BDConex;

public class KeysDAO {
	

	
    public int siguienteId(String nomTabla){
        int siguienteId = 1;
        String sql = "select max(id) from " + nomTabla ;
        try {
            Connection con = BDConex.getDataSource().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
            	siguienteId = rs.getInt("max(id)") +1;
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("Error en metodo siguienteId");
        }
        return siguienteId;
    }

}
