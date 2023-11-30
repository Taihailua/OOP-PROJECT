import java.util.Date;

public class Sale {
    private String phoneID;
    private String description;
    private Date startDate;
    private Date endDate;
    private int priceSale;

    public Sale() {
    }

    public Sale(String phoneID, String description, Date startDate, Date endDate,int priceSale) {
        this.phoneID = phoneID;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceSale=priceSale;
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

    public int getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }

    public void applySale() {
        // áp dụng khuyến mãi cho sản phẩm
        System.out.println("Đã áp dụng khuyến mãi cho sản phẩm có ID: " + phoneID);
        System.out.println("Mô tả: " + description);
        System.out.println("Thời gian bắt đầu: " + startDate);
        System.out.println("Thời gian kết thúc: " + endDate);
    }

    public void removeSale() {
        // xoá khuyến mãi 
        System.out.println("Đã gỡ bỏ khuyến mãi cho sản phẩm có ID: " + phoneID);
    }
}
