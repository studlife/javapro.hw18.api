package hillel.javapro.product;

import java.time.LocalDate;
import java.util.Date;

public class Product {

    private int id;
    private String type;
    private Double price;
    private boolean discount;
    private LocalDate createDate;

    public Product(int id, String type, Double price, boolean discount, LocalDate createDate) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.createDate = createDate;
    }

    public Product(String type, Double price, boolean discount, LocalDate createDate) {
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.createDate = createDate;
    }

    public Product(String type, Double price, boolean sale) {
        this.type = type;
        this.price = price;
        this.discount = sale;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean getDiscount() {
        return discount;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", createDate=" + createDate +
                '}';
    }
}
