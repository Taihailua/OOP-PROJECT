
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Order {
    static Scanner sc= new Scanner(System.in);
    private OrderItem orderItems[] = new OrderItem[0];
    private int itemCount=0;// số lượng mục trong đơn hàng
    private static int nextOrderCode=1;
    private Customer customer=new Customer();
   
    
    public Order() {
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public OrderItem[] getOrderItems() {
        return orderItems;
    }

    public int getItemCount() {
        return itemCount;
    }

    public static int getNextOrderCode() {
        return nextOrderCode;
    }

    public Customer getCustomer() {
        return customer;
    }
    
    

    // tạo mã đơn hàng tự động
    public static String generateOrderCode(){
        String Code="ORD"+String.format("%03d", nextOrderCode);
        nextOrderCode++;
        return Code;
    }
    //thêm sản phẩm vào đơn hàng
    public void addOrderItem(OrderItem orderItem){
        itemCount++;
        orderItems=Arrays.copyOf(orderItems, itemCount);
        orderItems[itemCount-1]=new OrderItem();
        orderItems[itemCount-1]=orderItem;
    }
    //xóa sản phẩm trong đơn hàng
    public void removeOrderItem(){
        System.out.print("Nhap chi so: ");
        int index=Integer.parseInt(sc.nextLine());
        if(index >=0 && index <itemCount){
            for(int i=index;i<itemCount-1;i++){
                orderItems[i]=orderItems[i+1];
            }
            itemCount--;
            orderItems=Arrays.copyOf(orderItems,itemCount);
            System.out.println("Xoa thanh cong!");
        }
        else{
            System.out.println("Vi tri khong hop le.Khong the xoa!!");
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
    }
    // chỉnh sửa sản phẩm trong đơn hàng
    public void editOrderItem(){
        System.out.print("Nhap chi so: ");
        
        int index=Integer.parseInt(sc.nextLine());
        if(index < 0 || index >itemCount){
            System.out.println("Vi tri khong hop le!");
            return ;
        }
            System.out.print("Nhap so luong moi");
            int Amount =Integer.parseInt(sc.nextLine());
            if(Amount<0) {
                System.out.println("Gia tri khong hop le");
                return;
            }
            else if(Amount==0){
                removeOrderItem(index);
            }
                else{
                    orderItems[index].setAmount(Amount);
                }
    }
    // tính tổng tiền của đơn hàng
    public int calculateTotalCost(){
        int totalCost=0;
        for(int i=0;i<itemCount;i++){
            totalCost+=orderItems[i].getTotalCost();
        }
        return totalCost;
    }
    // xóa toàn bộ đơn hàng
    public void deleteOrderItems(){
        orderItems=Arrays.copyOf(orderItems,0);
        itemCount=0;
    }
    
    public String DecFormat(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }
    
   // hiển thị chi tiết từng sản phẩm có trong đơn hàng
    public void displayOrderDetail(){
        System.out.println("Khach hang:"+customer.getName());
        System.out.println("--------------------------------");
        System.out.println("Order Detail:"
                + "\nOrder code:"+generateOrderCode());
        System.out.println(String.format("|%-3s|%-10s|%-25s|%-6s|%-14s|", "STT","PhoneID","Name","Amount","Price (VND)"));
        for(int i=0;i<itemCount;i++){
            System.out.println(String.format("|%-3d|%-10s|%-25s|%-6s|%-14s|",
            (i+1),
            orderItems[i].getProduct().getPhoneID(),
            orderItems[i].getProduct().getName(),
            orderItems[i].getAmount(),
            DecFormat(orderItems[i].getTotalCost())));
        }
        System.out.println("Total Cost:"+DecFormat(calculateTotalCost())+" VND");
    }
    public void inputOrderItem(ImportStock appProduct){
        System.out.print("Nhap ma san pham: ");
        int pos=appProduct.findID(sc.nextLine());
        if(pos==-1){
            System.out.println("Khong tim thay san pham");
            return;
        }
        System.out.print("Nhap so luong san pham: ");
        int amount=Integer.parseInt(sc.nextLine());
        if(amount<=0){
            System.out.println("So luong khong dung");
            return;
        }
        if(amount>appProduct.getAmount()[pos]){
            System.out.println("Vuot qua so luong trong kho");
            return;
        }
        OrderItem odt=new OrderItem(appProduct.getProducts()[pos],amount);
        addOrderItem(odt);
    }
    //menu
    public void showMenu(){
        System.out.println("Vui long nhap thong tin!");
        this.customer.inputCustomerInfo();
        ImportStock appProduct = new ImportStock();
        appProduct.output();
        while(true){
            System.out.println("1.Them san pham\n2. Xoa san pham\n3. Sua san pham");
            System.out.println("4. Xoa toan bo gio hang\n5. Hien thi gio hang\n0.Quay lai");
            System.out.print("Moi chon chuc nang: ");
            int n=Integer.parseInt(sc.nextLine());
            switch(n){
                case 1: inputOrderItem(appProduct);break;
                case 2: removeOrderItem();break;
                case 3: editOrderItem();break;
                case 4: deleteOrderItems();break;
                case 5: displayOrderDetail();break;
                case 0: return;
                default: System.out.println("Sai cu phap");break;
            }
        }
    }
//    public static void main(String[] args) {
//        ImportStock DS=new ImportStock();
//        OrderItem odt=new OrderItem(DS.products[0],12);
//        OrderItem odt3=new OrderItem(DS.products[4],12);
//        OrderItem odt1=new OrderItem(DS.products[1],12);
//        OrderItem odt2=new OrderItem(DS.products[2],12);
//        Customer cs2=new Customer("KH2","Minh Vuong","0377658957","8a Phan Van tri","MV@gmail.com");
//        Order od=new Order(cs2);
//        od.addOrderItem(odt);
//        od.addOrderItem(odt1);
//        od.addOrderItem(odt2);
//        od.addOrderItem(odt3);
//        od.showMenu();
//    }
}

