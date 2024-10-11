import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ViewList{
    public void viewList() throws IOException{
        SukiList sukiList = new SukiList();
        
        sukiList.sukiList();
        sukiList.getSukiName();
        
        BufferedReader br = new BufferedReader (new FileReader("UtangList.txt"));
        
        String keyword = "---";
        String keyword2 = sukiList.getSukiName();
        String line;
        int lineNumber = 0;
        boolean isPrinting = false;
        ArrayList<String> debtList = new ArrayList<>();
        
        while((line = br.readLine()) != null){            
            if(line.contains(keyword + keyword2)){
                isPrinting = !isPrinting;
                continue;
            }
            if(isPrinting){
                
                System.out.println(line);
            }
            lineNumber++;
        }
    }
}