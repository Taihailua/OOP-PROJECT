
import java.net.Socket;
import java.util.Scanner;

public class OrderItem {
    private Phone product;
    private int amount;
    private Scanner sc=new Scanner(System.in);
    public OrderItem() {
    }

    public OrderItem(Phone product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Phone getProduct() {
        return product;
    }

    public void setProduct(Phone product) {
       
        
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getTotalCost(){
        return product.getPrice()*amount;
    }
   public void input(){
       Phone phone=new Phone();
       System.out.print("Nhap ten san pham muon them vao gio hang:");
       phone.setName(sc.nextLine());
       setProduct(phone);
       System.out.print("\nNhap so luong:");
       setAmount(Integer.parseInt(sc.nextLine()));
       getTotalCost();
   }
    @Override
    public String toString() {
        return product + "\namount=" + amount + "\t Cost="+getTotalCost();
    }
    public void output(){
        System.out.println(toString());
    }
  
}
 
