import java.util.Scanner;
import java.util.Calendar;

public abstract class Warranty {
    private String productSerialNumber;
    private String productModel;
    private Phone product = new Phone();
    private String description;
    private String tenKhachHang;
    private String hanBatDau;
    private String hanKetThuc;
    static Scanner sc = new Scanner(System.in);

    public Warranty(Phone products, String description, String tenKhachHang, String hanBatDau, String hanKetThuc,
            String productSerialNumber) {
        this.product = product;
        this.productSerialNumber = productSerialNumber;
        this.productModel = productModel;
        this.description = description;
        this.tenKhachHang = tenKhachHang;
        this.hanBatDau = hanBatDau;
        this.hanKetThuc = hanKetThuc;
    }

    public Warranty() {
    }

    public void setProduct(Phone product) {
        this.product = product;
    }

    public Phone getProduct() {
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
        System.out.println("Nhap ten dien thoai can bao hanh");
        product.setName(sc.nextLine());
        System.out.println("Please enter product's serial number: ");
        setProductSerialNumber(sc.nextLine());
        System.out.println("Please enter product's model: ");
        setProductModel(sc.nextLine());
        System.out.println("Nhap mo ta cua van de cua dien thoai");
        setDescription(sc.nextLine());
        System.out.println("Hay nhap ten khach hang den bao hanh");
        setTenKhachHang(sc.nextLine());
        System.out.println("Hay nhap ngay kich hoat bao hanh (Ghi chu DD/MM/YYYY): ");
        setHanBatDau(sc.nextLine());
        System.out.println("Hay nhap ngay ket thuc bao hanh (Ghi chu DD/MM/YYYY): ");
        setHanketThuc(sc.nextLine());
    }

    @Override
    public String toString() {
        return "Ten Dien Thoai: " + product.getName() + "\t\tSo series: " + productSerialNumber + "\t\tModel: "
                + getProductModel() + "\t\tMo Ta loi: " + description + "\nTen Khach Hang: " + tenKhachHang
                + "\t\tNgay Kich Hoat BH: " + hanBatDau + "\t\tNgay het han BH: " + hanKetThuc;
    }

    public abstract void calculateRemainingWarranty();

    public abstract void isWarrantyValid();

    public abstract void printWarrantyDetails();

    public void output() {
        System.out.println(toString());
    }
}
