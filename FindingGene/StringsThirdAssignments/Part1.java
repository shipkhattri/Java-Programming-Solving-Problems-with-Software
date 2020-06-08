
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
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
        return dnaStr.length();
    }
    public void testFindStopCodon(){
    String dna="ATGCGGTGGTATTGACCGAT";
    int startIndex=0;
    int taaIndex=findStopCodon(dna,startIndex,"TAA");
    int tagIndex=findStopCodon(dna,startIndex,"TAG");
    int tgaIndex=findStopCodon(dna,startIndex,"TGA");
    System.out.println(taaIndex+tagIndex+tgaIndex);
    }
    public String findGene(String dna, int startIndex){
        startIndex= dna.indexOf("ATG", startIndex+3);
        if(startIndex==-1){
            return "";
        }
        
        int taaIndex=findStopCodon(dna,startIndex,"TAA");
        int tagIndex=findStopCodon(dna,startIndex,"TAG");
        int tgaIndex=findStopCodon(dna,startIndex,"TGA");
        int temp= Math.min(taaIndex,tagIndex);
        int minIndex= Math.min(temp,tgaIndex);
        
        if(minIndex==dna.length())
            return "";
            
        return dna.substring(startIndex,minIndex+3);
    }
    public void testFindGene(){
        String dna="AAGCGTAATTGG";//No ATG
        System.out.println("DNA strand is "+dna);
        printAllGenes(dna);

        dna="AATGCGTAGTTAGG";//ATG and valid stop codon
        System.out.println("DNA strand is "+dna);
        printAllGenes(dna);

        dna="ACATGTGCTAATAGCGG";//ATG and multiple valid stop codon
        System.out.println("DNA strand is "+dna);
        printAllGenes(dna);

        dna="AATGCGTAGTAATGG";//ATG and no valid stopcodons
        System.out.println("DNA strand is "+dna);
        printAllGenes(dna);
        
        dna="AATGCGTGTTAATGG";//ATG and TAA with substring length not multiple of 3
        System.out.println("DNA strand is "+dna);
        printAllGenes(dna);
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
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}
