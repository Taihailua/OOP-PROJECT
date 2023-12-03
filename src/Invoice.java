
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Invoice {
    static Scanner sc= new Scanner(System.in);
    private Order order;
    private Date invoiceDate;
    private Employee salesperson=new Employee("NV001","Pham Van Kiet","0976204872","pvk210504@gmail.com","TPHCM","QL",40000000);

    private static boolean isPaid=false;
    public Invoice() {
    }

    public Invoice(Order order) {
        this.order = order;
    }
    
    public Invoice(Date invoiceDate, Employee salesperson) {
        
        this.invoiceDate = invoiceDate;
       
        this.salesperson = salesperson;
    }
    
    //hiển thị đơn hàng cho khách hàng xem trước khi xác nhận thanh toán
    public void order(){
        
        this.order.displayOrderDetail();
        System.out.println("ToTal:"+order.calculateTotalCost());
    }
    
    
    // xác nhận thanh toán của khách hàng
   public void payInvoice(){
       if(!isPaid){
           System.out.println("Thanh toan thanh cong!");
           isPaid=true;
         }
       else{
           System.out.println("Thanh toan that bai!"); 
       }
       return ;
   }
   //in hoá đơn sau khi khách hàng thanh toán thành công
   public void printInvoice() {
        if(isPaid==true){
        System.out.println("Invoice Information:");
        System.out.println("===============================");
        order.displayOrderDetail();
        System.out.println("ToTal:"+order.calculateTotalCost());
        System.out.println("Invoice Date: " + invoiceDate);
        System.out.println("===============================");
        System.out.println("Salesperson: " + salesperson.getName());
        saveToFile("data\\hoadon.txt");
        }
    }
//lưu hoá đơn vào file
    public void saveToFile(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Salesperson: " + salesperson.getName() + "\n");
            writer.write("Invoice Information:\n");
            writer.write("===============================\n");
            order.displayOrderDetail();
            writer.write("Invoice Date: " + invoiceDate + "\n");
            writer.write("===============================\n");
            writer.close();
            System.out.println("Hóa đơn đã được lưu vào file: " + fileName);
        } catch (IOException e) {
            System.out.println("Có lỗi khi lưu hóa đơn vào file.");
            e.printStackTrace();
        }
    }
    public void showMenu(){
         while(true){
            System.out.println("1.Hien thi don hang \n2.Thanh toan");
            System.out.println("\n0.Quay lai trang mua hang ");
            System.out.print("Moi chon chuc nang: ");
            int n=Integer.parseInt(sc.nextLine());
            switch(n){
                case 1: order();break;
                case 2: payInvoice();break;
                case 0: return;
                default: System.out.println("Sai cu phap");break;
            }
        }
    }
}