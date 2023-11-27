public class ListCustomer implements FileIO{
    Customer[] DSKH=new Customer[0];

    public ListCustomer() {
        readFromFile("data\\DSKH.txt");
    }

    @Override
    public void readFromFile(String fileName) {
        
    }

    @Override
    public void writeToFile(String fileName, String data) {
        
    }
    
    
}
