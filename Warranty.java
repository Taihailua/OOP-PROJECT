import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Warranty {
    private Phone product;
    private Date startDate;
    private Date endDate;
    private String description;
    static Scanner sc = new Scanner(System.in);
    public Warranty(Phone product, Date startDate, Date endDate, String description) {
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public Phone getProduct() {
        return product;
    }

    public void setProduct(Phone product) {
        this.product = product;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void input() {
      getProduct();
      getEndDate();
      getStartDate();
      System.out.println("Hay nhap phan mo ta");
      setDescription(sc.nextLine());
    }

    @Override
    
    public abstract int calculateRemainingWarranty();

    public abstract boolean isWarrantyValid();

    public abstract void printWarrantyDetails();


}
