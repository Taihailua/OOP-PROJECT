import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class ProductWarranty extends Warranty {
    private String productSerialNumber; // sẽ bổ sung sau
    private String productModel; // sẽ bổ sung sau

    

    @Override
    public void calculateRemainingWarranty() {
        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        // Định nghĩa 2 mốc thời gian ban đầu
        
        try {
            Date endDate = dateFormat.parse(getHanKetThuc());
            Date currentDate = new Date();
            long diffInMillies = Math.abs(endDate.getTime() - currentDate.getTime());
            long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);

            System.out.println("So ngay bao hanh con lai: " + diff);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void isWarrantyValid() {
        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Định nghĩa 2 mốc thời gian ban đầu
        try {
            Date endDate = dateFormat.parse(getHanKetThuc());
            Date currentDate = new Date();
            long diffInMillies = Math.abs(endDate.getTime() - currentDate.getTime());
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
    public void printWarrantyDetails() {

    }

}