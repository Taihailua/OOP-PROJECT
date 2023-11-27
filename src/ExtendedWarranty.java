import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
public class ExtendedWarranty extends Warranty {
    static Scanner sc = new Scanner(System.in);
    @Override
    public void calculateRemainingWarranty() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar currentDate = Calendar.getInstance();
        Calendar newDate = Calendar.getInstance();
        try {
            System.out.println("Hay nhap thoi luong muon gia han: ");
            int extendedPeriod = Integer.parseInt(sc.nextLine());
            System.out.println("Hay chon thoi luong gia han (ngay/thang/nam)");
            String c = sc.nextLine();
            switch (c) {
                case "ngay":
                    currentDate.add(Calendar.DATE, extendedPeriod);
                    break;
                case "thang":
                    currentDate.add(Calendar.MONTH, extendedPeriod);
                    break;
                case "nam":
                    currentDate.add(Calendar.YEAR, extendedPeriod);
                    break;
                // Các trường hợp khác có thể thêm vào sau này
                default:
                    System.out.println("Thoi luong khong hop le !");
                    break;
            }
            String newActivateDateString = df.format(newDate.getTime());
            String newDateString = df.format(currentDate.getTime());
            setHanketThuc(newDateString);
            setHanBatDau(newActivateDateString);
            System.out.println("Gia han bao hanh thanh cong !");
            System.out.println("Da gia han them " + extendedPeriod +" "+ c);
        } catch (Exception e) {
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
            long diffInMillies =endDate.getTime() - currentDate.getTime();
            long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
            if (diff <= 0)
                System.out.println("Da het han bao hanh");
            else {
                System.out.println("Con han bao hanh");
                System.out.println("So ngay bao hanh con lai" + diff);
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printWarrantyDetails() {
        isWarrantyValid();
        calculateRemainingWarranty();
        output();
    }
}
