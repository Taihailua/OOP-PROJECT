
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
                long salary = Long.parseLong(txt[6]); // giả sử lương là long

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
        String name = getNonEmptyInput("Nhập tên: ");
        String phone = getValidPhoneNumberInput("Nhập số điện thoại: ");
        String address = getNonEmptyInput("Nhập địa chỉ: ");
        String email = getNonEmptyInput("Nhập email: ");
        String position = getValidPositionInput("Nhập vị trí: ");
        long salary = getValidSalaryInput("Nhập lương: ");

        Employee newEmployee = new Employee(idEmployee, name, phone, address, email, position, salary);
        addEmployeeToArray(newEmployee);
    }

    public void outputEmployee() {
        System.out.println("Danh sách nhân viên:");
        System.out.println(
                "__________________________________________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-15s%-25s%-20s%-20s\n", "Mã NV", "Tên", "Số điện thoại", "Địa chỉ", "Email",
                "Chức vụ", "Luong (VND)");
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
            // Tìm thấy nhân viên
            System.out.println("Nhập thông tin id nhân viên mới:");
            employeeList[index].inputEmployeeInfo();

            // Lưu thông tin cập nhật vào tệp tin
            writeToFile(employeeList);

            System.out.println("Cập nhật thành công!");
        } else {
            System.out.println("Không tìm thấy nhân viên với ID " + employeeId);
        }
    }

    public void deleteEmployeeById(String employeeId) {
        int index = findEmployeeIndexById(employeeId);

        if (index != -1) {
            // Tìm thấy nhân viên
            Employee[] newArray = new Employee[employeeList.length - 1];
            System.arraycopy(employeeList, 0, newArray, 0, index);
            System.arraycopy(employeeList, index + 1, newArray, index, employeeList.length - index - 1);
            employeeList = newArray;

            // Lưu thông tin cập nhật vào tệp tin
            writeToFile(employeeList);

            System.out.println("Xóa nhân viên thành công!");
        } else {
            System.out.println("Không tìm thấy nhân viên với ID " + employeeId);
        }
    }

    public void searchEmployeeById(String employeeId) {
        int index = findEmployeeIndexById(employeeId);

        if (index != -1) {
            // Tìm thấy nhân viên
            System.out.println("Thông tin nhân viên có mã " + employeeId + ":");
            System.out.println(employeeList[index]);
        } else {
            System.out.println("Không tìm thấy nhân viên với ID " + employeeId);
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
        // Sắp xếp danh sách nhân viên theo lương giảm dần
        Arrays.sort(employeeList, Comparator.comparing(Employee::getSalary).reversed());

        // In ra thông tin của nhân viên
        outputEmployee();
    }

    private String getValidPositionInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim().toUpperCase();
            if (!input.equals("QL") && !input.equals("NV")) {
                System.out.println("Chức vụ chỉ có thể là 'QL' hoặc 'NV'.");
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
                System.out.println("Mã nhân viên không đúng định dạng. Vui lòng nhập lại (VD: NV001).");
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
                System.out.println("Số điện thoại phải có ít nhất 10 chữ số. Vui lòng nhập lại.");
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
                System.out.println("Thông tin không được bỏ trống. Vui lòng nhập lại.");
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
                System.out.println("Lương phải là một số. Vui lòng nhập lại.");
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
            System.out.println("1. Xuất danh sách nhân viên");
            System.out.println("2. Thêm nhân viên");
            System.out.println("3. Xóa nhân viên theo ID");
            System.out.println("4. Sửa nhân viên theo ID");
            System.out.println("5. Tìm kiếm nhân viên theo ID");
            System.out.println("6. Tìm kiếm nhân viên theo tên");
            System.out.println("7. Thống kê nhân viên có mức lương giảm dần");
            System.out.println("8. Xuất thông tin nhân viên có mức lương cao nhất");
            System.out.println("9. Xuất danh sách nhân viên là Quản lý (QL)");
            System.out.println("0. Trở về");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 2:
                    inputEmployee();
                    break;
                case 1:
                    outputEmployee();
                    break;
                case 3:
                    System.out.print("Nhập mã ID nhân viên cần xóa: ");
                    String deleteId = scanner.next();
                    deleteEmployeeById(deleteId);
                    break;
                case 4:
                    System.out.print("Nhập mã ID nhân viên cần sửa: ");
                    String updateId = scanner.next();
                    updateEmployeeById(updateId);
                    break;
                case 5:
                    System.out.print("Nhập mã ID nhân viên cần tìm kiếm: ");
                    String searchId = scanner.next();
                    searchEmployeeById(searchId);
                    break;
                case 6:
                    scanner.nextLine(); // Dòng này để xử lý kí tự Enter còn lại sau khi nhập số
                    System.out.print("Nhập tên nhân viên cần tìm kiếm: ");
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
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Hãy chọn lại.");
            }
        } while (choice != 0);
    }

    void loadFromFile() {
        employeeList = readFromFile();
    }

    private String generateEmployeeId() {
        // Tìm mã nhân viên lớn nhất hiện tại
        int maxId = 0;
        for (Employee employee : employeeList) {
            String employeeId = employee.getIdEmployee();
            int idNumber = Integer.parseInt(employeeId.substring(2)); // Lấy phần số từ mã nhân viên
            if (idNumber > maxId) {
                maxId = idNumber;
            }
        }

        // Tạo mã nhân viên mới
        int newId = maxId + 1;
        return String.format("NV%03d", newId);
    }

    public void outputManagerEmployees() {
        System.out.println("Danh sách nhân viên là Quản lý (QL):");
        System.out.println(
                "_________________________________________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Mã NV", "Tên", "Số điện thoại", "Địa chỉ", "Email",
                "Chức vụ", "Luong");
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
        System.out.println("Danh sách nhân viên có tên giống với '" + name + "':");
        System.out.println(
                "_________________________________________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Mã NV", "Tên", "Số điện thoại", "Địa chỉ", "Email",
                "Chức vụ", "Luong");

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
            System.out.println("Danh sách nhân viên trống.");
            return;
        }

        // Tìm nhân viên có mức lương cao nhất
        Employee highestSalaryEmployee = employeeList[0];
        for (int i = 1; i < employeeList.length; i++) {
            if (employeeList[i].getSalary() > highestSalaryEmployee.getSalary()) {
                highestSalaryEmployee = employeeList[i];
            }
        }

        // In thông tin nhân viên có mức lương cao nhất
        System.out.println("Thông tin nhân viên có mức lương cao nhất:");
        System.out.println(
                "_________________________________________________________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "Mã NV", "Tên", "Số điện thoại", "Địa chỉ", "Email",
                "Chức vụ", "Luong");
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
