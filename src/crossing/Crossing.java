
package crossing;

import dao.CrossingDAO;
import excepciones.MiExcepcion;
import java.sql.SQLException;
import java.util.List;
import modelo.User;
import modelo.Character;
import modelo.Item;


public class Crossing {

    
    public static void main(String[] args) throws SQLException, MiExcepcion {
        
        CrossingDAO crossingDAO = new CrossingDAO();
        
        User u1 = new User("Debuen", "stucom", 100, 0, "", 0);
        User u2 = new User("Jaria", "stucom", 100, 0, "", 0);
        User u3 = new User("Aguayo", "stucom", 100, 0, "", 0);
        
        Character c1 = new Character("Lobo", "DAM", "STUCOM", "iPhone");
        Character c2 = new Character("Perro", "DAW", "STUCOM", "Apuntes DAW");
        Character c3 = new Character("Elefante", "ASIX", "STUCOM", "Mochila");
        
        Item i1 = new Item("iPhone", 500.00, 900.00, "mobil", "DAM");
        Item i2 = new Item("Apuntes DAW", 1.00, 5.00, "apuntes", "DAW");
        Item i3 = new Item("Mochila Nike", 10.00, 25.00, "mochila", "Bachillerato");
        
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
            System.out.println("--------- USUARIO POR NOMBRE ----------");
            System.out.println("Usuario " + u1.getUsername());
            List<User> usuario = crossingDAO.selectAllUsers();
            for (User u : usuario) {
                if(u.getUsername().equalsIgnoreCase(u1.getUsername()))
                System.out.println(u);
            }
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- REGISTRO PERSONAJE ----------");
            System.out.println("Registrando personaje...");
            try {
                crossingDAO.insertarCharacter(c1);
                System.out.println("Personaje "+ c1.getName()+" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Registrando personaje...");
            try {
                crossingDAO.insertarCharacter(c2);
                System.out.println("Personaje "+ c2.getName()+" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Registrando personaje...");
            try {
                crossingDAO.insertarCharacter(c3);
                System.out.println("Personaje "+ c3.getName()+" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            
            System.out.println("Registrando personaje...");
            try {
                crossingDAO.insertarCharacter(c1);
                System.out.println("Personaje "+ c1.getName()+" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- REGISTRO ITEM ----------");
            System.out.println("Registrando item...");
            try {
                crossingDAO.insertarItem(i1);
                System.out.println("Item "+ i1.getName()+" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Registrando item...");
            try {
                crossingDAO.insertarItem(i2);
                System.out.println("Item "+ i2.getName()+" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("Registrando item...");
            try {
                crossingDAO.insertarItem(i3);
                System.out.println("Item "+ i3.getName()+" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            
            System.out.println("Registrando item...");
            try {
                crossingDAO.insertarItem(i1);
                System.out.println("Item "+ i1.getName()+" dado de alta");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- VALIDACION USUARIO ----------");
            System.out.println("Validando usuario...");
            try {
                String username = "Jaria";
                String passw = "stucom";
                crossingDAO.validacion(username, passw);
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            
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
