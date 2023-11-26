import java.io.*;

public class Payment {
    private String tenKhachHang;
    private double soTien;

    public Payment(String tenKhachHang, double soTien) {
        this.tenKhachHang = tenKhachHang;
        this.soTien = soTien;
    }
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    //  xuất thông tin thanh toán ra file

    public void xuatThongTin(String tenFile) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(tenFile));
            writer.println("Tên khách hàng: " + tenKhachHang);
            writer.println("Số tiền: " + soTien);
            writer.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    //đọc thông tin thanh toán từ file

    public static Payment docThongTin(String tenFile) {
        Payment payment = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(tenFile));
            String tenKhachHang = reader.readLine().split(": ")[1];
            double soTien = Double.parseDouble(reader.readLine().split(": ")[1]);
            payment = new Payment(tenKhachHang, soTien);
            reader.close();
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
        return payment;
    }
}
