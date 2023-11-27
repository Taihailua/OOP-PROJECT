
import java.security.spec.DSAGenParameterSpec;


public class ListEmployee implements FileIO{
    public Employee[] DSNV=new Employee[0];

    public ListEmployee() {
        readFromFile("data\\DSNV.txt");
    }

    @Override
    public void readFromFile(String fileName) {
        
    }

    @Override
    public void writeToFile(String fileName, String data) {
        
    }
    
    
}
