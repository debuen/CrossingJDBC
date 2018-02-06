
package modelo;

import java.util.Objects;


public class Item {
    
    private String name;
    private Double price;
    private Double saleprice;
    private String type;
    private String style;

    public Item(String name, Double price, Double saleprice, String type, String style) {
        this.name = name;
        this.price = price;
        this.saleprice = saleprice;
        this.type = type;
        this.style = style;
    }

    public Item() {
    }

    public Item(String name) {this.name = name;}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(Double saleprice) {
        this.saleprice = saleprice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        return this.name.equalsIgnoreCase(other.getName());
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", price=" + price + ", saleprice=" + saleprice + ", type=" + type + ", style=" + style + '}';
    }
    
    
    
}
