import java.util.Scanner;

public class Manufacturer {
    private String name,address,phoneNumber;

    public Manufacturer() {
    }

    public Manufacturer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
    public void input(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Nhap ten NSX: ");
        setName(sc.nextLine());
        System.out.print("Nhap dia chi NSX: ");
        setAddress(sc.nextLine());
        System.out.print("Nhap SDT NSX: ");
        setPhoneNumber(sc.nextLine());
    }
    
    public void newedit(){
        Scanner sc=new Scanner(System.in);
        System.out.print("New name NSX: ");
        setName(sc.nextLine());
        System.out.print("New address: ");
        setAddress(sc.nextLine());
        System.out.print("New phonenumber: ");
        setPhoneNumber(sc.nextLine());
    }

    @Override
    public String toString() {
        return name +"\n"+address+"\n"+phoneNumber;
    }
    
    public String output(){
        return ", nameNSX=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber;
    }
}
