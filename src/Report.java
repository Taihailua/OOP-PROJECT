
public class Report {
  private Order []salesData = new Order[0];
  private int countSalesdata=0;
 // private Invoice []revenueData =new Invoice[0];
  //private int countRevenueData=0;
   private ImportStock DSDT =new ImportStock();
  public void addOrder(Order order){
      for(int i=0;i<salesData.length;i++){
          countSalesdata++;
          salesData[i]=order;
          return;
      }
  }
//  public void addInvoice(Invoice invoice){
//      for(int i=0;i<revenueData.length;i++){
//          countRevenuedata++;
//          revenueData[i]=invoice;
//          return;
//      }
//  }
  public void displayStock(){
      DSDT.output();
  }
  
}
