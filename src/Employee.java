/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

public class Employee extends Person {

    private String idEmployee;
    private String position;
    private long salary;

    public Employee() {
    }

    public Employee(String idEmployee, String position, long salary, String name, String phone, String address, String email) {
        super(name, phone, address, email);
        this.idEmployee = idEmployee;
        this.position = position;
        this.salary = salary;
    }

//    GET
    public String getIdEmployee() {
        return idEmployee;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

//   SET
    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public void setSalary(long salary) {
        this.salary = salary;
    }

//    HAM THAY DOI CHUC VI, LUONG
     public void changePosAndSal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap 1 de thay doi chuc vu, 2 de thay doi luong:");
        int choice = sc.nextInt();
        sc.nextLine();  // Đọc dòng mới sau khi đọc số

        if (choice == 1) {
            System.out.print("Nhap chuc vu moi: ");
            this.position = sc.nextLine();
            System.out.println("Da cap nhat chuc vu!");
        } else if (choice == 2) {
            System.out.print("Nhap luong moi: ");
            this.salary = sc.nextLong();
            System.out.println("Da cap nhat luong!");
        } else {
            System.out.println("Lua chon khong hop le.");
        }
    }

    public void inputEmployeeInfo() {
        Scanner scanner = new Scanner(System.in);

        // Gọi phương thức inputInfo của lớp Person để nhập thông tin chung
        System.out.print("Nhap ma nhan vien: ");
        this.idEmployee = scanner.nextLine();

        super.inputInfo();

        System.out.print("Nhap chuc vu: ");
        this.position = scanner.nextLine();

        System.out.print("Nhap luong: ");
        this.salary = scanner.nextLong();

    }

    @Override
    public String toString() {
        return "Employee{" + "\nidEmployee: " + idEmployee + "\nPosition: " + position + "\nSalary: " + salary + "\nName: " + name + "\nPhone: " + phone + "\nAddress: " + address + "\nEmail: " + email + '}' + "\n----------------------\n";
    }

    

}
