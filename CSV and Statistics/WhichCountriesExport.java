
/**
 * Write a description of WhichCountriesExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest){
        
        for(CSVRecord record : parser){
            String export=record.get("Exports");
            if(export.contains(exportOfInterest)){
                String country=record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void whoExportsCoffee(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser,"coffee");
    }
    public String countryInfo(CSVParser parser, String country){
        
        for(CSVRecord record: parser){
            if(record.get("Country").equals(country)){
                String info=record.get("Country")+": "+ record.get("Exports")+": "+record.get("Value (dollars)");
                return info;
            }
        }
        return "NOT FOUND";
      
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        
        for(CSVRecord record:parser){
            String exports=record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count=0;
        for(CSVRecord record : parser){
            String export=record.get("Exports");
            if(export.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser,String amount){
        for(CSVRecord record : parser){
            String value=record.get("Value (dollars)");
            if((amount.length()) < (value.length())){
                System.out.println(record.get("Country")+" "+value);
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser,"Nauru"));
        //parser = fr.getCSVParser();
        //listExportersTwoProducts(parser,"cotton","flowers");
        //parser = fr.getCSVParser();
        //System.out.println(numberOfExporters(parser,"cocoa"));
        //parser = fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
    }
}
