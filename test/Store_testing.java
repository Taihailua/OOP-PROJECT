public class Store_testing {
    private ImportStock DSDT =new ImportStock();
    private Order []orders =new Order[0];
    private int countOrder=0;
    
   public void addOrder(Order order){
        for(int i=0;i<=orders.length;i++){
            orders[++countOrder]=order;
        }
    }
    public void removeOrder(int index){
        if(index>=0 && index<countOrder){
            for(int i=0;i<index-1;i++){
                orders[i]=orders[i+1];
            }
            orders[countOrder-1]=null;
            countOrder--;
        }
        else {
            System.out.println("Index khong hop le.Khong the xoa!");
        }
    }
    // hiển thị thông tin về tất cả đơn hàng trong cửa hàng
    public void displayOrder(){
        System.out.println("Order:");
        for(Order order : orders){
            if(order!=null){
                order.displayOrderDetail();
            }
        }
    }
    //tìm đơn hàng
    public Order findOrderByCode(String orderCode){
        for(Order order:orders){
            if(order!=null && order.getOrderCode().equals(orderCode))
                return order;
        }
        return null;
    }
    //tính tổng doanh thu của cửa hàng
    public double calculateTotalRevenue(){
        double total=0;
        for(Order order:orders){
            if(order!=null){
                total +=order.calculateTotalCost();
            }
        }
        return total;
    }
    public static void main(String[] args) {
        Store_testing Store=new Store_testing();
        Store.DSDT.output();
//        Store.DSDT.add();
//        Store.DSDT.edit();
        Store.DSDT.findName("SamSung");
    }
}
