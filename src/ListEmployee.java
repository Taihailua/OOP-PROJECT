
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

public class ListEmployee {
    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Employee[] employeeList = new Employee[0];
    private Scanner scanner = new Scanner(System.in);

    public void writeToFile(Employee[] array) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data\\dataEmployee.txt"))) {
            for (Employee employee : array) {
                writer.write(employee.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Employee[] readFromFile() {
        Employee[] array = new Employee[0];
        try (BufferedReader reader = new BufferedReader(new FileReader("data\\dataEmployee.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] txt = line.split(";");
                String idEmployee = txt[0];
                String name = txt[1];
                String phone = txt[2];
                String address = txt[3];
                String email = txt[4];
                String position = txt[5];
                long salary = Long.parseLong(txt[6]); // giả sử luong là long

                Employee newEmployee = new Employee(idEmployee, name, phone, address, email, position, salary);
                array = Arrays.copyOf(array, array.length + 1);
                array[array.length - 1] = newEmployee;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public void inputEmployee() {
        scanner.nextLine();
        String idEmployee = generateEmployeeId();
        String name = getNonEmptyInput("Nhap ten: ");
        String phone = getValidPhoneNumberInput("Nhap so dien thoai: ");
        String address = getNonEmptyInput("Nhap dia chi: ");
        String email = getNonEmptyInput("Nhap email: ");
        String position = getValidPositionInput("Nhap vi tri: ");
        long salary = getValidSalaryInput("Nhap luong: ");

        Employee newEmployee = new Employee(idEmployee, name, phone, address, email, position, salary);
        addEmployeeToArray(newEmployee);
        clearScreen();
    }

    public void outputEmployee() {
        System.out.println("Danh sach nhan vien:");
        System.out.println(
                "__________________________________________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-15s%-25s%-20s%-20s\n", "Ma NV", "Ten", "So dien thoai", "Dia chi", "Email",
                "Chuc vu", "Luong (VND)");
        System.out.println(
                "__________________________________________________________________________________________________________________________________");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        for (Employee employee : employeeList) {
            String formattedSalary = numberFormat.format(employee.getSalary());
            System.out.printf("%-20s%-20s%-20s%-15s%-25s%-20s%-20s\n",
                    employee.getIdEmployee(),
                    employee.getName(),
                    employee.getPhone(),
                    employee.getAddress(),
                    employee.getEmail(),
                    employee.getPosition(),
                    formattedSalary); // Add the missing salary specifier
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void addEmployeeToArray(Employee newEmployee) {
        employeeList = Arrays.copyOf(employeeList, employeeList.length + 1);
        employeeList[employeeList.length - 1] = newEmployee;
        writeToFile(employeeList);
    }

    public void updateEmployeeById(String employeeId) {
        int index = findEmployeeIndexById(employeeId);

        if (index != -1) {
            // Tìm thấy nhan vien
            System.out.println("Nhap thong tin nhan vien moi:");
            employeeList[index].inputEmployeeInfo();

            // Lưu thông tin cập nhật vào tệp tin
            writeToFile(employeeList);
            clearScreen();
            System.out.println("Cap nhat thanh cong!");
        } else {
            System.out.println("Khong tim thay nhan vien voi ID " + employeeId);
        }
    }

    public void deleteEmployeeById(String employeeId) {
        clearScreen();
        int index = findEmployeeIndexById(employeeId);

        if (index != -1) {
            // Tìm thấy nhan vien
            Employee[] newArray = new Employee[employeeList.length - 1];
            System.arraycopy(employeeList, 0, newArray, 0, index);
            System.arraycopy(employeeList, index + 1, newArray, index, employeeList.length - index - 1);
            employeeList = newArray;

            // Lưu thông tin cập nhật vào tệp tin
            writeToFile(employeeList);

            System.out.println("Xoa nhan vien thanh cong!");
        } else {
            System.out.println("Khong tim thay nhan vien voi ID " + employeeId);
        }

    }

    public void searchEmployeeById(String employeeId) {
        int index = findEmployeeIndexById(employeeId);

        if (index != -1) {
            // Tìm thấy nhan vien
            System.out.println("Thong tin nhan vien co ma " + employeeId + ":");
            System.out.println(employeeList[index]);
        } else {
            System.out.println("Khong tim thay nhan vien voi ID " + employeeId);
        }
    }

    private int findEmployeeIndexById(String employeeId) {
        for (int i = 0; i < employeeList.length; i++) {
            if (employeeList[i].getIdEmployee().equals(employeeId)) {
                return i;
            }
        }
        return -1; // Không tìm thấy
    }

    public void printSortedEmployees() {
        // Sắp xếp Danh sach nhan vien theo luong giảm dần
        Arrays.sort(employeeList, Comparator.comparing(Employee::getSalary).reversed());

        // In ra thông tin của nhan vien
        outputEmployee();
    }

    private String getValidPositionInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toUpperCase();
            if (!input.equals("QL") && !input.equals("NV")) {
                System.out.println("Chuc vu chi co the la 'NV' hoac 'QL'.");
            }
        } while (!input.equals("QL") && !input.equals("NV"));
        return input;
    }

    private String getValidEmployeeIdInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toUpperCase();
            if (!input.matches("NV\\d{3}")) {
                System.out.println("Ma nhan vien khong dung dinh dang. Vui long nhap lai (VD: NV001).");
            }
        } while (!input.matches("NV\\d{3}"));
        return input;
    }

    private boolean isValidPhoneNumber(String input) {
        // Loại bỏ khoảng trắng và kiểm tra có ít nhất 10 chữ số không
        return input.replaceAll("\\s", "").length() >= 10 && input.matches("\\d+");
    }

    private String getValidPhoneNumberInput(String prompt) {
        String input;
        boolean isValidPhoneNumber;

        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            isValidPhoneNumber = isValidPhoneNumber(input);

            if (!isValidPhoneNumber) {
                System.out.println("So dien thoai phai co it nhat 10 chu so. Vui long nhap lai.");
            }
        } while (!isValidPhoneNumber);

        return input;
    }

    private String getNonEmptyInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Thong tin khong duoc bo trong. Vui long nhap lai.");
            }
        } while (input.isEmpty());
        return input;
    }

    private long getValidSalaryInput(String prompt) {
        long salary = 0; // Khởi tạo giá trị mặc định
        boolean isValidInput;

        do {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                salary = Long.parseLong(input);
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Luong phai la mot so. Vui long nhap lai.");
                isValidInput = false;
            }
        } while (!isValidInput);

        return salary;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("_________________________________________________________ ");
            System.out.println("___________________ QUAN LY NHAN VIEN ___________________ ");
            System.out.println("1. Xuat Danh sach nhan vien");
            System.out.println("2. Thêm nhan vien");
            System.out.println("3. Xoa nhan vien theo ID");
            System.out.println("4. Sua nhan vien theo ID");
            System.out.println("5. Tim kiem nhan vien theo ID");
            System.out.println("6. Tim kiem nhan vien theo ten");
            System.out.println("7. Thong ke nhan vien có mức luong giam dan");
            System.out.println("8. Xuat thông tin nhan vien co muc luong cao nhat");
            System.out.println("9. Xuat Danh sach nhan vien là Quan ly (QL)");
            System.out.println("0. Tro ve");
            System.out.print("Nhap lua chon cua ban: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    clearScreen();
                    outputEmployee();
                    break;
                case 2:
                    clearScreen();
                    inputEmployee();
                    break;

                case 3:
                    clearScreen();
                    outputEmployee();
                    System.out.print("Nhap ma ID nhan vien can xoa: ");
                    String deleteId = scanner.next();
                    deleteEmployeeById(deleteId);
                    break;
                case 4:
                    clearScreen();
                    outputEmployee();
                    System.out.print("Nhap ma ID nhan vien can sua: ");
                    String updateId = scanner.next();
                    updateEmployeeById(updateId);
                    break;
                case 5:
                    System.out.print("Nhap ma ID nhan vien can tim kiem: ");
                    String searchId = scanner.next();
                    searchEmployeeById(searchId);
                    break;
                case 6:
                    scanner.nextLine(); // Dòng này để xử lý kí tự Enter còn lại sau khi Nhap số
                    System.out.print("Nhap ten nhan vien can tim kiem: ");
                    String searchName = scanner.nextLine();
                    searchEmployeeByName(searchName);
                    break;
                case 7:
                    printSortedEmployees();
                    break;
                case 9:
                    outputManagerEmployees();
                    break;
                case 8:
                    printHighestSalaryEmployee();
                    break;
                case 0:
                    clearScreen();
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (choice != 0);
    }

    void loadFromFile() {
        employeeList = readFromFile();
    }

    private String generateEmployeeId() {
        // Tìm ma nhan vien lớn nhất hiện tại
        int maxId = 0;
        for (Employee employee : employeeList) {
            String employeeId = employee.getIdEmployee();
            int idNumber = Integer.parseInt(employeeId.substring(2)); // Lấy phần số từ ma nhan vien
            if (idNumber > maxId) {
                maxId = idNumber;
            }
        }

        // Tạo ma nhan vien mới
        int newId = maxId + 1;
        return String.format("NV%03d", newId);
    }

    public void outputManagerEmployees() {
        System.out.println("Danh sach nhan vien la Quan ly (QL):");
        System.out.println(
                "_________________________________________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Ma NV", "Ten", "So dien thoai", "Dia chi", "Email",
                "Chuc vu", "Luong");
        System.out.println(
                "_________________________________________________________________________________________________________________________________");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        for (Employee employee : employeeList) {
            if ("QL".equals(employee.getPosition())) {
                String formattedSalary = numberFormat.format(employee.getSalary());
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
                        employee.getIdEmployee(),
                        employee.getName(),
                        employee.getPhone(),
                        employee.getAddress(),
                        employee.getEmail(),
                        employee.getPosition(),
                        formattedSalary); // Add the missing salary specifier
                System.out.println(
                        "---------------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    public void searchEmployeeByName(String name) {
        System.out.println("Danh sach nhan vien có ten giong voi '" + name + "':");
        System.out.println(
                "_________________________________________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Ma NV", "Ten", "So dien thoai", "Dia chi", "Email",
                "Chuc vu", "Luong");

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        for (Employee employee : employeeList) {
            if (employee.getName().toLowerCase().contains(name.toLowerCase())) {
                String formattedSalary = numberFormat.format(employee.getSalary());
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n",
                        employee.getIdEmployee(),
                        employee.getName(),
                        employee.getPhone(),
                        employee.getAddress(),
                        employee.getEmail(),
                        employee.getPosition(),
                        formattedSalary);
                System.out.println(
                        "---------------------------------------------------------------------------------------------------------------------------------");
            }
        }
    }

    public void printHighestSalaryEmployee() {
        if (employeeList.length == 0) {
            System.out.println("Danh sach nhan vien trong.");
            return;
        }

        // Tìm nhan vien có mức luong cao nhất
        Employee highestSalaryEmployee = employeeList[0];
        for (int i = 1; i < employeeList.length; i++) {
            if (employeeList[i].getSalary() > highestSalaryEmployee.getSalary()) {
                highestSalaryEmployee = employeeList[i];
            }
        }

        // In thông tin nhan vien có mức luong cao nhất
        System.out.println("Thông tin nhan vien có mức luong cao nhat:");
        System.out.println(
                "_________________________________________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Ma NV", "Ten", "So dien thoai", "Dia chi", "Email",
                "Chuc vu", "Luong");
        System.out.println(
                "_________________________________________________________________________________________________________________________________");

        // Tạo đối tượng NumberFormat với Locale.US để sử dụng dấu phẩy ngăn cách hàng
        // nghìn
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        // Chuyển đổi giá trị salary thành chuỗi với định dạng số có dấu phẩy
        String formattedSalary = numberFormat.format(highestSalaryEmployee.getSalary());

        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s VND\n",
                highestSalaryEmployee.getIdEmployee(),
                highestSalaryEmployee.getName(),
                highestSalaryEmployee.getPhone(),
                highestSalaryEmployee.getAddress(),
                highestSalaryEmployee.getEmail(),
                highestSalaryEmployee.getPosition(),
                formattedSalary);
        System.out.println(
                "---------------------------------------------------------------------------------------------------------------------------------");
    }

}
