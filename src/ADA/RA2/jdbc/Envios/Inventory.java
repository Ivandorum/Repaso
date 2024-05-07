package ADA.RA2.jdbc.Envios;

public class Inventory {
    private int id;
    private int stock;
    private int productId;

    public Inventory(int stock, int productId) {
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
        return "Inventory{" +
                "id=" + id +
                ", stock=" + stock +
                ", productId=" + productId +
                '}';
    }
}
