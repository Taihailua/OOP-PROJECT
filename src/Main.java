
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListCustomer appCustomer = new ListCustomer();
        ListEmployee appEmployee = new ListEmployee();
        ImportStock appProduct = new ImportStock();

        Order appOrder = new Order();

        // Thêm dữ liệu khách hàng ban đầu
        addInitialCustomerData(appCustomer);
        // Thêm dữ liệu nhân viên ban đầu
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
                                appOrder.showMenu();
                                break;
                            case 2:
                                Invoice HD=new Invoice(appOrder);
                                HD.showMenu();
                                break;
                            default:
                                System.out.println("Lua chon khong hop le. Hay chon lai.");
                        }
                    } while (customerChoice != 0);
                    break;
                case 2:
                    // Thực hiện chức năng của admin
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
                                appCustomer.showMenu();
                                break;
                            case 2:
                                appEmployee.showMenu();
                                break;
                            case 3:
                                appProduct.showMenu();

                                break;
                            case 4:
                                //appOrder.showMenu();

                                break;
                            case 0:
                                System.out.println("Tro ve thanh cong!");
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

    // Tạo dữ liệu khách hàng ban đầu
    private static void addInitialCustomerData(ListCustomer appCustomer) {
        Customer cus1 = new Customer("KH001", "Pham Van Kiet", "0976204872", "TPHCM", "pvk210504@gmail.com");
        Customer cus2 = new Customer("KH002", "Nguyen Van A", "0987654321", "Ha Noi", "nguyenvana@gmail.com");
        Customer cus3 = new Customer("KH003", "Tran Thi B", "0901122334", "Da Nang", "tranthib@gmail.com");
        Customer cus4 = new Customer("KH004", "Le Van C", "0901122339", "Can Tho", "levanc@gmail.com");
        Customer cus5 = new Customer("KH005", "Truong Tan D", "09011496566", "Ca Mau", "truongtand@gmail.com");

        appCustomer.addCustomerToArray(cus1);
        appCustomer.addCustomerToArray(cus2);
        appCustomer.addCustomerToArray(cus3);
        appCustomer.addCustomerToArray(cus4);
        appCustomer.addCustomerToArray(cus5);
    }

    // Tạo dữ liệu nhân viên ban đầu
    private static void addInitialEmployeeData(ListEmployee appEmployee) {
        Employee emp1 = new Employee("NV001", "Pham Van Kiet", "0976204872", "TPHCM", "pvk210504@gmail.com", "QL", 40000000);
        Employee emp2 = new Employee("NV002", "Truong Tan A", "0987654321", "Ha Noi", "nguyenvana@gmail.com", "NV", 10000000);
        Employee emp3 = new Employee("NV003", "Le Thi B", "0901122334", "Da Nang", "tranthib@gmail.com", "NV", 12000000);
        Employee emp4 = new Employee("NV004", "Tran Thi C", "0901122339", "Can Tho", "levanc@gmail.com", "NV", 8000000);
        Employee emp5 = new Employee("NV005", "Nguyen Van D", "09011496566", "Ca Mau", "truongtand@gmail.com", "NV", 6000000);

        appEmployee.addEmployeeToArray(emp1);
        appEmployee.addEmployeeToArray(emp2);
        appEmployee.addEmployeeToArray(emp3);
        appEmployee.addEmployeeToArray(emp4);
        appEmployee.addEmployeeToArray(emp5);
    }
}
