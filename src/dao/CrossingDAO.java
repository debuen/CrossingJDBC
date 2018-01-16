
package dao;

import excepciones.MiExcepcion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.User;


public class CrossingDAO {
    
    private Connection conexion;
    
    public void insertarUsuario(User u) throws SQLException, MiExcepcion {
        if (existeUsuario(u)) {
            throw new MiExcepcion("ERROR: El usuario ya existe");
        } else {    
            String insert = "insert into user values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(insert);

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getStucoins());
            ps.setInt(4, u.getLevel());
            ps.setString(5, u.getPlace());
            ps.setInt(6, u.getPoints());

            ps.executeUpdate();
            ps.close();
        }    
    }
    
    private boolean existeUsuario(User u) throws SQLException {
        String select = "select * from user where username='" + u.getUsername() + "'";
        Statement st = conexion.createStatement();
        boolean existe = false;
        ResultSet rs = st.executeQuery(select);
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        return existe;
    }
    
    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/stucomcrossing";
        String user = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, user, pass);
    }
    
    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
}
