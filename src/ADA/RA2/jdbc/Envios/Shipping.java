package ADA.RA2.jdbc.Envios;

import java.time.LocalDateTime;

public class Shipping {
    private int id;
    private LocalDateTime shippingDate;
    private int productId;
    private int orderId;

    public Shipping(LocalDateTime shippingDate, int productId, int orderId) {
        this.shippingDate = shippingDate;
        this.productId = productId;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", shippingDate=" + shippingDate +
                ", productId=" + productId +
                ", orderId=" + orderId +
                '}';
    }
}
