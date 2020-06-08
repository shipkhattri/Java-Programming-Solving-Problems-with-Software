
/**
 * Write a description of AllCodons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllCodons {

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
    public String findGene(String dna){
        int startIndex=dna.indexOf("ATG");
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
    public void testFindStopCodon(){
        String dna="ATGyyyzzTAAxTGAxxyyyzzzTAAxx";
        String gene=findGene(dna);
        System.out.println(gene);
    }
}
