import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Invoice {
    private Order order;
    private Date invoiceDate;
    private Salesperson salesperson;
    private Sale sale;
    private Payment payment;

    public Invoice(Order order, Date invoiceDate, Sale sale, Salesperson salesperson) {
        this.order = order;
        this.invoiceDate = invoiceDate;
        this.sale = sale;
        this.salesperson = salesperson;
    }
//in hoá đơn
    public void printInvoice() {
        System.out.println("Invoice Information:");
        System.out.println("===============================");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " + order.getCustomerName());
        System.out.println("Invoice Date: " + invoiceDate);
        System.out.println("Salesperson: " + salesperson.getName());
        System.out.println("Total Amount: $" + sale.getTotalAmount());
        System.out.println("===============================");
    }
//lưu hoá đơn dô file
    public void saveToFile(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Invoice Information:\n");
            writer.write("===============================\n");
            writer.write("Order ID: " + order.getOrderId() + "\n");
            writer.write("Customer: " + order.getCustomerName() + "\n");
            writer.write("Invoice Date: " + invoiceDate + "\n");
            writer.write("Salesperson: " + salesperson.getName() + "\n");
            writer.write("Total Amount: $" + sale.getTotalAmount() + "\n");
            writer.write("===============================\n");
            writer.close();
            System.out.println("Hóa đơn đã được lưu vào file: " + fileName);
        } catch (IOException e) {
            System.out.println("Có lỗi khi lưu hóa đơn vào file.");
            e.printStackTrace();
        }
    }
//đọc hoá đơn từ file
    public void readFromFile(String fileName) {
        try {
            File file = new File(fileName);
            java.util.Scanner scanner = new java.util.Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
           System.out.println("Không tìm thấy file.");
            e.printStackTrace();
        }
    }
}
