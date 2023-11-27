
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Currency;

public class ListCustomer implements FileIO{
    Customer[] DSKH=new Customer[0];

    public ListCustomer() {
        readFromFile("data\\DSKH.txt");
    }
    

    @Override
    public void readFromFile(String fileName) {
         try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s=br.readLine();
            String str[];
            while (s!=null){
                Customer KH=new Customer();
                str=s.split(";");
                KH.setIdCustomer(str[0]);
                KH.setName(str[1]);
                KH.setPhone(str[2]);
                KH.setAddress(str[3]);
                KH.setEmail(str[4]);
                DSKH=Arrays.copyOf(DSKH,DSKH.length+1);
                DSKH[DSKH.length-1]=KH;
                s=br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile(String fileName, String data) {
        
    }

    public void output(){
        for(var x:DSKH){
            System.out.println(x.toString());
        }
    }
    
    public static void main(String[] args) {
        ListCustomer DSKH=new ListCustomer();
        DSKH.output();
    }
}
