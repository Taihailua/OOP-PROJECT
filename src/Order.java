public class Order {
    //private Customer customer;
    private OrderItem orderItems[] = new OrderItem[0];
    private int itemCount=0;// số lượng mục trong đơn hàng
    private String orderCode;
    private static int nexOrderCode=1;
    public Order() {
    }

    public Order(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }
    // tạo mã đơn hàng tự động
    public String generateOrderCode(){
        String orderCode="ORD"+String.format("%03d", nexOrderCode);
        nexOrderCode++;
        return orderCode;
    }
//    public Order(Customer customer) {
//        
//        this.customer=customer;
//    }
    
    public OrderItem[] getOrderItems() {
        return orderItems;
    }
//    public void listOrderItem(){
//        for(int i=0;i<itemCount;i++){
//            OrderItem orderItem =new OrderItem();
//            orderItem.input();
//            addOrderItem(orderItem);
//        }
//    }
    
    public void addOrderItem(OrderItem orderItem){
        if(itemCount<orderItems.length){
            orderItems[itemCount]=orderItem;
            itemCount++;
        }
        else{
            System.out.println("Khong the them !!");
        }
    }
    public void removeOrderItem(int index){
        if(index >=0 && index <itemCount){
            for(int i=0;i<index-1;i++){
                orderItems[i]=orderItems[i+1];
            }
            orderItems[itemCount-1]=null;
            itemCount--;
        }
        else{
            System.out.println("Vi tri khong hop le.Khong the xoa!!");
        }
    }
    public double calculateTotalCost(){
        double totalCost=0;
        for(int i=0;i<itemCount;i++){
            totalCost+=orderItems[i].getTotalCost();
        }
        return totalCost;
    }
    public void displayOrderDetail(){
        System.out.println("Order Detail:");
       // System.out.println("Customer:"+customer.getName());
        System.out.println("Items:");
        for(int i=0;i<itemCount;i++){
            orderItems[i].output();
        }
        System.out.println("Total Cost:"+calculateTotalCost());
    }
}

