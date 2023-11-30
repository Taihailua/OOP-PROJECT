
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ListCustomer {

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
        String name = getNonEmptyInput("Nhập tên: ");
        String phone = getNonEmptyInput("Nhập số điện thoại: ");
        String address = getNonEmptyInput("Nhập địa chỉ: ");
        String email = getNonEmptyInput("Nhập email: ");

        // Tạo đối tượng Customer bằng cách sử dụng constructor với tham số
        Customer newCustomer = new Customer(id, name, phone, address, email);

        // Thêm khách hàng vào mảng hoặc danh sách khách hàng của bạn
        addCustomerToArray(newCustomer);
    }

    public void outputCustomer() {
        System.out.println("Danh sách khách hàng:");
        System.out.println("__________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Mã KH", "Tên", "Số điện thoại", "Địa chỉ", "Email");
        System.out.println("__________________________________________________________________________________________________");
        for (Customer customer : customerList) {
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                    customer.getIdCustomer(),
                    customer.getName(),
                    customer.getPhone(),
                    customer.getAddress(),
                    customer.getEmail());
            System.out.println("--------------------------------------------------------------------------------------------------");

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
            // Tìm thấy khách hàng
            System.out.println("Nhập thông tin khách hàng mới:");
            customerList[index].inputCustomerInfo();

            // Lưu thông tin cập nhật vào tệp tin
            writeToFile(customerList);

            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Không tìm thấy khách hàng với ID " + customerId);
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
            // Tìm thấy khách hàng
            Customer[] newArray = new Customer[customerList.length - 1];
            System.arraycopy(customerList, 0, newArray, 0, index);
            System.arraycopy(customerList, index + 1, newArray, index, customerList.length - index - 1);
            customerList = newArray;

            // Lưu thông tin cập nhật vào tệp tin
            writeToFile(customerList);

            System.out.println("Xóa khách hàng thành công!");
        } else {
            System.out.println("Không tìm thấy khách hàng với ID " + customerId);
        }
    }

    public void searchCustomerById(String customerId) {
        int index = findCustomerIndexById(customerId);

        if (index != -1) {
            System.out.println("Thông tin khách hàng có mã " + customerId + ":");
            System.out.println(customerList[index]);
        } else {
            System.out.println("Không tìm thấy khách hàng với ID " + customerId);
        }
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("__________________________________________________________ ");
            System.out.println("___________________ QUAN LY KHACH HANG ___________________ ");
            System.out.println("1. Xuất danh sách khách hàng");
            System.out.println("2. Thêm khách hàng ");
            System.out.println("3. Xóa khách hàng theo ID");
            System.out.println("4. Sửa khách hàng theo ID");
            System.out.println("5. Tìm kiếm khách hàng theo ID");
            System.out.println("6. Tìm kiếm khách hàng theo tên");
            System.out.println("0. Trở về.");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 2:
                    inputCustomer();
                    break;
                case 1:
                    outputCustomer();
                    break;
                case 3:
                    System.out.print("Nhập mã ID khách hàng cần xóa: ");
                    String deleteId = scanner.next();
                    deleteCustomerById(deleteId);
                    break;
                case 4:
                    System.out.print("Nhập mã ID khách hàng cần sửa: ");
                    String updateId = scanner.next();
                    updateCustomerById(updateId);
                    break;
                case 5:
                    System.out.print("Nhập mã ID khách hàng cần tìm kiếm: ");
                    String searchId = scanner.next();
                    searchCustomerById(searchId);
                    break;
                case 6:
                    scanner.nextLine(); // Xử lý kí tự Enter còn lại sau khi nhập số
                    System.out.print("Nhập tên khách hàng cần tìm kiếm: ");
                    String searchName = scanner.nextLine();
                    searchCustomerByName(searchName);
                    break;

                case 0:
                    System.out.println("Trở về thành công!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại.");
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
                System.out.println("Không được bỏ trống, vui lòng nhập lại!");
            }
        } while (input.isEmpty());
        return input;
    }

    private String generateCustomerId() {
        // Tìm mã khách hàng lớn nhất hiện tại
        int maxId = 0;
        for (Customer customer : customerList) {
            String customerId = customer.getIdCustomer();
            int idNumber = Integer.parseInt(customerId.substring(2)); // Lấy phần số từ mã khách hàng
            if (idNumber > maxId) {
                maxId = idNumber;
            }
        }

        // Tạo mã khách hàng mới
        int newId = maxId + 1;
        return String.format("KH%03d", newId);
    }

    public void searchCustomerByName(String name) {
        System.out.println("Danh sách khách hàng có tên giống với '" + name + "':");
        System.out.println("__________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "Mã KH", "Tên", "Số điện thoại", "Địa chỉ", "Email");
        System.out.println("__________________________________________________________________________________________________");
        for (Customer customer : customerList) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.printf("%-20s%-20s%-20s%-20s%-20s\n",
                        customer.getIdCustomer(),
                        customer.getName(),
                        customer.getPhone(),
                        customer.getAddress(),
                        customer.getEmail());
                System.out.println("--------------------------------------------------------------------------------------------------");
            }
        }
    }

}
