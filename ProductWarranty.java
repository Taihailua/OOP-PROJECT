import java.util.Date;

public class ProductWarranty extends Warranty {
    private String productSerialNumber;
    private String productModel;

    public ProductWarranty(Phone product, Date startDate, Date endDate, String description, String productSerialNumber, String productModel) {
        super(product, startDate, endDate, description);
        this.productSerialNumber = productSerialNumber;
        this.productModel = productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    @Override
    public int calculateRemainingWarranty() {
        return 0;
    }

    @Override
    public boolean isWarrantyValid() {

        return false;
    }

    @Override
    public void printWarrantyDetails() {

    }
}