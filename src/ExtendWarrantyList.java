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
            writer.write("Phone's name;Serial Number;Model;description;customer's name;Activation Date;Expiration Date");
            writer.newLine();
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

        try (BufferedReader reader = new BufferedReader(new FileReader("data\\hoadondemo.txt"))) {
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
        System.out.println("List of customers subscribing to Warranty services");
        System.out.println("__________________________________________________________________________________________________");
        System.out.printf("%-17s%-17s%-17s%-17s%-17s%-17s%-17s\n", "PhoneName", "Serial", "Model", "Description", "Customer's name", "Activation Date", "Expiration Date");
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
        String product = getNonEmptyInput("Please enter product's name: ");
        String activation = getNonEmptyInput("Please enter product's serial number: ");
        String expiration = getNonEmptyInput("Please enter product's model: ");
        String serial = getNonEmptyInput("Please describe the product's problem: ");
        String model = getNonEmptyInput("Please enter customer's name: ");
        String description = getNonEmptyInput("Please enter warranty activation date (NOTE: enter as DD/MM/YYYY): ");
        String name = getNonEmptyInput("Please enter warranty expiration date (NOTE: enter as DD/MM/YYYY): ");
        ProductWarranty ob = new ProductWarranty(product, serial, model, description, name, activation, expiration);
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

    public void printSearchCustomerByName(String name) {
        int index = findCustomerByName(name);
        if (index != -1) {
            System.out.println("Information of Customer" + name + "subscribing to warranty services: ");
            System.out.println(list[index]);
        } else {
            System.out.println("Not found Customer whose name is " + name);
        }
    }

    public void deleteCustomerByName(String name) {
        int index = findCustomerByName(name);
        if (index != -1) {
            ProductWarranty[] newArray = new ProductWarranty[list.length - 1];
            System.arraycopy(list, 0, newArray, 0, index);
            System.arraycopy(list, index + 1, newArray, index, list.length - index - 1);
            list = newArray;
            writeToFile(list);
            System.out.println("Delete successfully");
        } else {
            System.out.println("Customer's name not found");
        }

    }

    public void calculateRemainingWarranty(String date, int i) {
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
            list[i].setHanketThuc(newDateString);
            list[i].setHanBatDau(newActivateDateString);
            System.out.println("Gia han bao hanh thanh cong !");
            System.out.println("Da gia han them " + extendedPeriod +" "+ c);
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
                System.out.println("1. Truy xuat danh sach va in du lieu trong file hoadon.txt");
                System.out.println("2. Add new customer subscribing to warranty services ");
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
