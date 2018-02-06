
package modelo;

import java.util.ArrayList;


public class User {
    
    private String username;
    private String password;
    private int stucoins;
    private int level;
    private String place;
    private int points;
    private ArrayList<Inventory> inventory;
    private ArrayList<Contact> contacts;

    public User() {
    }
    
    public User(String username, String password, int stucoins, int level, String place, int points) {
        this.username = username;
        this.password = password;
        this.stucoins = stucoins;
        this.level = level;
        this.place = place;
        this.points = points;
        inventory = new ArrayList<>();
        contacts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStucoins() {
        return stucoins;
    }

    public void setStucoins(int stucoins) {
        this.stucoins = stucoins;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Inventory> inventory) {
        this.inventory = inventory;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", stucoins=" + stucoins + ", level=" + level + ", place=" + place + ", points=" + points + ", inventory=" + inventory + ", contacts=" + contacts + '}';
    }

    

    

    
    
    
}
