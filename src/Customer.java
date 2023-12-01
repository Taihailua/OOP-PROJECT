import java.util.Scanner;

public class Customer extends Person {
    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String idCustomer;

    public Customer() {
    }

    public Customer(String idCustomer, String name, String phone, String address, String email) {
        super(name, phone, address, email);
        this.idCustomer = idCustomer;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return idCustomer + ";" + name + ";" + phone + ";" + address + ";" + email;
    }

    public void inputCustomerInfo() {
        clearScreen();
        System.out.println("----------------------------------------------------------------");
        System.out.println("------------------Nhap thong tin cua ban: ----------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ten khach hang: ");
        setName(scanner.nextLine());

        System.out.print("Nhap so dien thoai: ");
        setPhone(scanner.nextLine());

        System.out.print("Nhap dia chi: ");
        setAddress(scanner.nextLine());

        System.out.print("Nhap email: ");
        setEmail(scanner.nextLine());
    }

}
