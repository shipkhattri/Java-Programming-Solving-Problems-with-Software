
/**
 * Write a description of FindGene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2{

    public String findSimpleGene(String dna, int startIndex,int endIndex)
    {
        //start codon = ATG
        //end codon = TAA
        String result="";
        if(startIndex==-1 || endIndex==-1)
        {
            return "";
        }
        result=dna.substring(startIndex,endIndex+3);
        int len=result.length();
        if(len%3==0)
        {
            return result;
        }
        return "";
    }
    public void testSimpleGene()
    {

        String dna="AATGCGTAGTTAATGG";
        dna.toUpperCase();//ATG and TAA with substring length multiple of 3
        System.out.println("DNA strand is "+dna);
        int startIndex=dna.indexOf("ATG");
        int endIndex=dna.indexOf("TAA");
        String gene=findSimpleGene(dna,startIndex,endIndex);
        System.out.println("Gene is "+gene);

        dna="AATGCGTGTTaATGG";//ATG and TAA with substring length not multiple of 3
        dna.toUpperCase();
        System.out.println("DNA strand is "+dna);
        startIndex=dna.indexOf("ATG");
        endIndex=dna.indexOf("TAA");
        gene=findSimpleGene(dna,startIndex,endIndex);
        System.out.println("Gene is "+gene);
    }
}
