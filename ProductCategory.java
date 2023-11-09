
package DOAN;

import java.util.Scanner;

public class ProductCategory {
    private String description;

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
    
    public void input(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhap thong so dien thoai");
        setDescription(sc.nextLine());
    }

    @Override
    public String toString() {
        return ", description=" + description;
    }
}
