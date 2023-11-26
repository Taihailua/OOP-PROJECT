import java.util.Arrays;
public class Report {
  private Order []salesData = new Order[0];
  private int countSalesdata=0;
  private Invoice []revenueData =new Invoice[0];
  private int countRevenueData=0;
  private ImportStock DSDT =new ImportStock();
  public void addOrder(Order order){
     countSalesdata++;
       salesData=Arrays.copyOf(salesData,countSalesdata);
       salesData[countSalesdata-1]=new Order();
       order.input();
       salesData[countSalesdata-1]=order;
  }
//  public void addInvoice(Invoice invoice){
//      countRevenueData++;
//       revenueData=Arrays.copyOf(revenueData,countRevenueData);
//       revenueData[countRevenueData-1]=new Invoice();
//       
//       revenueData[countRevenueData-1]=invoice;
//  }
  public void displayStock(){
      DSDT.output();
  }
  
}
