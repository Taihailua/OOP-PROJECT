
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author KIET
 */
public class Person {
    protected String name;
    protected String phone;
    protected String address;
    protected String email;

    public Person() {
    }

    public Person(String name, String phone, String address, String email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    // GET
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

    // SET
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

    public void inputInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap ten : ");
        this.name = scanner.nextLine();

        System.out.print("Nhap sdt: ");
        this.phone = scanner.nextLine();

        System.out.print("Nhap dia chi: ");
        this.address = scanner.nextLine();

        System.out.print("Nhap email: ");
        this.email = scanner.nextLine();
    }
}
