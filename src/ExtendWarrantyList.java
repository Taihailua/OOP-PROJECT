import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
public class ExtendWarrantyList {
    private ProductWarranty[] list = new ProductWarranty[0];
    private Scanner sc = new Scanner(System.in);

    public void writeToFile(ProductWarranty[] array) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data\\baohanh.txt"))) {
            for (ProductWarranty product : array) {
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ProductWarranty[] readFromFile() {
        ProductWarranty[] array = new ProductWarranty[0];

        try (BufferedReader reader = new BufferedReader(new FileReader("data\\baohanh.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] txt = line.split(";");
                String product = txt[0];
                String activation = txt[1];
                String expiration = txt[2];
                String serial = txt[3];
                String model = txt[4];
                String des = txt[5];
                String name = txt[6];
                ProductWarranty ob = new ProductWarranty(product, serial, model, des, name, activation, expiration);
                array = Arrays.copyOf(array, array.length + 1);
                array[array.length-1] = ob;
                

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public void printList() {
        System.out.println("Danh Sach Khach Hang Su Dung Dich Vu Bao Hanh");
        System.out.println("__________________________________________________________________________________________________");
        System.out.printf("%-17s%-17s%-17s%-17s%-17s%-17s%-17s\n", "TenDienThoai", "MaDienThoai", "NSX", "MoTa", "TenKhachHang", "NgayKichHoat", "NgayKetThuc");
        System.out.println("__________________________________________________________________________________________________");
        for (ProductWarranty ob : list) {
            System.out.printf("%-17s%-17s%-17s%-17s%-17s%-17s%-17s\n",
                    ob.getProduct(),
                    ob.getProductSerialNumber(),
                    ob.getProductModel(),
                    ob.getDescription(),
                    ob.getTenKhachHang(),
                    ob.getHanBatDau(),
                    ob.getHanKetThuc());
            System.out.println("--------------------------------------------------------------------------------------------------");
        }
        writeToFile(list);
    }

    public void addCustomer() {
        sc.nextLine();
        String product = getNonEmptyInput("Hay nhap ten san pham: ");
        String ngayBD = getNonEmptyInput("Hay nhap ma san pham: ");
        String ngayKT = getNonEmptyInput("Hay nhap nha san xuat: ");
        String MaDT = getNonEmptyInput("Hay mo ta loi: ");
        String NSX = getNonEmptyInput("Hay nhap ten khach hang: ");
        String description = getNonEmptyInput("Hay nhap ngay kich hoat bao hanh (Ghi chu: nhap theo DD/MM/YYYY): ");
        String name = getNonEmptyInput("Hay nhap ngay ket thuc dich vu bao hanh (Ghi chu: nhap theo DD/MM/YYYY): ");
        ProductWarranty ob = new ProductWarranty(product, MaDT, NSX, description, name, ngayBD, ngayKT);
        addCustomerToArray(ob);
    }

    public void addCustomerToArray(ProductWarranty ob) {
        list = Arrays.copyOf(list, list.length +1);
        list[list.length-1] = ob;
        writeToFile(list);
    }

    public int findCustomerByName(String name) {
        int i;
        for (i = 0; i < list.length; ++i) 
            if (list[i].getTenKhachHang().equals(name))
                return i;
        return -1;
    }


    public void calculateRemainingWarranty(String date, int i) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar currentDate = Calendar.getInstance();
        Calendar newDate = Calendar.getInstance();            
        try {
            Date endDate = dateFormat.parse(date);
            Date currDate = new Date();
            long diffInMillies = endDate.getTime() - currDate.getTime();
            long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
            int addi = (int) diff;
            if (diff <= 0 ) {
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
                list[i].setHanketThuc(newDateString);
                list[i].setHanBatDau(newActivateDateString);
                System.out.println("Gia han bao hanh thanh cong !");
                System.out.println("Da gia han them " + extendedPeriod +" "+ c);
            } else {
                System.out.println("Hay nhap thoi luong muon gia han: ");
                int extendedPeriod = Integer.parseInt(sc.nextLine());
                System.out.println("Hay chon thoi luong gia han (ngay/thang/nam)");
                String c = sc.nextLine();
                switch (c) {
                    case "ngay":
                        currentDate.add(Calendar.DATE, extendedPeriod + addi + 1);
                        break;
                    case "thang":
                        currentDate.add(Calendar.DATE,addi + 1);
                        currentDate.add(Calendar.MONTH, extendedPeriod);
                        break;
                    case "nam":
                        currentDate.add(Calendar.DATE,addi + 1);
                        currentDate.add(Calendar.YEAR, extendedPeriod);
                        break;
                    // Các trường hợp khác có thể thêm vào sau này
                    default:
                        System.out.println("Thoi luong khong hop le !");
                        break;
                }
                String newActivateDateString = df.format(newDate.getTime());
                String newDateString = df.format(currentDate.getTime());
                list[i].setHanketThuc(newDateString);
                list[i].setHanBatDau(newActivateDateString);
                System.out.println("Gia han bao hanh thanh cong !");
                System.out.println("Da gia han them " + extendedPeriod +" "+ c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void isWarrantyValid(String date) {
        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Định nghĩa 2 mốc thời gian ban đầu
        try {
            Date endDate = dateFormat.parse(date);
            Date currentDate = new Date();
            long diffInMillies = endDate.getTime() - currentDate.getTime();
            long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
            if (diff <= 0)
                System.out.println("Da het han bao hanh");
            else
                System.out.println("Con han bao hanh");
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }





    public void extendWarrantyMenu() {
            int choice;
            loadFromFile();
            do {
                System.out.println("__________________________________________________________ ");
                System.out.println("___________________ QUAN LY DANH SACH BAO HANH ___________________ ");
                System.out.println("3. In mang danh sach hien tai");
                System.out.println("4. Tim va gia han bao hanh cua khach hang theo ten da tim");
                System.out.println("0. Exit.");
                System.out.print("Enter your option: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1: 
                        printList();
                        break;
                    case 2: 
                        addCustomer();
                        break;
                    case 3:
                        printList();
                        break;
                    case 4:
                        sc.nextLine();
                        String name = getNonEmptyInput("Please enter name: ");
                        int i = findCustomerByName(name);
                        if (i != -1) {
                            String e = list[i].getHanKetThuc();
                            calculateRemainingWarranty(e, i);
                            e = list[i].getHanKetThuc();
                            isWarrantyValid(e);
                            writeToFile(list);
                        } else {
                            System.out.println("Not found Customer whose name is " + name);
                        }
                        break;
                    case 0:
                        System.out.println("Exit successfully");
                        break;
                    default:
                        System.out.println("Invalid option ! please re enter your option");
                }

            } while (choice != 0);
    }
    void loadFromFile() {
        list = readFromFile();
    }
    private String getNonEmptyInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Không được bỏ trống, vui lòng nhập lại!");
            }
        } while (input.isEmpty());
        return input;
    }
}
