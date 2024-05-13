package ADA.RA2.jdbc.Tienda;

import java.time.LocalDateTime;

public class MembershipCard {
    private int id;
    private String cardNumber;
    private LocalDateTime expireDate;
    private int customerId;

    public MembershipCard(String cardNumber, LocalDateTime expireDate) {
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
}
