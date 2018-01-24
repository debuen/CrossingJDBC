
package dao;

import excepciones.MiExcepcion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.User;
import modelo.Character;
import modelo.Item;


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
    
    public List<User> selectAllUsers() throws SQLException {
        List<User> usuarios = new ArrayList<>();
        String select = "select * from user";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()) {
            User u = new User();
            u.setUsername(rs.getString("Username"));
            u.setPassword(rs.getString("Password"));
            u.setStucoins(rs.getInt("Stucoins"));
            u.setLevel(rs.getInt("Level"));
            u.setPlace(rs.getString("Place"));
            u.setPoints(rs.getInt("Points"));
            usuarios.add(u);
        }
        rs.close();
        st.close();
        return usuarios;
    }
    
    public void insertarCharacter(Character c) throws SQLException, MiExcepcion {
        if (existeCharacter(c)) {
            throw new MiExcepcion("ERROR: El personaje ya existe");
        } else {    
            String insert = "insert into stucomcrossing.character values (?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(insert);

            ps.setString(1, c.getName());
            ps.setString(2, c.getStudy());
            ps.setString(3, c.getPlace());
            ps.setString(4, c.getPreference());

            ps.executeUpdate();
            ps.close();
        }    
    }
    
    private boolean existeCharacter(Character c) throws SQLException {
        String select = "select * from stucomcrossing.character where name='" + c.getName() + "'";
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
    
    public void insertarItem(Item i) throws SQLException, MiExcepcion {
        if (existeItem(i)) {
            throw new MiExcepcion("ERROR: El item ya existe");
        } else {    
            String insert = "insert into item values (?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(insert);

            ps.setString(1, i.getName());
            ps.setDouble(2, i.getPrice());
            ps.setDouble(3, i.getSaleprice());
            ps.setString(4, i.getType());
            ps.setString(5, i.getStyle());

            ps.executeUpdate();
            ps.close();
        }    
    }
    
    private boolean existeItem(Item i) throws SQLException {
        String select = "select * from item where name='" + i.getName() + "'";
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
    
//    public void validacion(username, passw) throws SQLException, MiExcepcion {
//        if (existeItem(i)) {
//            throw new MiExcepcion("ERROR: El item ya existe");
//        } else {    
//            String insert = "insert into item values (?, ?, ?, ?, ?)";
//            PreparedStatement ps = conexion.prepareStatement(insert);
//
//            ps.setString(1, i.getName());
//            ps.setDouble(2, i.getPrice());
//            ps.setDouble(3, i.getSaleprice());
//            ps.setString(4, i.getType());
//            ps.setString(5, i.getStyle());
//
//            ps.executeUpdate();
//            ps.close();
//        }    
//    }
    
    private static void validacion(String username, String passw) throws SQLException {
        List<User> usuarios = new ArrayList<>();
        String select = "select * from user";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()) {
            User u = new User();
            u.setUsername(rs.getString("Username"));
            u.setPassword(rs.getString("Password"));
            u.setStucoins(rs.getInt("Stucoins"));
            u.setLevel(rs.getInt("Level"));
            u.setPlace(rs.getString("Place"));
            u.setPoints(rs.getInt("Points"));
            usuarios.add(u);
        }
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
