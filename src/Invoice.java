
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Invoice {
    static Scanner sc = new Scanner(System.in);
    private Order order;
    private Date invoiceDate;
    private Employee salesperson;
    private Sale sale;
    private Payment payment;
    private static boolean isPaid = false;

    public Invoice() {
    }

    public Invoice(Date invoiceDate, Sale sale, Employee salesperson) {

        this.invoiceDate = invoiceDate;
        this.sale = sale;
        this.salesperson = salesperson;
    }

    // hiển thị đơn hàng cho khách hàng xem trước khi xác nhận thanh toán
    public void order() {
        Order order = new Order();
        order.displayOrderDetail();
        System.out.println("Giam gia: $" + sale.getPriceSale());
        System.out.println("Tong:" + (order.calculateTotalCost() - sale.getPriceSale()));
    }

    // in hoá đơn sau khi khách hàng thanh toán thành công
    public void printInvoice() {
        System.out.println("Hoa don:");
        System.out.println("===============================");
        order.displayOrderDetail();
        System.out.println("Giam gia: $" + sale.getPriceSale());
        System.out.println("Tong:" + (order.calculateTotalCost() - sale.getPriceSale()));
        System.out.println("Ngay in hoa don: " + invoiceDate);
        System.out.println("===============================");
        System.out.println("Nhan vien: " + salesperson.getName());
    }

    // xác nhận thanh toán của khách hàng
    public void payInvoice() {
        if (!isPaid) {
            System.out.println("Thanh toan thanh cong!");
            isPaid = true;
        } else {
            System.out.println("Thanh toan that bai!");
        }
        return;
    }

    // lưu hoá đơn vào file
    public void saveToFile(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Nhan vien: " + salesperson.getName() + "\n");
            writer.write("Hoa don:\n");
            writer.write("===============================\n");
            order.displayOrderDetail();
            writer.write("Tong : $" + sale.getPriceSale() + "\n");
            writer.write("Ngay in hoa don: " + invoiceDate + "\n");
            writer.write("===============================\n");
            writer.close();
            System.out.println("Hoa don da duoc luu vao file: " + fileName);
        } catch (IOException e) {
            System.out.println("Co loi khi xuat hoa don vao file.");
            e.printStackTrace();
        }
    }

    public void showMenu() {
        while (true) {
            System.out.println("1.Hien thi don hang \n2.Thanh toan");
            System.out.println("\n0.Quay lai trang mua hang ");
            System.out.print("Moi chon chuc nang: ");
            int n = Integer.parseInt(sc.nextLine());
            switch (n) {
                case 1:
                    printInvoice();
                    break;
                case 2:
                    payInvoice();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Sai cu phap");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Invoice iv = new Invoice();
        ImportStock DS = new ImportStock();
        OrderItem odt = new OrderItem(DS.products[0], 12);
        OrderItem odt3 = new OrderItem(DS.products[4], 12);
        OrderItem odt1 = new OrderItem(DS.products[1], 12);
        OrderItem odt2 = new OrderItem(DS.products[2], 12);
        Customer cs2 = new Customer("KH2", "Minh Vuong", "0377658957", "8a Phan Van tri", "MV@gmail.com");
        Order od = new Order(cs2);
        od.addOrderItem(odt);
        od.addOrderItem(odt1);
        od.addOrderItem(odt2);
        od.addOrderItem(odt3);
        iv.showMenu();
    }
}