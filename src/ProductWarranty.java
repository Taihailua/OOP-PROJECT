import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class ProductWarranty extends Warranty {
    static Scanner sc = new Scanner(System.in);

    @Override
    public void calculateRemainingWarranty() {
        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Định nghĩa 2 mốc thời gian ban đầu

        try {
            Date endDate = dateFormat.parse(getHanKetThuc());
            Date currentDate = new Date();
            long diffInMillies = endDate.getTime() - currentDate.getTime();
            long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies,
                    java.util.concurrent.TimeUnit.MILLISECONDS);
            if (diff > 0)
                System.out.println("So ngay bao hanh con lai: " + diff);
            else
                System.out.println("So ngay bao hanh con lai: 0");
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
            long diffInMillies = endDate.getTime() - currentDate.getTime();
            long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies,
                    java.util.concurrent.TimeUnit.MILLISECONDS);
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
        isWarrantyValid();
        calculateRemainingWarranty();
    }

}
