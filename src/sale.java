import java.io.*;

public class Sale {
    private String tenSanPham;
    private int soLuong;
    private double giaBan;

    public Sale(String tenSanPham, int soLuong, double giaBan) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double tinhTongTien() {
        return soLuong * giaBan;
    }

    public void xuatThongTin(String tenFile) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(tenFile));
            writer.println("Tên sản phẩm: " + tenSanPham);
            writer.println("Số lượng: " + soLuong);
            writer.println("Giá bán: " + giaBan);
            writer.println("Tổng tiền: " + tinhTongTien());
            writer.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    //  đọc thông tin đơn hàng từ file

    public static Sale docThongTin(String tenFile) {
        Sale sale = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(tenFile));
            String tenSanPham = reader.readLine().split(": ")[1];
            int soLuong = Integer.parseInt(reader.readLine().split(": ")[1]);
            double giaBan = Double.parseDouble(reader.readLine().split(": ")[1]);
            sale = new Sale(tenSanPham, soLuong, giaBan);
            reader.close();
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
        return sale;
    }
}
