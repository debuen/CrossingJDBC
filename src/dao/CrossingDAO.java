
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
import modelo.Contact;
import modelo.Inventory;
import modelo.Item;


public class CrossingDAO {
    
    private Connection conexion;
    
    //pasamos un objeto usuario para añadirlo a la bbdd
    public void insertarUsuario(User u) throws SQLException, MiExcepcion {
        //si el usuario no esta en la bbdd saltara el error
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
    
    //miramos si el objeto usuario esta en la bbdd
    private boolean existeUsuario(User u) throws SQLException {
        String select = "select * from user where username='" + u.getUsername() + "'";
        Statement st = conexion.createStatement();
        //sino se devuelve nada 
        boolean existe = false;
        ResultSet rs = st.executeQuery(select);
        //si se devuelve algun resultado entrara aqui
        if (rs.next()) {
            existe = true;
        }
        rs.close();
        st.close();
        return existe;
    }
    
    //devolvemos todos los usuarios consultando en la base de datos
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
            //vamos añadiendo cada atributo al objeto u y al array usuarios
            usuarios.add(u);
        }
        rs.close();
        st.close();
        return usuarios;
    }
    
    //comentado antes == insertarUsuario
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
    
    //comentado antes == existeUsuario
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
    
    //comentado antes == insertarUsuario
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
    
    //comentado antes == existeUsuario
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
    
    //devolvemos un objeto usuario mediante un string nombre
    public User returnUserByName(String nombre) throws SQLException{
        User u = new User();
        //en la consulta mostramos el usuario que tenga como nombre el string pasado
        String query = "select * from user where username ='"+nombre+"';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            //se añaden todos los atributos a un objeto u
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setStucoins(rs.getInt("stucoins"));
            u.setLevel(rs.getInt("level"));
            u.setPlace(rs.getString("place"));
            u.setPoints(rs.getInt("points"));
        }
        rs.close();
        st.close();
        return u;
    }
    
    //comentado antes == returnUserByName
    public Item returnItemByName(String nombre) throws SQLException{
        Item i = new Item();
        String query = "select * from item where name ='"+nombre+"';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            i.setName(rs.getString("name"));
            i.setPrice(rs.getDouble("price"));
            i.setSaleprice(rs.getDouble("saleprice"));
            i.setType(rs.getString("type"));
            i.setStyle(rs.getString("style"));     
        }
        rs.close();
        st.close();
        return i;
    }
    
    //comentado antes == returnUserByName
    public Character returnCharacterByName(String nombre) throws SQLException{
        Character c = new Character();
        String query = "select * from stucomcrossing.character where name ='"+nombre+"';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            c.setName(rs.getString("name"));
            c.setStudy(rs.getString("study"));
            c.setPlace(rs.getString("place"));
            c.setPreference(rs.getString("preference"));         
        }
        rs.close();
        st.close();
        return c;
    }
    
    //en este metodo pasamos 2 strings para despues compararlos a la base de datos con sus respectivos campos
    public boolean validar(String name, String passw)throws SQLException, MiExcepcion{
        String select = "select * from user where username ='"+name+"' and password ='"+passw+"';";
        Statement st = conexion.createStatement();
        boolean registrado = false;
        ResultSet rs = st.executeQuery(select);
        //si el select devuelve algo, devuelve un true
        if (rs.next()) {
            registrado = true;
        }
        //sino devuelve nada da un mensaje de error
        else if(registrado == false){
            throw new MiExcepcion("ERROR: Nombre de usuario o contraseña incorrecto");
        }
        rs.close();
        st.close();
        return registrado;
    }

    //pasamos or el main un usuario con los campos editados
    public void updateUser(User u) throws SQLException{
        //utilizamos el update para restablecerlos y el interrogante es donde ira el nuevo campo
        String update = "update user set username=?, password=?, stucoins=?, level=?, place=?, points=? where username = '" + u.getUsername()+"';";
        PreparedStatement ps = conexion.prepareStatement(update);
        //cada numero corresponde de manera ordenada al campo del select que sera restablecido
        ps.setString(1, u.getUsername());
        ps.setString(2, u.getPassword());
        ps.setInt(3, u.getStucoins());
        ps.setInt(4, u.getLevel());
        ps.setString(5, u.getPlace());
        ps.setInt(6, u.getPoints());
        
        ps.executeUpdate();
        ps.close();
    }
    
    //como el metodo updateUser, pero aqui en vez de pasar un objeto, pasamos el string del camppo que queremos modificar y solo hara falta introducrilo al update
    public void updateUserPlace(User u, String place) throws SQLException, MiExcepcion{
        String update = "update user set place= '" + place + "' where username = '" + u.getUsername() +"';";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.executeUpdate();
        ps.close();
    }
    //comentado antes == updateUserPlace
    public void updateCharacterPlace(Character c, String place) throws SQLException, MiExcepcion{
        String update = "update stucomcrossing.character set place= '" + place + "' where name = '" + c.getName() +"';";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.executeUpdate();
        ps.close();
    }
    //comentado antes == updateUserPlace
    public void updateItemPrice(Item i, Double precio) throws SQLException, MiExcepcion{
        String update = "update item set saleprice= '" + precio + "' where name = '" + i.getName() +"';";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.executeUpdate();
        ps.close();
    }
    //comentado antes == returnUserByName, pero aqui igualamos un campo de una tabla con la de otra
    public List<Character> findCharacterByUserPlace(User u) throws SQLException, MiExcepcion{
        List<Character> personajes = new ArrayList<>();
        String query = "select * from stucomcrossing.character where place = '" + u.getPlace() +"';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Character c = new Character();
            c.setName(rs.getString("name"));
            c.setStudy(rs.getString("study"));
            c.setPlace(rs.getString("place"));
            c.setPreference(rs.getString("preference"));
            personajes.add(c);
        }
        rs.close();
        st.close();
        return personajes;
    }
    
    
    public void buyItem(User u, Item i) throws SQLException, MiExcepcion{
        //lo utilizamos para las transacciones, para si una query sale mal,que no se ejecute ninguna de las que hay
        conexion.setAutoCommit(false);
        try{
            if (!existeUsuario(u)) {
                throw new MiExcepcion("ERROR: El Usuario no existe");
            }
            //Si el usuario existe
            else {
                int precio = (int) Math.round(i.getPrice());
                if(precio > u.getStucoins()){
                    throw new MiExcepcion("ERROR: El Usuario no tiene suficientes stucoins");
                }
                //Si tiene suficientes stucoins
                else{
                    int dinero = u.getStucoins() - precio;
                    u.setStucoins(dinero);
                    String update = "update user set stucoins= stucoins where username = '" + u.getUsername() +"';";
                    PreparedStatement ps = conexion.prepareStatement(update);
                    ps.executeUpdate();
                    ps.close();
                    //Si el usuario ya tiene el item
                    Inventory inv = new Inventory(i, 1);
                    u.setInventory(returnInventory(u));
                    if(u.getInventory().contains(inv)){
                        int pos = u.getInventory().indexOf(inv);
                        Inventory actual = u.getInventory().get(pos);
                        actual.setQuantity(actual.getQuantity()+1);
                        String update2 = "update inventory set quantity= quantity+1 where user = '" + u.getUsername() +"' and item = '" + i.getName() +"';";
                        ps = conexion.prepareStatement(update2);
                        ps.executeUpdate();
                        ps.close();
                    }else{
                        //añadimos el item al inventario del usuario a nivel de objetos
                        u.getInventory().add(inv);
                        //ahora lo añadimos a nivel de bbdd
                        String insert = "insert into inventory values (?, ?, ?)";
                        ps = conexion.prepareStatement(insert);
                        ps.setString(1, u.getUsername());
                        ps.setString(2, i.getName());
                        ps.setInt(3, 1);
                        ps.executeUpdate();
                        ps.close();
                    }
                }
            }
        }catch (SQLException ex) {
                conexion.rollback();;
                throw new MiExcepcion(ex.getMessage());        
        }finally{
            Statement st = conexion.createStatement();
            st.close();
            conexion.setAutoCommit(true);
        }
    }
    
    private boolean itemQuantity(User u, Item i) throws SQLException {
        //devuelve algo si el inventorio tiene mas de una cantidad
        String query = "select * from inventory where user='" + u.getUsername() + "and item ='" + i.getName() + "and quantity >1 '";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        boolean uno = false;
        while (rs.next()) {
            //solo tiene 1
            uno = true;
        }
        rs.close();
        st.close();
        return uno;

    }
    //igual que el de buy item pero en vez de sumar los campos, se restan
    public void sellItem(User u, Item i) throws SQLException, MiExcepcion{
        conexion.setAutoCommit(false);
        try{
            if (!existeUsuario(u)) {
                throw new MiExcepcion("ERROR: El Usuario no existe");
            }
            //Si el usuario existe
            else {
                int precio = (int) Math.round(i.getSaleprice());
                    
                    int dinero = u.getStucoins() + precio;
                    u.setStucoins(dinero);
                    String update = "update user set stucoins= stucoins where username = '" + u.getUsername() +"';";
                    PreparedStatement ps = conexion.prepareStatement(update);
                    ps.executeUpdate();
                    ps.close();
                    //Si el usuario ya tiene el item
                    Inventory inv = new Inventory(i, 1);
                    u.setInventory(returnInventory(u));
                    if(u.getInventory().contains(inv)){
                        //si solo le queda un item
//                        if(itemQuantity(u, i) == true){
//                            String delete = "delete from inventory where user ='" + u.getUsername() + "' and item='" + i.getName();
//                            ps = conexion.prepareStatement(delete);
//                            ps.executeUpdate();
//                            ps.close();
//                        }else{
                            //si tiene mas de uno
                            int pos = u.getInventory().indexOf(inv);
                            Inventory actual = u.getInventory().get(pos);
                            actual.setQuantity(actual.getQuantity()-1);
                            String update2 = "update inventory set quantity = quantity-1 where user = '" + u.getUsername() +"' and item = '" + i.getName() +"';";
                            ps = conexion.prepareStatement(update2);
                            ps.executeUpdate();
                            ps.close();
//                        }
                    }else{
                        throw new MiExcepcion("ERROR: El usuario no tiene este item");
                    }
                
            }
        }catch (SQLException ex) {
                conexion.rollback();;
                throw new MiExcepcion(ex.getMessage());        
        }finally{
            Statement st = conexion.createStatement();
            st.close();
            conexion.setAutoCommit(true);
        }
    }    

    //devolvemos una lista del inventario de un usuario accediendo al campo user, que hace referncia al un objeto que se encuentra en la tabla de usuario
    public ArrayList<Inventory> returnInventory(User u) throws SQLException, MiExcepcion{
        ArrayList<Inventory> inventario = new ArrayList<>();
        String query = "select * from inventory where user ='"+u.getUsername()+"';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Inventory i = new Inventory();
            i.setItem(returnItemByName(rs.getString("item")));
            i.setQuantity(rs.getInt("quantity"));
            inventario.add(i);
        }
        rs.close();
        st.close();
        return inventario;
    }
    
    //comentado antes == returnInventory
    public List<Contact> returnContacts(User u) throws SQLException, MiExcepcion{
        List<Contact> contactos = new ArrayList<>();
        String query = "select * from contact where user ='"+u.getUsername()+"';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Contact c = new Contact();
            c.setCharacter(returnCharacterByName(rs.getString("character")));
            c.setDate(rs.getDate("date"));
            c.setLevel(rs.getInt("level"));
            c.setPoints(rs.getInt("points"));
            contactos.add(c);
        }
        rs.close();
        st.close();
        return contactos;
    }
    
    //devolvemos el nombre de usuario con sus puntos, ordenados de mayor a menor por estos mismos.
    public List<String> returnUsersRanking() throws SQLException, MiExcepcion{
        List<String> ranking = new ArrayList<>();
        String query = "select username, points  from user order by points desc limit 10;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        int num = 1;
        while(rs.next()){
            ranking.add(num+" "+rs.getString("username")+" "+rs.getInt("points"));
            num++;
        }
        rs.close();
        st.close();
        return ranking;
    }
    
    //devolvemos una lista de objetos character
    public List<Character> returnCharactersNotMeet(User u) throws SQLException, MiExcepcion{
        List<Character> personajes = new ArrayList<>();
        //hacemos un select para mostrartodos los characters que un usuario no tenga en sus contactos, con un subselect
        String query = "select * from stucomcrossing.character where not exists (select * from contact where user = '" + u.getUsername() +"');";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        //añadimos los atributos al objeto c y al array personajes
        while(rs.next()){
            Character c = new Character();
            c.setName(rs.getString("name"));
            c.setStudy(rs.getString("study"));
            c.setPlace(rs.getString("place"));
            c.setPreference(rs.getString("preference"));
            personajes.add(c);
        }
        rs.close();
        st.close();
        return personajes;
    }
    
    //comentado antes == returnCharacterNoMeet
    public List<Item> returnItemNotHaveUser(User u) throws SQLException, MiExcepcion{
        List<Item> items = new ArrayList<>();
        String query = "select * from item where not exists (select * from inventory where user = '" + u.getUsername() +"');";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while(rs.next()){
            Item i = new Item();
            i.setName(rs.getString("name"));
            i.setPrice(rs.getDouble("price"));
            i.setSaleprice(rs.getDouble("saleprice"));
            i.setType(rs.getString("type"));
            i.setStyle(rs.getString("style"));
            items.add(i);
        }
        rs.close();
        st.close();
        return items;
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
