
package modelo;

import java.util.Date;


public class Contact {
    
    private String user;
    private Character character;
    private Date date;
    private int level;
    private int points;

    public Contact(String user, Character character, Date date, int level, int points) {
        this.user = user;
        this.character = character;
        this.date = date;
        this.level = level;
        this.points = points;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    
    
}
