package Simulacros.ADA.PorMi.Tienda;

import java.time.LocalDateTime;

public class Tarjeta {
    private int id;
    private String cardNumber;
    private LocalDateTime expireDate;
    private int customerId;

    public Tarjeta(String cardNumber, LocalDateTime expireDate, int customerId) {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.customerId = customerId;
    }
    public Tarjeta(String cardNumber, LocalDateTime expireDate) {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expireDate=" + expireDate +
                ", customerId=" + customerId +
                '}';
    }
}
