
package modelo;

import java.util.Date;


public class Contact {
    
    private Character character;
    private Date date;
    private int level;
    private int points;

    public Contact() {
    }

    public Contact(Character character, Date date, int level, int points) {
        this.character = character;
        this.date = date;
        this.level = level;
        this.points = points;
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
