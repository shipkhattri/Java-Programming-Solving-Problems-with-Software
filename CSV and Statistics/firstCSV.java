
/**
 * Write a description of firstCSV here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class firstCSV {
    void read(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            System.out.print(record.get("Name")+" ");
            System.out.print(record.get("Color")+" ");
            System.out.println(record.get("Food"));
        }
    }
}
