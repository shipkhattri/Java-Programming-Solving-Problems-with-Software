
/**
 * Write a description of FindMultipleGene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class FindMultipleGene {
    
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
    public void printAllGenes(String dna){
        int startIndex=0;
        while(true)
        {
            String currGene=findGene(dna,startIndex);
            if(currGene.isEmpty())
                break;
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene,startIndex) + 
                currGene.length();
        }
    }
    public void testOn(String dna){
        System.out.println("Testing getAllGenes on "+dna);
        StorageResource genes=getAllGenes(dna);
        for(String g:genes.data()){
            System.out.println(g);
        }
    }
    public void test()
    {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    }
    public StorageResource getAllGenes(String dna){
        StorageResource geneList=new StorageResource();
        int startIndex=0;
        while(true){
            String currGene=findGene(dna,startIndex);
            if(currGene.isEmpty())
                break;
            geneList.add(currGene);
            startIndex=dna.indexOf(currGene,startIndex)+
                        currGene.length();
        }
        return geneList;
    }
}
