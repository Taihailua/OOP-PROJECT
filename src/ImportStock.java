import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportStock implements FileIO{
    private Phone[] products=new Phone[0];
    private int[] amount=new int[0];
    private int len=0;
//    private Salesperson receiver;
    
    static Scanner sc=new Scanner(System.in);
    
    
    public ImportStock() {
        readFromFile("data\\data.txt");
    }
    
    @Override
    public void readFromFile(String fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine()!=null) {
                len++;
                products=Arrays.copyOf(products,len);
                ProductCategory Category=new ProductCategory();
                Manufacturer NSX=new Manufacturer();
                products[len-1]=new Phone(br.readLine(),br.readLine(),Integer.parseInt(br.readLine()),Category,NSX);
                Category.setDescription(br.readLine());
                NSX.setName(br.readLine());
                NSX.setAddress(br.readLine());
                NSX.setPhoneNumber(br.readLine());
                amount=Arrays.copyOf(amount,len);
                amount[len-1]=Integer.parseInt(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void writeToFile(String fileName){
        
    }

    public int find(String phoneID){
        for(int i=0;i<len;i++){
            if(products[i].getPhoneID().equals(phoneID))
                return i;
        }
        return -1;
    }
    
    public void add(){
        System.out.print("Nhap ma dien thoai can them: ");
        String ID=sc.nextLine();
        System.out.print("Nhap so luong dien thoai: ");
        int SoLuong=Integer.parseInt(sc.nextLine());
        int pos=find(ID);
        if(pos==-1){
            len++;
            products=Arrays.copyOf(products,len);
            products[len-1]=new Phone();
            products[len-1].input();
            amount=Arrays.copyOf(amount,len);
            amount[len-1]=SoLuong;
        }
        else {
            amount[pos]+=SoLuong;
        }
    }
    
    public void edit(){
        System.out.print("Nhap ma dien thoai can sua: ");
        String ID=sc.nextLine();
        int pos=find(ID);
        if(pos==-1){
            System.out.println("Khong tim thay dien thoai!");
            return;
        }
        System.out.println("1. Edit phoneID\n2. Edit name\n3. Edit price");
        System.out.println("4. Edit Category\n5. Edit NSX\n0. Exit");
        while(true){
            System.out.print("Moi chon chuc nang: ");
            int n=Integer.parseInt(sc.nextLine());
            switch(n){
                case 1: System.out.print("Nhap phoneID: ");
                    products[pos].setPhoneID(sc.nextLine());break;
                case 2: System.out.print("Nhap name: ");
                    products[pos].setName(sc.nextLine());break;
                case 3: System.out.print("Nhap price: ");
                    products[pos].setPrice(Integer.parseInt(sc.nextLine()));break;
                case 4: ProductCategory newCategory=new ProductCategory();
                    newCategory.input();
                    products[pos].setCategory(newCategory);break;
                case 5: Manufacturer newNSX=new Manufacturer();
                    newNSX.input();
                    products[pos].setNSX(newNSX);break;
                case 0: return;
                default: System.out.println("Sai cu phap");break;
            }
        }
    }
    
    public void remove(){
        System.out.print("Nhap ma dien thoai can xoa: ");
        String ID=sc.nextLine();
        int pos=find(ID);
        
        if(pos==-1){
            System.out.println("Khong tim thay");
            return;
        }
        
        for(int i=pos+1;i<len;i++){
            products[i-1]=products[i];
        }
        len--;
        products=Arrays.copyOf(products,len);
        
        
        for(int i=pos+1;i<len+1;i++){
            amount[i-1]=amount[i];
        }
        amount=Arrays.copyOf(amount,len);
    }
    
    public void output(){
        System.out.println("Danh Sach Dien thoai: ");
        for(int i=0;i<len;i++){
            products[i].output();
            System.out.println("So luong: "+amount[i]);
        }
    }

    public void sell(String phoneID,int amount){
        int pos=find(phoneID);
        if(pos==-1){
            System.out.println("Dien thoai nay khong ton tai.");
            return;
        }
        if(this.amount[pos]<amount){
            System.out.println("Dien thoai "+products[pos].getName()
                    +" chi con "+this.amount[pos]+" san pham.");
            return;
        }
        this.amount[pos]-=amount;
    }
}
