package client.entity;

import java.util.Objects;

public class Product {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private Boolean isAvailable;

    public Product(Integer id, String name, Double price, String description, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(description, product.description) &&
                Objects.equals(isAvailable, product.isAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, description, isAvailable);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(",name=").append(name);
        sb.append(",price=").append(price);
        sb.append(",description=").append(description);
        sb.append(",isAvailable=").append(isAvailable);
        return sb.toString();
    }
}
