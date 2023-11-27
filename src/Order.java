
import java.util.Arrays;

public class Order {
    //private Customer customer;
    private OrderItem orderItems[] = new OrderItem[0];
    private int itemCount=0;// số lượng mục trong đơn hàng
    private String orderCode;
    private static int nextOrderCode=1;
   
    
    public Order() {
        this.orderCode = generateOrderCode();
    }

    // tạo mã đơn hàng tự động
    public static String generateOrderCode(){
        String Code="ORD"+String.format("%03d", nextOrderCode);
        nextOrderCode++;
        return Code;
    }
    public String getOrderCode() {
        return orderCode;
    }

    public void addOrderItem(OrderItem orderItem){
        itemCount++;
        orderItems=Arrays.copyOf(orderItems, itemCount);
        orderItems[itemCount-1]=new OrderItem();
        orderItem.input();
        orderItems[itemCount-1]=orderItem;
    }
    public void removeOrderItem(int index){
        if(index >=0 && index <itemCount){
            for(int i=0;i<index-1;i++){
                orderItems[i]=orderItems[i+1];
            }
            orderItems[itemCount-1]=null;
            itemCount--;
            System.out.println("Xoa thanh cong!");
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
    public void input(){
        getOrderCode();
        displayOrderDetail();
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

