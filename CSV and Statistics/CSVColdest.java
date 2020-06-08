
/**
 * Write a description of CSVColdest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVColdest {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar=null;
        for(CSVRecord currRow:parser){
            smallestSoFar = getsmallestOfTwo(currRow,smallestSoFar);
        }
        return smallestSoFar;
    }
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("The coldest temperature on that day was "+ smallest.get("TemperatureF"));
    }
    public String fileWithColdestTemperature() {
        CSVRecord smallestSoFar=null;
        String smallestFileName=null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr=new FileResource(f);   
            CSVRecord currRow=coldestHourInFile(fr.getCSVParser());
            if(smallestSoFar==null)
                smallestSoFar=currRow;
            else{
                double currTemp=Double.parseDouble(currRow.get("TemperatureF"));
                double smallestTemp=Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if(currTemp != -9999 && currTemp<smallestTemp){
                    smallestSoFar=currRow;
                    smallestFileName=f.getName();
                }
            }
        }
        return smallestFileName; 
    }
    public void testFileWithColdestTemperature() {
        String coldestName=fileWithColdestTemperature();
        System.out.println("Coldest day was in file: "+coldestName);
        
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("The coldest temperature on that day was : "+
                    coldest.get("TemperatureF"));
        //System.out.println("All the temperatures on the coldest day were : ");
        //for(CSVRecord record:fr.getCSVParser()){
        //    System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
        //}
    }
    public CSVRecord getsmallestOfTwo(CSVRecord currRow, CSVRecord smallestSoFar){
        if(smallestSoFar==null)
            smallestSoFar=currRow;
        else{
            double currTemp=Double.parseDouble(currRow.get("TemperatureF"));
            double smallestTemp=Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if(currTemp != -9999 && currTemp<smallestTemp)
                smallestSoFar=currRow;
            }
        return smallestSoFar; 
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar=null;
        double currTemp=0;
        double lowestTemp=0;
        for(CSVRecord currRow:parser){
            if(lowestSoFar==null)
                lowestSoFar=currRow;
        else{
            if(currRow.get("Humidity").equals("N/A")){
                currTemp=-999;}
            else{
                currTemp=Double.parseDouble(currRow.get("Humidity"));}
            if(lowestSoFar.get("Humidity").equals("N/A")){
                lowestTemp=-999;} 
            else{
                lowestTemp=Double.parseDouble(lowestSoFar.get("Humidity"));}
            if(currTemp<lowestTemp && currTemp != -999)
                    lowestSoFar=currRow;
            }
        }
        return lowestSoFar;
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord csv=lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+
                    " at "+csv.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currRow = lowestHumidityInFile(fr.getCSVParser());
            if(lowestSoFar == null)
                lowestSoFar = currRow;
            else{
                double currTemp = Double.parseDouble(currRow.get("Humidity"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("Humidity"));
                if(currTemp != -9999 && currTemp<lowestTemp){
                    lowestSoFar = currRow;
                }
            }
        }
        return lowestSoFar;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord coldest=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+coldest.get("Humidity")
                    +" at "+coldest.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser parser){
        double avgTemp=0.0;
        int count=0;
        for(CSVRecord record:parser){
            avgTemp+=Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        avgTemp=avgTemp/count;
        return avgTemp;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double avgTemp=averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average Temperature in file is "+ avgTemp);
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        double avgTemp=0.0;
        int count=0;
        for(CSVRecord record:parser){
            double currHumid=Double.parseDouble(record.get("Humidity"));
            if(currHumid>=value){
            avgTemp+=Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
       }
       if(avgTemp==0.0)
        return 0.0;
       avgTemp=avgTemp/count;
       return avgTemp;
    }
    public void testAverageTemperatureWithHumidityInFile(){
        FileResource fr = new FileResource();
        double avgTemp=averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if(avgTemp==0.0)
            System.out.println("No temperatures with that humidity");
        else    
            System.out.println("Average Temperature when high humidity is "+ avgTemp);            
    }
}
