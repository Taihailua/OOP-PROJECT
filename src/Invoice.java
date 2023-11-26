import java.io.*;

public class Invoice {
    private int soLuong;
    private double donGia;

    public Invoice(int soLuong, double donGia) {
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double tinhTongTien() {
        return soLuong * donGia;
    }


    public void xuatThongTin(String tenFile) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(tenFile));
            writer.println("Số lượng: " + soLuong);
            writer.println("Đơn giá: " + donGia);
            writer.println("Tổng tiền: " + tinhTongTien());
            writer.close();
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    public static Invoice docThongTin(String tenFile) {
        Invoice invoice = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(tenFile));
            int soLuong = Integer.parseInt(reader.readLine().split(": ")[1]);
            double donGia = Double.parseDouble(reader.readLine().split(": ")[1]);
            invoice = new Invoice(soLuong, donGia);
            reader.close();
        } catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        }
        return invoice;
    }
}
