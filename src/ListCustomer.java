
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ListCustomer {
    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Customer[] customerList = new Customer[0];
    private Scanner scanner = new Scanner(System.in);

    public void writeToFile(Customer[] array) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data\\dataCustomer.txt"))) {
            for (Customer customer : array) {
                writer.write(customer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Customer[] readFromFile() {
        Customer[] array = new Customer[0];
        try (BufferedReader reader = new BufferedReader(new FileReader("data\\dataCustomer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] txt = line.split(";");
                String id = txt[0];
                String name = txt[1];
                String phone = txt[2];
                String address = txt[3];
                String email = txt[4];
                Customer newCustomer = new Customer(id, name, phone, address, email);
                array = Arrays.copyOf(array, array.length + 1);
                array[array.length - 1] = newCustomer;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public void inputCustomer() {
        scanner.nextLine();
        String id = generateCustomerId();
        String name = getNonEmptyInput("Nhap ten: ");
        String phone = getNonEmptyInput("Nhap so dien thoai: ");
        String address = getNonEmptyInput("Nhap dia chi: ");
        String email = getNonEmptyInput("Nhap email: ");

        // Tạo đối tượng Customer bằng cách sử dụng constructor với tham số
        Customer newCustomer = new Customer(id, name, phone, address, email);

        // Thêm khach hang vào mảng hoặc danh sách khach hang của bạn
        addCustomerToArray(newCustomer);
    }

    public void outputCustomer() {
        System.out.println("Danh sách khach hang:");
        System.out.println(
                "__________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Ma KH", "ten", "so dien thoai", "dia chi", "Email");
        System.out.println(
                "__________________________________________________________________________________________________");
        for (Customer customer : customerList) {
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    customer.getIdCustomer(),
                    customer.getName(),
                    customer.getPhone(),
                    customer.getAddress(),
                    customer.getEmail());
            System.out.println(
                    "--------------------------------------------------------------------------------------------------");

        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void addCustomerToArray(Customer newCustomer) {
        customerList = Arrays.copyOf(customerList, customerList.length + 1);
        customerList[customerList.length - 1] = newCustomer;
        writeToFile(customerList);
    }

    public void updateCustomerById(String customerId) {
        int index = findCustomerIndexById(customerId);

        if (index != -1) {
            // Tìm thấy khach hang
            System.out.println("Nhap thong tin khach hang moi:");
            customerList[index].inputCustomerInfo();

            // Lưu thông tin cập nhật vào tệp tin
            writeToFile(customerList);

            System.out.println("Cap nhat thanh cong !");
        } else {
            System.out.println("Khong tim thay khach hang voi ID " + customerId);
        }
    }

    private int findCustomerIndexById(String customerId) {
        for (int i = 0; i < customerList.length; i++) {
            if (customerList[i].getIdCustomer().equals(customerId)) {
                return i;
            }
        }
        return -1; // Không tìm thấy
    }

    public void deleteCustomerById(String customerId) {
        int index = findCustomerIndexById(customerId);

        if (index != -1) {
            // Tìm thấy khach hang
            Customer[] newArray = new Customer[customerList.length - 1];
            System.arraycopy(customerList, 0, newArray, 0, index);
            System.arraycopy(customerList, index + 1, newArray, index, customerList.length - index - 1);
            customerList = newArray;

            // Lưu thông tin cập nhật vào tệp tin
            writeToFile(customerList);

            System.out.println("Xoa khach hang thanh cong!");
        } else {
            System.out.println("Khong tim thay khach hang voi ID " + customerId);
        }
    }

    public void searchCustomerById(String customerId) {
        int index = findCustomerIndexById(customerId);

        if (index != -1) {
            System.out.println("Thông tin khach hang có ma " + customerId + ":");
            System.out.println(customerList[index]);
        } else {
            System.out.println("Khong tim thay khach hang voi ID " + customerId);
        }
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("__________________________________________________________ ");
            System.out.println("___________________ QUAN LY KHACH HANG ___________________ ");
            System.out.println("1. Xuat danh sach khach hang");
            System.out.println("2. Them khach hang ");
            System.out.println("3. Xoa khach hang theo ID");
            System.out.println("4. Sua khach hang theo ID");
            System.out.println("5. Tim kiem khach hang theo ID");
            System.out.println("6. Tim kiem khach hang theo ten");
            System.out.println("0. Trở về.");
            System.out.print("Nhap lựa chọn của bạn: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 2:
                    inputCustomer();
                    break;
                case 1:
                    outputCustomer();
                    break;
                case 3:
                    System.out.print("Nhap ma ID khach hang can xoa: ");
                    String deleteId = scanner.next();
                    deleteCustomerById(deleteId);
                    break;
                case 4:
                    System.out.print("Nhap ma ID khach hang can sua: ");
                    String updateId = scanner.next();
                    updateCustomerById(updateId);
                    break;
                case 5:
                    System.out.print("Nhap ma ID khach hang can tim kiem: ");
                    String searchId = scanner.next();
                    searchCustomerById(searchId);
                    break;
                case 6:
                    scanner.nextLine(); // Xử lý kí tự Enter còn lại sau khi Nhap số
                    System.out.print("Nhap ten khach hang can tim kiem: ");
                    String searchName = scanner.nextLine();
                    searchCustomerByName(searchName);
                    break;

                case 0:
                    clearScreen();
                    System.out.println("Tro ve thanh cong!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai");
            }
        } while (choice != 0);
    }

    void loadFromFile() {
        customerList = readFromFile();
    }

    private String getNonEmptyInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Khong duoc bo trong, vui long nhap lai !");
            }
        } while (input.isEmpty());
        return input;
    }

    private String generateCustomerId() {
        // Tìm Ma khach hang lớn nhất hiện tại
        int maxId = 0;
        for (Customer customer : customerList) {
            String customerId = customer.getIdCustomer();
            int idNumber = Integer.parseInt(customerId.substring(2)); // Lấy phần số từ Ma khach hang
            if (idNumber > maxId) {
                maxId = idNumber;
            }
        }

        // Tạo Ma khach hang mới
        int newId = maxId + 1;
        return String.format("KH%03d", newId);
    }

    public void searchCustomerByName(String name) {
        System.out.println("Danh sách khach hang có ten giong voi '" + name + "':");
        System.out.println(
                "__________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Ma KH", "ten", "so dien thoai", "dia chi", "Email");
        System.out.println(
                "__________________________________________________________________________________________________");
        for (Customer customer : customerList) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                        customer.getIdCustomer(),
                        customer.getName(),
                        customer.getPhone(),
                        customer.getAddress(),
                        customer.getEmail());
                System.out.println(
                        "--------------------------------------------------------------------------------------------------");
            }
        }
    }

}
