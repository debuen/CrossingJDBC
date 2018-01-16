
package crossing;

import dao.CrossingDAO;
import excepciones.MiExcepcion;
import java.sql.SQLException;
import java.util.List;
import modelo.User;


public class Crossing {

    
    public static void main(String[] args) throws SQLException, MiExcepcion {
        
        CrossingDAO crossingDAO = new CrossingDAO();
        
        User u1 = new User("Debuen", "stucom", 100, 0, "", 0);
        User u2 = new User("Jaria", "stucom", 100, 0, "", 0);
        User u3 = new User("Aguayo", "stucom", 100, 0, "", 0);
        
        System.out.println("************************************************************");
        System.out.println();
        System.out.println("Testeando conexión con la base de datos...");
        try {
            crossingDAO.conectar();
            System.out.println("Establecida la conexión.");
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- REGISTRO USUARIOS ----------");
            System.out.println("Registrando usuario...");
            try {
                crossingDAO.insertarUsuario(u1);
                System.out.println("Usuario "+ u1.getUsername() +" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Registrando usuario...");
            try {
                crossingDAO.insertarUsuario(u2);
                System.out.println("Usuario "+ u2.getUsername() +" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Registrando usuario...");
            try {
                crossingDAO.insertarUsuario(u3);
                System.out.println("Usuario "+ u3.getUsername() +" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            
            System.out.println("Registrando usuario...");
            try {
                crossingDAO.insertarUsuario(u1);
                System.out.println("Usuario "+ u1.getUsername() +" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("Cerrando conexión con la base de datos");
            crossingDAO.desconectar();
            System.out.println("Conexión cerrada.");
            System.out.println();
            System.out.println("************************************************************");
        } catch (SQLException ex) {
            System.out.println("Error al conectar / desconectar: " + ex.getMessage());
        }
        
    }
    
    private static void altaUsuario(CrossingDAO crossingDAO, User u1) throws SQLException {
        try {
            crossingDAO.insertarUsuario(u1);
            System.out.println("Usuario "+ u1.getUsername() +" dado de alta");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
