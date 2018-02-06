package crossing;

import dao.CrossingDAO;
import excepciones.MiExcepcion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.User;
import modelo.Character;
import modelo.Contact;
import modelo.Inventory;
import modelo.Item;

public class Crossing {
    
    public static void main(String[] args) throws SQLException, MiExcepcion {
        
        CrossingDAO crossingDAO = new CrossingDAO();
        
        User u1 = new User("Debuen", "stucom", 100, 0, "", 0);
        User u2 = new User("Jaria", "stucom", 100, 0, "", 0);
        User u3 = new User("Aguayo", "stucom", 100, 0, "", 0);
        
        Character c1 = new Character("Lobo", "DAM", "stucom", "iPhone");
        Character c2 = new Character("Perro", "DAW", "stucom", "Apuntes DAW");
        Character c3 = new Character("Elefante", "ASIX", "stucom", "Mochila");
        
        Item i1 = new Item("iPhone", 40.00, 80.00, "mobil", "DAM");
        Item i2 = new Item("Apuntes DAW", 5.00, 10.00, "apuntes", "DAW");
        Item i3 = new Item("Mochila Nike", 25.00, 50.00, "mochila", "Bachillerato");
        
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
            altaUsuario(crossingDAO, u1);
            altaUsuario(crossingDAO, u2);
            altaUsuario(crossingDAO, u3);
            altaUsuario(crossingDAO, u1);
            System.out.println();
            
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- USUARIO POR NOMBRE ----------");
            System.out.println("Buscando usuario...");
            String nombre = "Jaria";
            User usuario = crossingDAO.returnUserByName(nombre);
            System.out.println(usuario);
            System.out.println();
            
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- REGISTRO PERSONAJE ----------");
            altaPersonaje(crossingDAO, c1);
            altaPersonaje(crossingDAO, c2);
            altaPersonaje(crossingDAO, c3);
            altaPersonaje(crossingDAO, c1);
            
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- REGISTRO ITEM ----------");
            altaItem(crossingDAO, i1);
            altaItem(crossingDAO, i2);
            altaItem(crossingDAO, i3);
            altaItem(crossingDAO, i1);
            
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- VALIDACION USUARIO ----------");
            System.out.println("Validando usuario...");
            try {
                String username = "Jaria";
                String passw = "stucom";
                crossingDAO.validar(username, passw);
                System.out.println("Usuario " + username + " validado correctamente");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            
            System.out.println("Validando usuario...");
            try {
                String username = "Jaria";
                String passw = "stucomsomtots";
                crossingDAO.validar(username, passw);
                System.out.println("Usuario " + username + " validado correctamente");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- MODIFICAR USUARIO ----------");
            System.out.println("Usuario Aguayo...");
            System.out.println(u3);
            System.out.println("Modificando...");
            u3 = new User("Aguayo", "stucom2", 500, 20, "stucom", 50);
            crossingDAO.updateUser(u3);
            System.out.println(u3);
            
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- MODIFICAR LUGAR USUARIO ----------");
            System.out.println("Moviendo usuario de lugar...");
            try {
                String lugar = "casa";
                crossingDAO.updateUserPlace(u1, lugar);
                System.out.println("Usuario " + u1.getUsername() + " movido de lugar");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            
            System.out.println();
            
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- MODIFICAR LUGAR PERSONAJE ----------");
            System.out.println("Moviendo personaje de lugar...");
            try {
                String lugar = "zoo";
                crossingDAO.updateCharacterPlace(c3, lugar);
                System.out.println("Personaje " + c3.getName() + " movido de lugar");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
            
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- MODIFICAR PRECIO ITEM ----------");
            System.out.println("Modificando precio item...");
            try {
                Double precio = 50.0;
                crossingDAO.updateItemPrice(i3, precio);
                System.out.println("Precio de " + i3.getName() + " modificado");
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
            
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- PERSONAJES MISMO LUGAR USUARIO ----------");
            System.out.println("Buscando...");
            try {
                List<Character> personajes = crossingDAO.findCharacterByUserPlace(u3);
                for (Character c : personajes) {
                    System.out.println(c);
                }
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- COMPRAR ITEM ----------");
            System.out.println("Comprando item...");
            System.out.println(u1);
            crossingDAO.buyItem(u1, i3);
            System.out.println(u1);
            System.out.println("Comprado");
            System.out.println();
            
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- VENDER ITEM ----------");
            System.out.println("Vendiendo item...");
            System.out.println(u1);
            crossingDAO.sellItem(u1, i3);
            System.out.println(u1);
            System.out.println("Vendido");
            System.out.println();
            
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- INVENTARIO DE UN USUARIO ----------");
            System.out.println("Buscando inventario de " +u3.getUsername()+ "...");
            try {
                List<Inventory> inventorio = crossingDAO.returnInventory(u3);
                for (Inventory i : inventorio) {
                    System.out.println(i);
                }
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
            
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- LISTA DE CONTACTOS DE UN USUARIO ----------");
            System.out.println("Buscando contactos de " +u1.getUsername()+ "...");
            try {
                List<Contact> contactos = crossingDAO.returnContacts(u1);
                for (Contact c : contactos) {
                    System.out.println(c);
                }
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- ITEMS NO CONSEGUIDOS ----------");
            System.out.println("Buscando items que "+u3.getUsername()+" no tiene");
            try {
                List<Item> items = crossingDAO.returnItemNotHaveUser(u3);
                for (Item i : items) {
                    System.out.println(i);
                }
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
            
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- TOP 10 USUARIOS ----------");
            try {
                List<String> rnkg = crossingDAO.returnUsersRanking();
                for (String s : rnkg) {
                    System.out.println(s);
                }
            } catch (MiExcepcion ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
            
            System.out.println("************************************************************");
            System.out.println();
            System.out.println("--------- PERSONAJES NO CONOCIDOS ----------");
            System.out.println("Buscando personajes que "+u2.getUsername()+" no conoce");
            try {
                List<Character> personajes = crossingDAO.returnCharactersNotMeet(u2);
                for (Character c : personajes) {
                    System.out.println(c);
                }
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
    
    private static void altaUsuario(CrossingDAO crossingDAO, User u) throws SQLException {
        System.out.println("Registrando usuario...");
        try {
            crossingDAO.insertarUsuario(u);
            System.out.println("Usuario " + u.getUsername() + " dado de alta");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void altaPersonaje(CrossingDAO crossingDAO, Character c) throws SQLException {
        System.out.println("Registrando personaje...");
        try {
            crossingDAO.insertarCharacter(c);
            System.out.println("Personaje " + c.getName() + " dado de alta");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void altaItem(CrossingDAO crossingDAO, Item i) throws SQLException {
        System.out.println("Registrando item...");
        try {
            crossingDAO.insertarItem(i);
            System.out.println("Item " + i.getName() + " dado de alta");
        } catch (MiExcepcion ex) {
            System.out.println(ex.getMessage());
        }
    }
}
