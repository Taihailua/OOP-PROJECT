
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ImportStock implements FileIO {

    Phone[] products = new Phone[0];
    private int[] amount = new int[0];
    private int len = 0;
//    private Employee receiver;
    static Scanner sc = new Scanner(System.in);

    public ImportStock() {
        readFromFile("data\\sanpham.txt");
    }

    public ImportStock(int n) {
    }

    @Override
    public void readFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                len++;
                products = Arrays.copyOf(products, len);
                ProductCategory Category = new ProductCategory();
                Manufacturer NSX = new Manufacturer();
                products[len - 1] = new Phone(br.readLine(), br.readLine(), Integer.parseInt(br.readLine()), Category, NSX);
                Category.setSoDong(Integer.parseInt(br.readLine()));
                String description = "";
                int n = Category.getSoDong();
                while (n > 0) {
                    description += br.readLine() + "\n";
                    n--;
                }
                Category.setDescription(description);
                NSX.setName(br.readLine());
                NSX.setAddress(br.readLine());
                NSX.setPhoneNumber(br.readLine());
                amount = Arrays.copyOf(amount, len);
                amount[len - 1] = Integer.parseInt(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile(String fileName, String data) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        int i = 1;
        String temp = "";
        for (Phone x : products) {
            temp += "DT" + i + "\n";
            temp += x.getName() + "\n";
            temp += x.getPhoneID() + "\n";
            temp += x.getPrice() + "\n";
            temp += x.getCategory();
            temp += x.getNSX() + "\n";
            temp += amount[i - 1] + "\n";
            i++;
        }
        writeToFile("data\\sanpham.txt", temp);
    }

    public int findID(String phoneID) {
        for (int i = 0; i < len; i++) {
            if (products[i].getPhoneID().equals(phoneID)) {
                return i;
            }
        }
        return -1;
    }

    public void findName() {
        System.out.print("Nhap ten san pham: ");
        String name = sc.nextLine();
        ImportStock list = new ImportStock(0);//constructor dung de tao doi tuong empty ImportStock, doi so n khong co tac dung
        for (int i = 0; i < len; i++) {
            if (products[i].getName().toLowerCase().contains(name.toLowerCase())) {
                list.addListFind(products[i], amount[i]);
            }
        }
        list.output();
    }

    public void add() {
        System.out.print("Nhap ma dien thoai can them: ");
        String ID = sc.nextLine();

        int pos = findID(ID);
        if (pos == -1) {
            len++;
            products = Arrays.copyOf(products, len);
            products[len - 1] = new Phone();
            products[len - 1].input(ID);
            System.out.print("Nhap so luong dien thoai: ");
            int SoLuong = Integer.parseInt(sc.nextLine());
            amount = Arrays.copyOf(amount, len);
            amount[len - 1] = SoLuong;
        } else {
            System.out.print("Nhap so luong dien thoai: ");
            amount[len - 1] += Integer.parseInt(sc.nextLine());
        }
        update();
    }

    public void addListFind(Phone phone, int amount) {
        int pos = findID(phone.getPhoneID());
        if (pos == -1) {
            len++;
            products = Arrays.copyOf(products, len);
            products[len - 1] = new Phone();
            products[len - 1] = phone;
            this.amount = Arrays.copyOf(this.amount, len);
            this.amount[len - 1] = amount;
        } else {
            this.amount[len - 1] += Integer.parseInt(sc.nextLine());
        }
    }

    public void add(String ID, int amount) {
        int pos = findID(ID);
        if (pos == -1) {
            len++;
            products = Arrays.copyOf(products, len);
            products[len - 1] = new Phone();
            products[len - 1].input(ID);
            this.amount = Arrays.copyOf(this.amount, len);
            this.amount[len - 1] = amount;
        } else {
            this.amount[len - 1] += amount;
        }
        update();
    }

    public void add(Phone phone, int amount) {
        int pos = findID(phone.getPhoneID());
        if (pos == -1) {
            len++;
            products = Arrays.copyOf(products, len);
            products[len - 1] = new Phone();
            products[len - 1] = phone;
            this.amount = Arrays.copyOf(this.amount, len);
            this.amount[len - 1] = amount;
        } else {
            this.amount[len - 1] += Integer.parseInt(sc.nextLine());
        }
        update();
    }

    public void edit() {
        System.out.print("Nhap ma dien thoai can sua: ");
        String ID = sc.nextLine();
        int pos = findID(ID);
        if (pos == -1) {
            System.out.println("Khong tim thay dien thoai!");
            return;
        }
        System.out.println("1. Edit name\n2. Edit price");
        System.out.println("3. Edit Category\n4. Edit NSX\n0. Exit");
        while (true) {
            System.out.print("Moi chon chuc nang: ");
            int n = Integer.parseInt(sc.nextLine());
            switch (n) {
                case 1:
                    System.out.print("New name: ");
                    products[pos].setName(sc.nextLine());
                    break;
                case 2:
                    System.out.print("New price: ");
                    products[pos].setPrice(Integer.parseInt(sc.nextLine()));
                    break;
                case 3:
                    ProductCategory newCategory = new ProductCategory();
                    newCategory.input();
                    products[pos].setCategory(newCategory);
                    break;
                case 4:
                    Manufacturer newNSX = new Manufacturer();
                    newNSX.newedit();
                    products[pos].setNSX(newNSX);
                    break;
                case 0:
                    update();
                    return;
                default:
                    System.out.println("Sai cu phap");
                    break;
            }
        }

    }

    public void remove(String ID) {
        int pos = findID(ID);
        if (pos == -1) {
            System.out.println("Khong tim thay");
            return;
        }

        for (int i = pos + 1; i < len; i++) {
            products[i - 1] = products[i];
        }
        len--;
        products = Arrays.copyOf(products, len);

        for (int i = pos + 1; i < len + 1; i++) {
            amount[i - 1] = amount[i];
        }
        amount = Arrays.copyOf(amount, len);
        System.out.println("remove completed");
        update();
    }

    public void remove() {
        System.out.print("Nhap ID san pham muon xoa: ");
        int pos = findID(sc.nextLine());
        if (pos == -1) {
            System.out.println("Khong tim thay");
            return;
        }

        for (int i = pos + 1; i < len; i++) {
            products[i - 1] = products[i];
        }
        len--;
        products = Arrays.copyOf(products, len);

        for (int i = pos + 1; i < len + 1; i++) {
            amount[i - 1] = amount[i];
        }
        amount = Arrays.copyOf(amount, len);
        System.out.println("remove completed");
        update();
    }

    public void output() {
        System.out.println("-----------------Danh Sach Dien thoai-------------------");
        System.out.println("__________________________________________________________________________________");
        System.out.println(String.format("|%-10s|%-20s|%-15s|%-15s|%-6s|", "ID", "NAME", "NSX Name", "PRICE(VND)", "AMOUNT"));
        System.out.println("__________________________________________________________________________________");
        for (int i = 0; i < len; i++) {
            System.out.print(String.format("|%-10s|%-20s|%-15s|%-15s|%-6d|",
                    products[i].getPhoneID(),
                    products[i].getName(),
                    products[i].getNSX().getName(),
                    products[i].DecFormat(products[i].getPrice()),
                    amount[i]));
            System.out.println("\n-----------------------------------------------------------------------------------");
        }
    }

    public void sell(String phoneID, int amount) {
        int pos = findID(phoneID);
        if (pos == -1) {
            System.out.println("Dien thoai nay khong ton tai.");
            return;
        }
        if (this.amount[pos] < amount) {
            System.out.println("Dien thoai " + products[pos].getName()
                    + " chi con " + this.amount[pos] + " san pham.");
            return;
        }
        this.amount[pos] -= amount;
        update();
    }

    public void showMenu() {
        while (true) {
            System.out.println("________________________________________________________ ");
            System.out.println("___________________ QUAN LY SAN PHAM ___________________ ");
            System.out.println("1. Hien thi danh sach san pham.");
            System.out.println("2. Them san pham.");
            System.out.println("3. Sua san pham.");
            System.out.println("4. Xoa san pham.");
            System.out.println("5. Sap xep san pham theo gia giam dan."); // Tùy chọn mới
            System.out.println("6. Sap xep san pham theo so luong giam dan.");
            System.out.println("7. Sap xep san pham theo ten A-Z");// Tùy chọn mới
            System.out.println("8. Loc san pham theo ID"); // Tùy chọn mới
            System.out.println("9. Loc san pham theo ten");
            System.out.println("10. Loc san pham theo NSX.");
            System.out.println("0. Exit.");
            System.out.print("Moi chon chuc nang: ");
            int n = Integer.parseInt(sc.nextLine());
            switch (n) {
                case 1: output(); break;
                case 2: add(); break;
                case 3: edit(); break;
                case 4: remove(); break;
                case 5:
                    sortByPriceDescending(); // Thực hiện sắp xếp
                    output(); // Hiển thị danh sách đã sắp xếp
                    break;
                case 6:
                    sortByAmountDescending(); // Thực hiện sắp xếp
                    output(); // Hiển thị danh sách đã sắp xếp
                    break;
                case 7:
                    sortProductsByNameAZ(); // Thực hiện lọc sản phẩm theo NSX
                    break;
                case 8:
                    filterProductsById(); // Thực hiện lọc sản phẩm theo tên
                    break;
                case 9:
                    filterProductsByName(); // Thực hiện lọc sản phẩm theo mã sản phẩm
                    break;
                case 10:
                    filterProductsByNSX(); // Thực hiện sắp xếp sản phẩm theo tên A-Z
                    break;
                case 0:
                    update();
                    return;
                default:
                    System.out.println("Sai cu phap");
                    break;
            }
        }
    }

    //Kiet: sap xep mat hang gia giam dan
    public void sortByPriceDescending() {
        // Sắp xếp mảng products và amount theo giá giảm dần
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (products[j].getPrice() < products[j + 1].getPrice()) {
                    // Hoán đổi sản phẩm
                    Phone tempProduct = products[j];
                    products[j] = products[j + 1];
                    products[j + 1] = tempProduct;

                    // Hoán đổi số lượng
                    int tempAmount = amount[j];
                    amount[j] = amount[j + 1];
                    amount[j + 1] = tempAmount;
                }
            }
        }
    }

    //Kiet: sap xep mat hang so luong giam dan
    public void sortByAmountDescending() {
        // Sắp xếp mảng amount theo số lượng giảm dần
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (amount[j] < amount[j + 1]) {
                    // Hoán đổi số lượng
                    int tempAmount = amount[j];
                    amount[j] = amount[j + 1];
                    amount[j + 1] = tempAmount;

                    // Hoán đổi sản phẩm
                    Phone tempProduct = products[j];
                    products[j] = products[j + 1];
                    products[j + 1] = tempProduct;
                }
            }
        }
    }

    public void filterProductsByNSX() {
        System.out.print("Nhap ten NSX: ");
        String nsxName = sc.nextLine();

        // Tạo một danh sách để lưu trữ các sản phẩm của nhà sản xuất có tên gần giống
        ImportStock filteredList = new ImportStock(0);

        for (int i = 0; i < len; i++) {
            String currentNSXName = products[i].getNSX().getName();

            // Nếu tên NSX chứa trong tên nhập vào
            if (currentNSXName.toLowerCase().contains(nsxName.toLowerCase())) {
                // Thêm sản phẩm và số lượng vào danh sách lọc
                filteredList.addListFind(products[i], amount[i]);
            }
        }

        // Xuất danh sách đã lọc
        System.out.println("Ket qua tim kiem mat hang cua NSX " + "'" + nsxName + "'" + ":");
        filteredList.output();
    }

    public void filterProductsByName() {
        System.out.print("Nhap ten san pham: ");
        String productName = sc.nextLine();

        // Tạo một danh sách để lưu trữ các sản phẩm có tên gần giống
        ImportStock filteredList = new ImportStock(0);

        for (int i = 0; i < len; i++) {
            String currentProductName = products[i].getName();

            // Nếu tên sản phẩm chứa trong tên nhập vào
            if (currentProductName.toLowerCase().contains(productName.toLowerCase())) {
                // Thêm sản phẩm và số lượng vào danh sách lọc
                filteredList.addListFind(products[i], amount[i]);
            }
        }

        // Xuất danh sách đã lọc
        System.out.println("Ket qua tim kiem mat hang co ten gan dung voi '" + "'" + productName + "'" + "':");
        filteredList.output();
    }

    public void filterProductsById() {
        System.out.print("Nhap ma san pham: ");
        String productCode = sc.nextLine();

        // Tạo một danh sách để lưu trữ các sản phẩm có mã gần giống
        ImportStock filteredList = new ImportStock(0);

        for (int i = 0; i < len; i++) {
            String currentProductCode = products[i].getPhoneID();

            // Nếu mã sản phẩm chứa trong mã nhập vào
            if (currentProductCode.toLowerCase().contains(productCode.toLowerCase())) {
                // Thêm sản phẩm và số lượng vào danh sách lọc
                filteredList.addListFind(products[i], amount[i]);
            }
        }

        // Xuất danh sách đã lọc
        System.out.println("Ket qua tim kiem '" + productCode + "':");
        filteredList.output();
    }

    public void sortProductsByNameAZ() {
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (products[j].getName().compareTo(products[j + 1].getName()) > 0) {
                    Phone tempPhone = products[j];
                    products[j] = products[j + 1];
                    products[j + 1] = tempPhone;
                    int tempAmount = amount[j];
                    amount[j] = amount[j + 1];
                    amount[j + 1] = tempAmount;
                }
            }
        }

        System.out.println("Danh sach mat hang sau khi sap xep theo ten A-Z:");
        output();
    }
}
