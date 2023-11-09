
package DOAN;

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
    
    @Override
    public void readFromFile(String fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine()!=null) {
                len++;
                products=Arrays.copyOf(products,len);
                ProductCategory Category=new ProductCategory();
                Manufacturer NSX=new Manufacturer();
                products[len-1]=new Phone(br.readLine(),br.readLine(),Double.parseDouble(br.readLine()),Category,NSX);
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

    public ImportStock() {
        readFromFile("textDocument\\data.txt");
    }
    
    public void add(){
        System.out.print("Nhap ma dien thoai can them: ");
        String ID=sc.nextLine();
        System.out.print("Nhap so luong dien thoai: ");
        int SoLuong=Integer.parseInt(sc.nextLine());
        for(int i=0;i<len;i++){
            if(products[i].getPhoneID().equals(ID)){
                 amount[i]+=SoLuong;
                 return;
            }
        }
        len++;
        products=Arrays.copyOf(products,len);
        products[len-1]=new Phone();
        products[len-1].input();
        amount=Arrays.copyOf(amount,len);
        amount[len-1]=SoLuong;
    }
    
    public void edit(){
        System.out.print("Nhap ma dien thoai can sua: ");
        String ID=sc.nextLine();
        int pos;
        for(pos=0;pos<len;pos++){
            if(products[pos].getPhoneID().equals(ID))
                break;
        }
        
        if(pos>=len){
            System.out.println("Khong tim thay");
            return;
        }
        
        System.out.println("Moi ban edit:");
        products[pos].input();
    }
    
    public void remove(){
        System.out.print("Nhap ma dien thoai can xoa: ");
        String ID=sc.nextLine();
        int pos;
        for(pos=0;pos<len;pos++){
            if(products[pos].getPhoneID().equals(ID))
                break;
        }
        
        if(pos>=len){
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

    
    
}
