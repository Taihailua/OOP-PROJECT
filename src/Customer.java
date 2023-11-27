import java.util.Scanner;

public class Customer extends Person{
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
    
      public void inputCustomerInfo() {
        Scanner scanner = new Scanner(System.in);

        // Gọi phương thức inputInfo của lớp Person để nhập thông tin chung
        

        System.out.print("Nhap ma khach hang: ");
        this.idCustomer = scanner.nextLine();
        super.inputInfo();
    }

    @Override
    public String toString() {
        return "Customer{" + "\nidCustomer: " + idCustomer + "\nName: " + name + "\nPhone: " + phone + "\nAddress: " + address + "\nEmail: " + email+ '}' +"\n----------------------\n";
    }
      
}



