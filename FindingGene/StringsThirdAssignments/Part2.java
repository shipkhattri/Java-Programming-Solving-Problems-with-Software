
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
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
    public int countCTG(String dna){
        int count=0;
        int index=dna.indexOf("CTG");
        while(true)
        {
            if(index==-1)
            break;
            if(index>=dna.length()-3){
                count=count+1;
                break;
            }
            count=count+1;
            index=dna.indexOf("CTG",index+3);
        }
        return count;
    }
    public void testCGRatio(){
        System.out.println(cgRatio("ATGCCATAG"));
    }
}

