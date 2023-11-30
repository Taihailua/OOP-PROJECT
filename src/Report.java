import java.util.Arrays;

public class Report {
    private Invoice[] revenueData = new Invoice[0];
    private int countRevenueData = 0;
    private ImportStock DSDT = new ImportStock();
    private Order[] orders = new Order[0];
    private int orderCount;

    public Report() {
    }

    // thêm từng order vào danh sách
    public void addOrder(Order order) {
        orderCount++;
        orders = Arrays.copyOf(orders, orderCount);
        orders[orderCount - 1] = new Order();
        orders[orderCount - 1] = order;
    }

    // hiển thị danh sách các order
    public void displayOrder() {
        for (int i = 0; i < orderCount; i++) {
            orders[i].displayOrderDetail();
            System.out.println("=====================================");
        }
    }

    // public void addInvoice(Invoice invoice){
    // countRevenueData++;
    // revenueData=Arrays.copyOf(revenueData,countRevenueData);
    // revenueData[countRevenueData-1]=new Invoice();
    //
    // revenueData[countRevenueData-1]=invoice;
    // }
    // hiển thi danh sách sản phẩm còn trong cửa hàng
    public void displayStock() {
        DSDT.output();
    }

}
