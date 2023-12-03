import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
public class ProductWarrantyList {
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
                array[array.length - 1] = ob;
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


        public void calculateRemainingWarranty(String date) {
        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Định nghĩa 2 mốc thời gian ban đầu
        try {
            Date endDate = dateFormat.parse(date);
            Date currentDate = new Date();
            long diffInMillies = endDate.getTime() - currentDate.getTime();
            long diff = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
            if (diff > 0)
            System.out.println("So ngay bao hanh con lai: " + diff);
            else 
            System.out.println("So ngay bao hanh con lai: 0");
        } catch (java.text.ParseException e) {
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

    public void printWarrantyDetails(String date) {
        isWarrantyValid(date);
        calculateRemainingWarranty(date);
    }



    public void WarrantyMenu() {
            int choice;
            loadFromFile();
            do {

                System.out.println("__________________________________________________________ ");
                System.out.println("___________________ QUAN LY DANH SACH BAO HANH ___________________ ");
                System.out.println("1. Them khach hang dang ki dich vu bao hanh");
                System.out.println("2. In mang danh sach hien tai");
                System.out.println("3. Tim kiem va xem chi tiet thong tin bao hanh cua khach hang theo ten da nhap");
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
                        String name = getNonEmptyInput("Hay nhap ten khach hang muon gia han: ");
                        int i = findCustomerByName(name);
                        if (i != -1) {
                            String e = list[i].getHanKetThuc();
                            printWarrantyDetails(e);
                        } else {
                            System.out.println("Khong tim thay khach hang ten " + name);
                        }
                        break;
                    case 0:
                        System.out.println("Thoat ra thanh cong 1");
                        break;
                    default:
                        System.out.println("Lua chon khong hop le ! vui long nhap lai");
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
                System.out.println("Khong duoc bo trong, vui long nhap lai!");
            }
        } while (input.isEmpty());
        return input;
    }
}
