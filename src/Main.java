
import java.util.Scanner;

public class Main {

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Tạo dữ liệu khách hàng
    private static void addInitialCustomerData(ListCustomer appCustomer) {
        appCustomer.loadFromFile();
    }

    // Tạo dữ liệu nhân viên
    private static void addInitialEmployeeData(ListEmployee appEmployee) {
        appEmployee.loadFromFile();
    }

    public static void main(String[] args) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        ListCustomer appCustomer = new ListCustomer();
        ListEmployee appEmployee = new ListEmployee();
        ImportStock appProduct = new ImportStock();

        Order appOrder = new Order();

        // Thêm dữ liệu khách hàng
        addInitialCustomerData(appCustomer);
        // Thêm dữ liệu nhân viên
        addInitialEmployeeData(appEmployee);

        int mainChoice;
        do {
            System.out.println("__________________________________________________________");
            System.out.println("__/__/__/__/__/__/_CUA HANG DIEN THOAI __/__/__/__/__/__/");
            System.out.println("1. Chuc nang Khach hang");
            System.out.println("2. Chuc nang Admin");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            mainChoice = scanner.nextInt();

            switch (mainChoice) {
                case 1:
                    // Thực hiện chức năng của khách hàng
                    clearScreen();
                    int customerChoice;
                    do {
                        System.out.println("____________________________________________________________");
                        System.out.println("___________________ Chao mung khach hang ___________________");
                        System.out.println("1. Quan ly gio hang");
                        System.out.println("2. Den trang thanh toan");
                        System.out.println("0. Thoat");
                        System.out.print("Nhap lua chon cua ban: ");
                        customerChoice = scanner.nextInt();
                        switch (customerChoice) {
                            case 1:
                                clearScreen();
                                appOrder.showMenu();
                                break;
                            case 2:
                                clearScreen();
                                Invoice HD=new Invoice(appOrder);
                                HD.showMenu();
                                break;
                            default:
                                System.out.println("Lua chon khong hop le. Hay chon lai.");
                        }
                    } while (customerChoice != 0);
                    clearScreen();
                    break;
                case 2:
                    // Thực hiện chức năng của admin
                    clearScreen();
                    int adminChoice;
                    do {
                        System.out.println("_______________________________________________________");
                        System.out.println("___________________ Chuc nang admin ___________________");
                        System.out.println("1. Quan ly khach hang");
                        System.out.println("2. Quan ly nhan vien");
                        System.out.println("3. Quan ly san pham");
                        System.out.println("4. Quan ly hoa don (Report)");
                        System.out.println("0. Thoat");
                        System.out.print("Nhap lua chon cua ban: ");
                        adminChoice = scanner.nextInt();

                        switch (adminChoice) {
                            case 1:
                                clearScreen();
                                appCustomer.showMenu();
                                break;
                            case 2:
                                clearScreen();
                                appEmployee.showMenu();
                                break;
                            case 3:
                                clearScreen();
                                appProduct.showMenu();
                                break;
                            case 4:
                                clearScreen();
                                appOrder.showMenu();
                                break;
                            case 0:
                                clearScreen();
                                break;

                            default:
                                System.out.println("Lua chon khong hop le. Hay chon lai.");
                        }
                    } while (adminChoice != 0);
                    break;
                case 0:
                    System.out.println("Tam Biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Hay chon lai.");
            }
        } while (mainChoice != 0);
    }
}
