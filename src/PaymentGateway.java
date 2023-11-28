public class PaymentGateway {
    private Payment payment;

    public boolean processPayment(Payment payment, double amount) {
        this.payment = payment;

        if (payment.getAmount() >= amount) {
            completePayment(amount);
            return true;
        } else {
            return false;
        }
    }

    private void completePayment(double amount) {
        // Thực hiện quy trình thanh toán ở đây
        System.out.println("Đã thanh toán thành công số tiền " + amount + " cho " + payment.getRecipient());
    }
}

public class Payment {
    private String recipient;
    private double amount;

    public Payment(String recipient, double amount) {
        this.recipient = recipient;
        this.amount = amount;
    }

    public String getRecipient() {
        return recipient;
    }

    public double getAmount() {
        return amount;
    }
}
