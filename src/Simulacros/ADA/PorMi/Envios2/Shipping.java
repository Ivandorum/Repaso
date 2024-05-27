package Simulacros.ADA.PorMi.Envios2;

import java.time.LocalDate;

public class Shipping {
    private int id;
    private LocalDate shippingDate;
    private int productId;
    private int orderId;

    public Shipping(LocalDate shippingDate, int productId, int orderId) {
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

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
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
