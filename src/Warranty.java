import java.util.Scanner;
import java.util.Calendar;
public abstract class Warranty {
    private Phone product=new Phone();
    private String description; 
    private String tenKhachHang;
    private String hanBatDau;
    private String hanKetThuc;
    static Scanner sc = new Scanner(System.in);



    public Warranty(Phone products, String description, String tenKhachHang, String hanBatDau, String hanKetThuc) {
        this.product = product;
        this.description = description;
        this.tenKhachHang = tenKhachHang;
        this.hanBatDau = hanBatDau;
        this.hanKetThuc = hanKetThuc;
    }
    public Warranty () {
    }

    public void setProduct(Phone product) {
        System.out.println("Nhap ten dien thoai can bao hanh");
        product.setName(sc.nextLine());
        this.product = product;
    }

    public Phone getProduct() {
        return product;
    }

    public void setDescription(String description) {
        System.out.println("Nhap mo ta cua van de cua dien thoai");
        description = sc.nextLine();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTenKhachHang(String tenKhachHang) {
        System.out.println("Hay nhap ten khach hang den bao hanh");
        tenKhachHang = sc.nextLine();
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setHanBatDau(String hanBatDau) {
        System.out.println("Hay nhap ngay kich hoat bao hanh");
        hanBatDau = sc.nextLine();
        this.hanBatDau = hanBatDau;
    }

    public String getHanBatDau() {
        return hanBatDau;
    }

    public void setHanketThuc(String hanKetThuc) {
        System.out.println("Hay nhap ngay ket thuc bao hanh");
        hanKetThuc = sc.nextLine();
        this.hanKetThuc = hanKetThuc;
    }

    public String getHanKetThuc() {
        return hanKetThuc;
    }

    public void input() {
        setProduct(product);
        setDescription(description);
        setTenKhachHang(tenKhachHang);
        setHanBatDau(hanBatDau);
        setHanketThuc(hanKetThuc);
    }

    @Override
    public String toString() {
        return "Ten Dien Thoai: " + product.getName() + "\tMo Ta loi: " + description + "\tTen Khach Hang: " + tenKhachHang + "\tNgay Kich Hoat BH: " + hanBatDau +"\tNgay het han BH: " + hanKetThuc ;
    }
    public abstract void calculateRemainingWarranty();
 
    public abstract void isWarrantyValid();

    public abstract void printWarrantyDetails();

    public void output() {
        System.out.println(toString());
    }
}
