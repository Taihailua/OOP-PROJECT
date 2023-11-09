
package DOAN;

import java.util.Scanner;

public class Phone{
    private String name,phoneID;
    private double price;
    private ProductCategory Category;
    private Manufacturer NSX;

    public Phone() {
    }

    public Phone(String name, String phoneID, double price, ProductCategory Category, Manufacturer NSX) {
        this.name = name;
        this.phoneID = phoneID;
        this.price = price;
        this.Category = Category;
        this.NSX = NSX;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(String phoneID) {
        this.phoneID = phoneID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return Category;
    }

    public void setCategory(ProductCategory Category) {
        this.Category = Category;
    }

    public Manufacturer getNSX() {
        return NSX;
    }

    public void setNSX(Manufacturer NSX) {
        this.NSX = NSX;
    }
    
    public void input(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Nhap ma dien thoai: ");
        setPhoneID(sc.nextLine());
        System.out.print("Nhap ten dien thoai: ");
        setName(sc.nextLine());
        System.out.print("Nhap gia: ");
        setPrice(Double.parseDouble(sc.nextLine()));
        Category=new ProductCategory();
        Category.input();
        NSX=new Manufacturer();
        NSX.input();
    }

    @Override
    public String toString() {
        return "Phone{" + "name=" + name + ", phoneID=" + phoneID + ", price=" + price + Category.toString() + NSX.toString() + '}';
    }

    public void output(){
        System.out.println(toString());
    }
    
}
