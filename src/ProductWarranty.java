import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
public class ProductWarranty extends Warranty {
    public ProductWarranty() {
    }

    public ProductWarranty(String product, String productSerialNumber, String productModel, String description, String tenKhachHang, String hanBatDau, String hanKetThuc) {
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
        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Định nghĩa 2 mốc thời gian ban đầu
        try {
            Date endDate = dateFormat.parse(date);
            Date currentDate = new Date();
            long diffInMillies = endDate.getTime() - currentDate.getTime();
            long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
            if (diff > 0)
            System.out.println("So ngay bao hanh con lai: " + diff);
            else 
            System.out.println("So ngay bao hanh con lai: 0");
        } catch (java.text.ParseException e) {
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
            long diffInMillies = endDate.getTime() - currentDate.getTime();
            long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
            if (diff <= 0)
                System.out.println("Da het han bao hanh");
            else
                System.out.println("Con han bao hanh");
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
