
public class Store {
    private Phone []products =new Phone[0];
    private Order []orders =new Order[0];
    private int countOrder=0;
     private int countPhone=0;
    
    public void addProduct(Phone product){
       for(int i=0;i<=products.length;i++){
               products[++countPhone]=product;  
       }
    }
    public void removeProduct(int index){
       if(index>=0 && index<countPhone){
            for(int i=0;i<index-1;i++){
                products[i]=products[i+1];
            }
            products[countPhone-1]=null;
            countPhone--;
        }
        else {
            System.out.println("Index khong hop le.Khong the xoa!");
        }
    }
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
    // hiển thị thông tin về tất cả sản phẩm trong của hàng
    public void displayProduct(){
        System.out.println("Product:");
        for(Phone product : products){
            if(product!=null){
                product.output();
            }
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
    // tìm tên sản phẩm
    public Phone findProductByName(String productName){
        for(Phone product :products){
            if(product !=null && product.getName().equals(productName))
                return product;
        }
        return null;
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
}
