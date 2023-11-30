import java.util.Scanner;

public class ProductCategory {
    private String description;
    private int SoDong;
    
    public ProductCategory() {
    }

    public ProductCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSoDong() {
        return SoDong;
    }

    public void setSoDong(int SoDong) {
        this.SoDong = SoDong;
    }

    public void input(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Thong so dien thoai co may dong? : ");
        setSoDong(Integer.parseInt(sc.nextLine()));
        description="";
        for(int i=1;i<=SoDong;i++){
            System.out.print("Nhap thong so thu "+i+" : ");
            description+=sc.nextLine()+"\n";
        }
    }
    @Override
    public String toString() {
        return SoDong+"\n"+description;
    }
    
    public String output(){
        return "Description:\n"+description;
    }
}
