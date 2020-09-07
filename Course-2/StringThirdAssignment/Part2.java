
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part2{
public float cgRatio(String dna) {
        int countC = 0;
        int countG = 0;
        int cIndex = dna.indexOf("C");
        int gIndex = dna.indexOf("G");
        int length=dna.length();
        while(true) {
            if(cIndex != -1) {
                countC++;
            } else {
                break;
            }
            if(gIndex != -1) {
                countG++;
            } else {
                break;
            }
            cIndex = dna.indexOf("C", cIndex+1);
            gIndex = dna.indexOf("G", gIndex+1);
        }
        float ratio = (float)(countC + countG) / length;
        return ratio;
}
public int countCTG(String dna){
    int startIndex=dna.indexOf("CTG");
    int count=0;
    while(startIndex != -1){
        count=count+1;
        startIndex=dna.indexOf("CTG",startIndex+1);
    }
    return count;
}
public void testcgRatio() {
        //String dna = "ATGCCATAG"; 
        //System.out.println(cgRatio(dna));
        String dna1 = "ATGCGTAAACGTAAATAG"; 
        System.out.println(cgRatio(dna1));
}
public void testCount(){
    String dna="ATGCTGTAG";
    System.out.println(countCTG(dna));
    String dna1 = "ATGCTGAAACTGAACTGATAG"; 
    System.out.println(countCTG(dna1));
}
}
