
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String a, String b){
        int startIndex=0,count=0;
        int currIndex;
        while(true){
            currIndex=b.indexOf(a,startIndex);
            if(currIndex==-1)
                break;
            count=count+1;
            startIndex=currIndex+a.length();
        }
        return count;
    }
    public void testHowMany(){
        String a="GAA";
        String b="ATGAACGAATTGAATC";
        System.out.println(howMany(a,b));
    }
}
