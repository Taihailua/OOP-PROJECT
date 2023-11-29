import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ImportStock implements FileIO{
    private Phone[] products=new Phone[0];
    private int[] amount=new int[0];
    private int len=0;
//    private Employee receiver;
    static Scanner sc=new Scanner(System.in);
    
    
    public ImportStock() {
        readFromFile("data\\sanpham.txt");
    }
    
    public ImportStock(int n) {
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
                Category.setSoDong(Integer.parseInt(br.readLine()));
                String description="";
                int n=Category.getSoDong();
                while(n>0){
                    description+=br.readLine()+"\n";
                    n--;
                }
                Category.setDescription(description);
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
    public void writeToFile(String fileName,String data){
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        int i=1;
        String temp="";
        for(Phone x:products){
            temp+="DT"+i+"\n";
            temp+=x.getName()+"\n";
            temp+=x.getPhoneID()+"\n";
            temp+=x.getPrice()+"\n";
            temp+=x.getCategory();
            temp+=x.getNSX()+"\n";
            temp+=amount[i-1]+"\n";
            i++;
        }
        writeToFile("data\\sanpham.txt", temp);
    }
    
    public int findID(String phoneID){
        for(int i=0;i<len;i++){
            if(products[i].getPhoneID().equals(phoneID))
                return i;
        }
        return -1;
    }
    
    public void findName(){
        System.out.print("Nhap ten san pham: ");
        String name=sc.nextLine();
        ImportStock list=new ImportStock(0);//constructor dung de tao doi tuong empty ImportStock, doi so n khong co tac dung
        for(int i=0;i<len;i++){
            if(products[i].getName().toLowerCase().contains(name.toLowerCase())){
                list.addListFind(products[i],amount[i]);
            }
        }
        list.output();
    }
    
    public void add(){
        System.out.print("Nhap ma dien thoai can them: ");
        String ID=sc.nextLine();
        
        int pos=findID(ID);
        if(pos==-1){
            len++;
            products=Arrays.copyOf(products,len);
            products[len-1]=new Phone();
            products[len-1].input(ID);
            System.out.print("Nhap so luong dien thoai: ");
            int SoLuong=Integer.parseInt(sc.nextLine());
            amount=Arrays.copyOf(amount,len);
            amount[len-1]=SoLuong;
        }
        else {
            System.out.print("Nhap so luong dien thoai: ");
            amount[len-1]+=Integer.parseInt(sc.nextLine());
        }
        update();
    }
    
    public void addListFind(Phone phone,int amount){
        int pos=findID(phone.getPhoneID());
        if(pos==-1){
            len++;
            products=Arrays.copyOf(products,len);
            products[len-1]=new Phone();
            products[len-1]=phone;
            this.amount=Arrays.copyOf(this.amount,len);
            this.amount[len-1]=amount;
        }
        else {
            this.amount[len-1]+=Integer.parseInt(sc.nextLine());
        }
    }
    
    public void add(String ID,int amount){
        int pos=findID(ID);
        if(pos==-1){
            len++;
            products=Arrays.copyOf(products,len);
            products[len-1]=new Phone();
            products[len-1].input(ID);
            this.amount=Arrays.copyOf(this.amount,len);
            this.amount[len-1]=amount;
        }
        else {
            this.amount[len-1]+=amount;
        }
        update();
    }
    
    public void add(Phone phone,int amount){
        int pos=findID(phone.getPhoneID());
        if(pos==-1){
            len++;
            products=Arrays.copyOf(products,len);
            products[len-1]=new Phone();
            products[len-1]=phone;
            this.amount=Arrays.copyOf(this.amount,len);
            this.amount[len-1]=amount;
        }
        else {
            this.amount[len-1]+=Integer.parseInt(sc.nextLine());
        }
        update();
    }
    
    public void edit(){
        System.out.print("Nhap ma dien thoai can sua: ");
        String ID=sc.nextLine();
        int pos=findID(ID);
        if(pos==-1){
            System.out.println("Khong tim thay dien thoai!");
            return;
        }
        System.out.println("1. Edit name\n2. Edit price");
        System.out.println("3. Edit Category\n4. Edit NSX\n0. Exit");
        while(true){
            System.out.print("Moi chon chuc nang: ");
            int n=Integer.parseInt(sc.nextLine());
            switch(n){
                case 1: System.out.print("New name: ");
                    products[pos].setName(sc.nextLine());break;
                case 2: System.out.print("New price: ");
                    products[pos].setPrice(Integer.parseInt(sc.nextLine()));break;
                case 3: ProductCategory newCategory=new ProductCategory();
                    newCategory.input();
                    products[pos].setCategory(newCategory);break;
                case 4: Manufacturer newNSX=new Manufacturer();
                    newNSX.newedit();
                    products[pos].setNSX(newNSX);break;
                case 0: update(); return;
                default: System.out.println("Sai cu phap");break;
            }
        }
        
    }
    
    public void remove(String ID){
        int pos=findID(ID);
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
        System.out.println("remove completed");
        update();
    }
    public void remove(){
        System.out.print("Nhap ID san pham muon xoa: ");
        int pos=findID(sc.nextLine());
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
        System.out.println("remove completed");
        update();
    }
    
    public void output(){
        System.out.println("-----------------Danh Sach Dien thoai-------------------");
        System.out.println(String.format("|%-10s|%-20s|%-15s|%-6s|", "ID","NAME","PRICE(VND)","AMOUNT"));
        for(int i=0;i<len;i++){
            products[i].output();
            System.out.println(String.format("|%-6d|",amount[i]));
        }
        System.out.println("--------------------------------------------------------");
    }

    public void sell(String phoneID,int amount){
        int pos=findID(phoneID);
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
        update();
    }
    public void showMenu(){
        while(true){
            System.out.println("1. Hien thi danh sach dien thoai.\n2. Them san pham\n3. Sua san pham\n4. Xoa san pham\n0. Exit");
            System.out.print("Moi chon chuc nang: ");
            int n=Integer.parseInt(sc.nextLine());
            switch(n){
                case 1: output();break;
                case 2: add();break;
                case 3: edit();break;
                case 4: remove();break;
                case 0: update(); return;
                default: System.out.println("Sai cu phap");break;
            }
        }
    }
}
