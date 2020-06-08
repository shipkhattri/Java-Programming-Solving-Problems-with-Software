
/**
 * Write a description of findAllgene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class findAllgene {
    public String findAllgene(String dna){
        int startIndex=dna.indexOf("ATG");
        int currIndex=dna.indexOf("TAA",startIndex+3);
        while(currIndex!=-1)
        {
            if((currIndex-startIndex)%3==0)
            {
                return dna.substring(startIndex,currIndex+3);
            }
            else{
                currIndex=dna.indexOf("TAA",currIndex+1);
            }
        }
        return "";
    }
    public void testFindAllgene(){
        String dna="AATGCGTAATTAATCG";
        System.out.println("DNA strand is: "+dna);
        String gene=findAllgene(dna);
        System.out.println("Gene is: "+gene);
    }
}
