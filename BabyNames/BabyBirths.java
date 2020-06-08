
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn<=100){
            System.out.println("Names "+ rec.get(0)+
                                " Gender "+rec.get(1)+
                                " Num Born "+rec.get(2));
                            }
                        }
    }
    
    public void totalBirths(FileResource fr){
        int totalBirth=0,totalBoys=0,totalGirls=0;
        int totalNames=0,GirlsNames=0,BoysNames=0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirth += numBorn;
            totalNames += 1;
            if(rec.get(1).equals("M")){
                totalBoys += numBorn;
                BoysNames += 1;
            }
            else{
                totalGirls += numBorn;
                GirlsNames += 1;
            }
        }
        System.out.println("Total number of births: "+totalBirth);
        System.out.println("Total number of girls: "+totalGirls);
        System.out.println("Total number of boys: "+totalBoys);
        System.out.println("Total names in file: "+totalNames);
        System.out.println("Number of girls names: "+GirlsNames);
        System.out.println("Number of boys names: "+BoysNames);
        
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getMaleRanking(String fname){
        int rank=0;
        FileResource fr = new FileResource(fname);
        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals("F")){
                rank++;
            if(rec.get(1).equals("M"))
                break;
            }
        }
        return rank;
    }
    
    public CSVRecord getMaleParser(String fname){
        CSVRecord maleParser=null;
        FileResource fr = new FileResource(fname);
        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(1).equals("M")){
                maleParser=rec;
                break;
            }
        }
        return maleParser;
    }
    
    public int getRank(int year, String name, String gender){
        int rankG=1,rankB=1;
        String fname = "data/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        for(CSVRecord rec: fr.getCSVParser(false)){
            if(rec.get(0).equals(name) && rec.get(1).equals(gender))
            {       
                if(gender.equals("F"))
                    return rankG;
                if(gender.equals("M"))
                    return rankB;
            }
            else{
                if(rec.get(1).equals("F"))
                    rankG++;
                if(rec.get(1).equals("M"))
                    rankB++;
            }
        }
        return -1;
    }
    
    public String getName(int year,int rank,String gender){
        String fname = "data/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        int maleRanking=getMaleRanking(fname);
        if(gender.equals("M"))
            rank+=maleRanking;
        for(CSVRecord rec: fr.getCSVParser(false)){   
            if(gender.equals("F") && rank==1){
                return rec.get(0);
            }
            if(gender.equals("M") && rank==1)
                return rec.get(0);
            rank--;
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name,int year,int newYear,String gender){
        int rank = getRank(year,name,gender);
        String newName = getName(newYear,rank,gender); 
        if(gender.equals("F"))
            System.out.println(name+" born in "+year+" would be "+
                        newName+" if she was born in "+newYear);
        if(gender.equals("M"))
            System.out.println(name+" born in "+year+" would be "+
                        newName+" if he was born in "+newYear);
    }
    
    public int yearOfHighestRank(String name,String gender){
        int highestYear=-1;
        int highestRank=-1;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int currYear=Integer.parseInt(f.getName().substring(3,7));
            int currRank=getRank(currYear,name,gender);
            if(currRank==-1)
                continue;
            if(highestRank==-1 || currRank<highestRank){
                highestRank=currRank;
                highestYear=currYear;
            }
        }
        return highestYear;
    }
    
    public double getAverageRank(String name,String gender){
        double avg=0.0;
        int count=0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            int currYear=Integer.parseInt(f.getName().substring(3,7));
            int currRank=getRank(currYear,name,gender);
            if(currRank != -1){
                avg += currRank;
            }
            count++;
        }
        avg=avg/count;
        return avg;
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        int totalBirths=0;
        int rank=getRank(year,name,gender);
        String fname = "data/yob"+year+".csv";
        FileResource fr = new FileResource(fname);
        for(CSVRecord rec: fr.getCSVParser(false)){
            if(! rec.get(1).equals(gender)){
                continue;
            }
            if(name.equals(rec.get(0)))
                break;
            else{
                int numBorn=Integer.parseInt(rec.get(2));
                totalBirths += numBorn;
            }
        }
        return totalBirths;
    }
    public void testing(){
        System.out.println(getRank(1971,"Frank","M"));
        System.out.println(getName(1982,450,"M"));
        whatIsNameInYear("Owen",1974,2014,"M");
        System.out.println(yearOfHighestRank("Mich","M"));
        System.out.println(getAverageRank("Robert","M"));
        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
}
