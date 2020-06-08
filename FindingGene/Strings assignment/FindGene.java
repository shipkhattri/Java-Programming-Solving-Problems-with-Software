
/**
 * Write a description of FindGene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FindGene {

    public String findSimpleGene(String dna)
    {
        //start codon = ATG
        //end codon = TAA
        String result="";
        int startIndex=dna.indexOf("ATG");
        if(startIndex==-1)
        {
            return "";
        }
        int endIndex=dna.indexOf("TAA",startIndex+3);
        if(endIndex==-1)
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
    	String dna="AAGCGTAATTGG";//No ATG
        System.out.println("DNA strand is "+dna);
        String gene=findSimpleGene(dna);
        System.out.println("Gene is "+gene);

        dna="AATGCGTAGTATGG";//No TAA
        System.out.println("DNA strand is "+dna);
        gene=findSimpleGene(dna);
        System.out.println("Gene is "+gene);

        dna="ACAGTG";//NO ATG and TAA
        System.out.println("DNA strand is "+dna);
        gene=findSimpleGene(dna);
        System.out.println("Gene is "+gene);

        dna="AATGCGTAGTTAATGG";//ATG and TAA with substring length multiple of 3
        System.out.println("DNA strand is "+dna);
        gene=findSimpleGene(dna);
        System.out.println("Gene is "+gene);

        dna="AATGCGTGTTAATGG";//ATG and TAA with substring length not multiple of 3
        System.out.println("DNA strand is "+dna);
        gene=findSimpleGene(dna);
        System.out.println("Gene is "+gene);
    }
}
