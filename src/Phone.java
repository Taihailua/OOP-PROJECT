import java.util.Scanner;
import java.text.DecimalFormat;

public class Phone{
    private String name,phoneID;
    private int price;
    private ProductCategory Category;
    private Manufacturer NSX;

    public Phone() {
    }

    public Phone(String name, String phoneID,int price, ProductCategory Category, Manufacturer NSX) {
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

    public int getPrice() {
        return price;
    }
    
    public String DecFormat(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    public void setPrice(int price) {
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
        setPrice(Integer.parseInt(sc.nextLine()));
        Category=new ProductCategory();
        Category.input();
        NSX=new Manufacturer();
        NSX.input();
    }

    @Override
    public String toString() {
        return "Phone {" + "name:" + name + ", ID:" + phoneID + ", price= " + DecFormat(price)+" VND" + Category.output()+ NSX.output()+ '}';
    }

    public void output(){
        System.out.println(toString());
    }
    
}
