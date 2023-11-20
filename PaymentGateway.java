class PaymentGateway implements Payment {
    @Override
    public void processPayment(double amount) {
        boolean paymentSuccessful = true; // Giả sử thanh toán thành công

        if (paymentSuccessful) {
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment failed!");
        }
    }
}
