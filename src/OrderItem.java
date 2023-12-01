
import java.net.Socket;
import java.util.Scanner;

public class OrderItem {
    private Phone product;
    private int amount;
    private Scanner sc = new Scanner(System.in);

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

    public int getTotalCost() {
        return product.getPrice() * amount;
    }

    // public void input(){
    // System.out.print("Nhap ma san pham muon them vao gio hang:");
    // findID
    // System.out.print("\nNhap so luong:");
    // setAmount(Integer.parseInt(sc.nextLine()));
    // getTotalCost();
    // }
    @Override
    public String toString() {
        return product + "|amount=" + amount + "\t Cost=" + getTotalCost();
    }

    public void output() {
        System.out.println(toString());
    }

}
