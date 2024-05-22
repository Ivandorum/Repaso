package Simulacros.ADA.PorMi.Envios;

import java.util.Map;

public class Inventario {
    private int id;
    private int stock;
    private int productId;

    public Inventario(int stock, int productId) {
        this.stock = stock;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id=" + id +
                ", stock=" + stock +
                ", productId=" + productId +
                '}';
    }
}
