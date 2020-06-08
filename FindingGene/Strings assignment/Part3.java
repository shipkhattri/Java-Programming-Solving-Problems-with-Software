
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.String;
public class Part3 {
    public boolean twoOccurences(String a, String b)
    {
        int pos=b.indexOf(a);
        int pos2=b.indexOf(a,pos+1);
        if(pos==-1 || pos2==-1)
            return false;
    return true;   
    }
    public String lastPart(String a ,String b)
    {
        int pos=b.indexOf(a);
        if(pos==-1)
            return b;
        return b.substring(pos+a.length());
    }
    public void Testing()
    {
        String a="zoo", b="banana";
        System.out.println("String a: "+a+" String b: "+ b);
        System.out.println(twoOccurences(a,b));
        System.out.println(lastPart(a,b));
    }
}
