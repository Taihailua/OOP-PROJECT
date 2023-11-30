
import java.net.Socket;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Vuong
 */
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
        System.out.print("Nhap ten san pham:");
       product.setName(sc.nextLine());
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
       setProduct(product);
       System.out.print("Nhap so luong:");
       setAmount(Integer.parseInt(sc.nextLine()));
   }
    @Override
    public String toString() {
        return product + "\namount=" + amount + "\t Cost="+getTotalCost();
    }
    public void output(){
        System.out.println(toString());
    }
  
}
 
