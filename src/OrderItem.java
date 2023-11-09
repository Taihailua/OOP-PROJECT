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
    public double getTotalCost(){
        return product.getPrice()*amount;
    }
    public void input(){
         getProduct();
         getProduct();
         getTotalCost();
    }
    @Override
    public String toString() {
        return "OrderItem:\n" + product + "\namount=" + amount + "\nTotalCost="+getTotalCost();
    }
    public void output(){
        System.out.println(toString());
    }
  
}
 
