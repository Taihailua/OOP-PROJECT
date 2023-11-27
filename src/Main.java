import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Khai bao
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        Customer[] arrCustomer = null;
        Employee[] arrEmployee = null;

        do {
            System.out.println("Chon chuc nang:");
            System.out.println("1. Nhap thong tin Khach hang");
            System.out.println("2. Nhap thong tin Nhan Vien");
            System.out.println("3. In thong tin Tat ca Khach hang");
            System.out.println("4. In thong tin Tat ca Nhan Vien");
            System.out.println("5. Thay doi vi tri, luong nhan vien:");
            System.out.println("6. Thoat");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Nhap so luong khach hang: ");
                    int n = sc.nextInt();
                    arrCustomer = new Customer[n];
                    for (int i = 0; i < n; i++) {
                        Customer newCus = new Customer();
                        newCus.inputCustomerInfo();
                        arrCustomer[i] = newCus;
                    }
                    System.out.println("Nhap tat ca khach hang thanh cong!");
                    break;

                case 2:
                    System.out.println("Nhap so luong Nhan Vien: ");
                    int m = sc.nextInt();
                    arrEmployee = new Employee[m];
                    for (int i = 0; i < m; i++) {
                        Employee newEmp = new Employee();
                        newEmp.inputEmployeeInfo();
                        arrEmployee[i] = newEmp;
                    }
                    System.out.println("Nhap tat ca nhan vien thanh cong!");
                    break;

                case 3:
                    System.out.println("Thong tin tat ca cac khach hang: ");
                    if (arrCustomer != null) {
                        for (Customer customer : arrCustomer) {
                            System.out.println(customer);
                        }
                    } else {
                        System.out.println("Chua co thong tin khach hang.");
                    }
                    break;

                case 4:
                    System.out.println("Thong tin tat ca nhan vien: ");
                    if (arrEmployee != null) {
                        for (Employee employee : arrEmployee) {
                            System.out.println(employee);
                        }
                    } else {
                        System.out.println("Chua co thong tin nhan vien.");
                    }
                    break;

                // Trong lớp Main
                case 5:
                    if (arrEmployee != null) {
                        System.out.print("Nhap ma so nhan vien muon thay doi: ");
                        String maNhanVien = sc.nextLine();
                        sc.nextLine();  // Đọc dòng mới sau khi đọc số

                        boolean found = false;
                        for (Employee employee : arrEmployee) {
                            if (employee.getIdEmployee()== maNhanVien) {
                                employee.changePosAndSal();
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Khong tim thay nhan vien co ma so " + maNhanVien);
                        }
                    } else {
                        System.out.println("Chua co thong tin nhan vien.");
                    }
                    break;

                case 6:
                    flag = false;
                    break;

                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
            }
        } while (flag);
    }
}
