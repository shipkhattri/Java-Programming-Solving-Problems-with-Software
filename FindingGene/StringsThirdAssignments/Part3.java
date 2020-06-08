
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part3 {
    public float cgRatio(String dna){
        dna=dna.toUpperCase();
        float Ratio=0,count=0;
        int index=dna.indexOf("C");
        while(true){
            if(index==-1)
                break;
            if(index==dna.length()-1){
                count=count+1;
                break;
            }
            count=count+1;
            index=dna.indexOf("C",index+1);
        }
        index=dna.indexOf("G");
        while(true){
            if(index==-1)
                break;
            if(index==dna.length()-1){
                count=count+1;
                break;
            }
            count=count+1;
            index=dna.indexOf("G",index+1);
        }
        Ratio=count/dna.length();
        return Ratio;
    }
    public void processGenes(StorageResource sr){
        System.out.println("Printing all strings that are longer than 60 characters:");
        int count=0;
        for(String s: sr.data()){
            if(s.length()>60){
                count++;
                System.out.println(s);
            }
        }
        
        System.out.println("Printing no of strings that are longer than 60 characters: "+count);
        
        System.out.println("Printing the strings with CGRatio higher than 0.35");
        count=0;
        for(String s: sr.data()){
            if(cgRatio(s)>0.35){
                count++;
                System.out.println(s);
            }
        }
        
        System.out.println("Printing no of strings whose cgratio higher than 0.35: "+count);        
        
        int longest=0;
        for(String s: sr.data()){
            if(longest<s.length()){
                longest=s.length();
            }
        }
        System.out.println("Length of longest gene: "+longest);
    }
    public int findStopCodon(String dnaStr,
                            int startIndex, 
                            String stopCodon){
    
        int currIndex=dnaStr.indexOf(stopCodon,startIndex+3);
        while(currIndex!=-1){
            int diff=currIndex-startIndex;
            if(diff%3==0){
                return currIndex;
            }
            else{
                currIndex=dnaStr.indexOf(stopCodon,currIndex+1);
            }
        }
        return -1;
    }
    public String findGene(String dna, int where){
        int startIndex=dna.indexOf("ATG",where);
        if(startIndex==-1){
            return "";
        }
        
        int taaIndex=findStopCodon(dna,startIndex,"TAA");
        int tagIndex=findStopCodon(dna,startIndex,"TAG");
        int tgaIndex=findStopCodon(dna,startIndex,"TGA");
        int minIndex=0;
        if(taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex<taaIndex )){
                minIndex = tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }
        if(minIndex == -1 ||
            (tagIndex != -1 && tagIndex<minIndex)){
                minIndex=tagIndex;
        }
        if(minIndex==-1)
            return "";
            
        return dna.substring(startIndex,minIndex+3);
    }
    public StorageResource getAllGenes(String dna){
        StorageResource geneList=new StorageResource();
        dna=dna.toUpperCase();
        int startIndex=0;
        while(true){
            String currGene=findGene(dna,startIndex);
            if(currGene.isEmpty())
                break;
            System.out.println(currGene);
            geneList.add(currGene);
            startIndex=dna.indexOf(currGene,startIndex)+currGene.length();
        }
        return geneList;
    }
    public void testProcessGenes(){
        FileResource fr=new FileResource();
        String s = fr.asString();
        StorageResource sr=getAllGenes(s);
        System.out.println(sr.size());
        
        processGenes(sr);
    }
}
