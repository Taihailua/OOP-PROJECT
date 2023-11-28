public class Payment {
    private String cardNumber;
    private String expirationDate;
    private String cardType;

    public Payment(String cardNumber, String expirationDate, String cardType) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCardType() {
        return cardType;
    }
}
