
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        if(index != -1) {
            int index2 = stringb.indexOf(stringa, index+1);
            if( index2 != -1) {
                return true;
            }
        }
        return false;
    }
    
     public String lastPart(String stringa, String stringb) {
        if(stringb.indexOf(stringa) != -1) {
            int index = stringb.indexOf(stringa);
            String b=stringb.substring(index+stringa.length(), stringb.length());
            return b;
        } else {
            return stringb;
        }
    }
    
    public void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
        System.out.println(lastPart("zoo", "farzookeeper"));
    }
    
    public static void main() {
        Part3 howMany = new Part3();
        howMany.testing();
    }
}