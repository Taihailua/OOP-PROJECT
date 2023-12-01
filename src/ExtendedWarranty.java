import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
public class ExtendedWarranty extends Warranty {
    static Scanner sc = new Scanner(System.in);
    public ExtendedWarranty(String product, String productSerialNumber, String productModel, String description, String tenKhachHang, String hanBatDau, String hanKetThuc) {
        super(product, productSerialNumber, productModel, description, tenKhachHang, hanBatDau, hanKetThuc);
    }

    public String getProduct() {
        return product;
    }

    public String getPrductSerialNumber() {
        return productSerialNumber;
    }

    public String getProductModel() {
        return productModel;
    }

    public String getDescription() {
        return description;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getHanBatDau() {
        return hanBatDau;
    }

    public String getHanKetThuc() {
        return hanKetThuc;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setProductSerialNumber(String productSerialNumber) {
        this.productSerialNumber = productSerialNumber;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setHanBatDau(String hanBatDau) {
        this.hanBatDau =hanBatDau;
    }

    public void setHanketThuc(String hanKetThuc) {
        this.hanKetThuc = hanKetThuc;
    }
    @Override
    public String toString() {
        return product+ ";" + productSerialNumber +";"+ productModel +";"+ description + ";" + tenKhachHang + ";" + hanBatDau + ";" + hanKetThuc;
    }
    @Override
    public void calculateRemainingWarranty(String date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar currentDate = Calendar.getInstance();
        Calendar newDate = Calendar.getInstance();
        try {
            System.out.println("Hay nhap thoi luong muon gia han: ");
            int extendedPeriod = Integer.parseInt(sc.nextLine());
            System.out.println("Hay chon thoi luong gia han (ngay/thang/nam)");
            String c = sc.nextLine();
            switch (c) {
                case "ngay":
                    currentDate.add(Calendar.DATE, extendedPeriod);
                    break;
                case "thang":
                    currentDate.add(Calendar.MONTH, extendedPeriod);
                    break;
                case "nam":
                    currentDate.add(Calendar.YEAR, extendedPeriod);
                    break;
                // Các trường hợp khác có thể thêm vào sau này
                default:
                    System.out.println("Thoi luong khong hop le !");
                    break;
            }
            String newActivateDateString = df.format(newDate.getTime());
            String newDateString = df.format(currentDate.getTime());
            setHanketThuc(newDateString);
            setHanBatDau(newActivateDateString);
            System.out.println("Gia han bao hanh thanh cong !");
            System.out.println("Da gia han them " + extendedPeriod +" "+ c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void isWarrantyValid(String date) {
        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Định nghĩa 2 mốc thời gian ban đầu
        try {
            Date endDate = dateFormat.parse(date);
            Date currentDate = new Date();
            long diffInMillies =endDate.getTime() - currentDate.getTime();
            long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
            if (diff <= 0)
                System.out.println("Da het han bao hanh");
            else {
                System.out.println("Con han bao hanh");
                System.out.println("So ngay bao hanh con lai" + diff);
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printWarrantyDetails(String date) {
        isWarrantyValid(date);
        calculateRemainingWarranty(date);
    }
}
