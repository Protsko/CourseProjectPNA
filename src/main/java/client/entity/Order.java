package client.entity;

import java.util.Objects;

public class Order {
    private Integer id;
    private Long userId;
    private Integer productId;
    private Boolean isPayed;

    public Order(Integer id, Long userId, Integer productId, Boolean isPayed) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.isPayed = isPayed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Boolean getPayed() {
        return isPayed;
    }

    public void setPayed(Boolean payed) {
        isPayed = payed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(productId, order.productId) &&
                Objects.equals(isPayed, order.isPayed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId, isPayed);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(",userId=").append(userId);
        sb.append(",productId=").append(productId);
        sb.append(",isPayed=").append(isPayed);
        return sb.toString();
    }
}
