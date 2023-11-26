import java.util.HashMap;

public class PaymentGateway {
    private HashMap<String, Integer> products;

    public PaymentGateway() {
        products = new HashMap<>();
    }

    public void themSanPham(String tenSanPham, int soLuong) {
        products.put(tenSanPham, soLuong);
    }

    public boolean xoaSanPham(String tenSanPham) {
        if (products.containsKey(tenSanPham)) {
            products.remove(tenSanPham);
            return true;
        }
        return false;
    }

    public void hienThiSanPhamCoSan() {
        if (products.isEmpty()) {
            System.out.println("Không có sản phẩm nào.");
        } else {
            System.out.println("Các sản phẩm có sẵn:");
            for (String tenSanPham : products.keySet()) {
                int soLuong = products.get(tenSanPham);
                System.out.println("- " + tenSanPham + ": " + soLuong + " sản phẩm có sẵn");
            }
        }
    }

    public boolean xuLyThanhToan(String tenSanPham, int soLuong) {
        if (products.containsKey(tenSanPham)) {
            int soLuongCoSan = products.get(tenSanPham);
            if (soLuongCoSan >= soLuong) {
                soLuongCoSan -= soLuong;
                products.put(tenSanPham, soLuongCoSan);
                System.out.println("Thanh toán thành công.");
                return true;
            } else {
                System.out.println("Số lượng không đủ để thanh toán.");
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm.");
        }
        return false;
    }
}
