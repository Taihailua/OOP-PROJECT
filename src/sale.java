class Sale {
    private int productId;
    private int quantity;
    private double price;

    public Sale(int productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters

    public double getTotalAmount() {
        return quantity * price;
    }
}

