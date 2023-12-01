import java.util.Scanner;
public abstract class Warranty {
    protected String productSerialNumber; 
    protected String productModel;
    protected String product;
    protected String description; 
    protected String tenKhachHang;
    protected String hanBatDau;
    protected String hanKetThuc;
    static Scanner sc = new Scanner(System.in);

    public Warranty(String product, String description, String tenKhachHang, String hanBatDau, String hanKetThuc, String productSerialNumber, String productModel) {
        this.product = product;
        this.productSerialNumber = productSerialNumber;
        this.productModel = productModel;
        this.description = description;
        this.tenKhachHang = tenKhachHang;
        this.hanBatDau = hanBatDau;
        this.hanKetThuc = hanKetThuc;
    }

    public Warranty () {
    }


    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setProductSerialNumber(String productSerialNumber) {
        this.productSerialNumber = productSerialNumber;
    }

    public String getProductSerialNumber() {
        return productSerialNumber;
    }

    public String getProductModel() {
        return productModel;
    }
    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }




    public void setDescription(String description) {

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTenKhachHang(String tenKhachHang) {

        this.tenKhachHang = tenKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setHanBatDau(String hanBatDau) {

        this.hanBatDau = hanBatDau;
    }

    public String getHanBatDau() {
        return hanBatDau;
    }

    public void setHanketThuc(String hanKetThuc) {

        this.hanKetThuc = hanKetThuc;
    }

    public String getHanKetThuc() {
        return hanKetThuc;
    }

    public void input() {
        System.out.println("Please enter product's name: ");
        setProduct(sc.nextLine());
        System.out.println("Please enter product's serial number: ");
        setProductSerialNumber(sc.nextLine());
        System.out.println("Please enter product's model: ");
        setProductModel(sc.nextLine());
        System.out.println("Please describe the problem of customer's phone: ");
        setDescription(sc.nextLine());
        System.out.println("Please enter customer's name: ");
        setTenKhachHang(sc.nextLine());
        System.out.println("Please enter warranty activation date (NOTE: enter as DD/MM/YYYY): ");
        setHanBatDau(sc.nextLine());
        System.out.println("Please enter warraty expiration date (NOTE: enter as DD/MM/YYYY): ");
        setHanketThuc(sc.nextLine());
    }

    @Override
    public String toString() {
        return  "Phone's name: " + product + "\t\tSerial Number: " + productSerialNumber + "\t\tModel: " + productModel + "\t\tDescription: " + description + "\nCustomer's name: " + tenKhachHang + "\t\tActivation Date: " + hanBatDau +"\t\tExpiration Date: " + hanKetThuc ;
    }
    public abstract void calculateRemainingWarranty(String date);
 
    public abstract void isWarrantyValid(String date);

    public abstract void printWarrantyDetails(String date);
    
    public void output() {
        System.out.println(toString());
    }
}
