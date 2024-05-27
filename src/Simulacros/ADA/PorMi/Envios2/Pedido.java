package Simulacros.ADA.PorMi.Envios2;

import java.time.LocalDateTime;

public class Pedido {
    private int id;
    private int clientId;
    private LocalDateTime orderDate;
    private boolean status;
    private int quantity;
    private int producId;

    public Pedido(int clientId, LocalDateTime orderDate, boolean status, int quantity, int producId) {
        this.clientId = clientId;
        this.orderDate = orderDate;
        this.status = status;
        this.quantity = quantity;
        this.producId = producId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getProducId() {
        return producId;
    }

    public void setProducId(int producId) {
        this.producId = producId;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", quntity=" + quantity +
                ", producId=" + producId +
                '}';
    }
}
