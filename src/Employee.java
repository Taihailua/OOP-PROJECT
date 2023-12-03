import java.util.Scanner;

public class Employee extends Person {
    private Scanner scanner = new Scanner(System.in);
    private String idEmployee;
    private String position;
    private long salary;

    public Employee() {
    }

    public Employee(String idEmployee, String name, String phone, String address, String email, String position,
            long salary) {
        super(name, phone, address, email);
        this.idEmployee = idEmployee;
        this.position = position;
        this.salary = salary;
    }

    // GET
    public String getIdEmployee() {
        return idEmployee;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    // SET
    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return idEmployee + ";" + name + ";" + phone + ";" + email + ";" + address + ";" + position + ";" + salary;
    }

    public void inputEmployeeInfo() {
        setName(getInput("Nhap ten nhan vien : "));
        setPhone(getInput("Nhap so dien thoai: "));
        setEmail(getInput("Nhap email: "));
        setAddress(getInput("Nhap dia chi: "));
        setPosition(getInput("Nhap vi tri: "));
        setSalary(Long.parseLong(getInput("Nhap luong co ban: ")));
    }

    private String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
