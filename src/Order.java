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
    public void listOrderItem(){
        for(int i=0;i<itemCount;i++){
            OrderItem orderItem =new OrderItem();
            orderItem.input();
            addOrderItem(orderItem);
        }
    }
    
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
class Main {
    public static void main(String[] args) {
       
        ProductCategory PC1=new ProductCategory("Dien Thoai IP13");
        ProductCategory PC2=new ProductCategory("Dien Thoai Sasung S21");
        Manufacturer Mf1=new Manufacturer("Vuong","Q12","122345");
        
        Phone phone1 = new Phone("iPhone 13","IP13", 999,PC1,Mf1);
        Phone phone2 = new Phone("Samsung Galaxy S21","SS21", 799,PC2,Mf1);

      
        OrderItem orderItem1 = new OrderItem(phone1, 2); 
        OrderItem orderItem2 = new OrderItem(phone2, 3);

        
 
    }
   }
