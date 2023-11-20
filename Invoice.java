class Invoice {
    private List<Sale> sales;

    public Invoice() {
        sales = new ArrayList<>();
    }

    public void addSale(Sale sale) {
        sales.add(sale);
    }

    public double getTotalAmount() {
        double total = 0.0;
        for (Sale sale : sales) {
            total += sale.getTotalAmount();
        }
        return total;
    }
}