import java.util.Date;

public class ExtendedWarranty extends Warranty {
    private int extendedPeriod;

    public ExtendedWarranty(Phone product, Date startDate, Date endDate, String description, int extendedPeriod) {
        super(product, startDate, endDate, description);
        this.extendedPeriod = extendedPeriod;
    }

    public void setExtendedPeriod(int period) {
        this.extendedPeriod = period;
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