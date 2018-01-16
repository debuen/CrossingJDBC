
package modelo;


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
    
    
    
}
