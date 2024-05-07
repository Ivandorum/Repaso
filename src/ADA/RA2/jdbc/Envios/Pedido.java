package ADA.RA2.jdbc.Envios;

import java.time.LocalDateTime;

public class Pedido {

    private int id;
    private int clientId;
    private LocalDateTime orderDate;
    private boolean status;
    private int quantity;
    private int productId;

    public Pedido(int clientId, LocalDateTime orderDate, boolean status, int quantity, int productId) {
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.status = status;
        this.quantity = quantity;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client_id=" + clientId +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }
}
