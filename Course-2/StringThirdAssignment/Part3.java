
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
    int currIndex=dna.indexOf(stopCodon,startIndex+3);
    while(currIndex!=-1){
        int diff=currIndex-startIndex;
        if(diff%3==0){
            return currIndex;
        }
        else{
            currIndex=dna.indexOf(stopCodon,currIndex+1);
        }
    }
    return -1;
}
public void testFindStop(){
    String dna = "ATGTCGTGA"; 
    System.out.println(findStopCodon(dna, 0, "TGA"));
    dna = "ATGTCGTGTAA"; 
    System.out.println(findStopCodon(dna, 0, "TAA"));
    dna = "ATGTCGTGCTAA"; 
    System.out.println(findStopCodon(dna, 0, "TAA"));
}
public String findGene(String dna,int where){
int startIndex=dna.indexOf("ATG",where);
if(startIndex==-1){
    return "";
}
int taaIndex=findStopCodon(dna,startIndex,"TAA");
int tagIndex=findStopCodon(dna,startIndex,"TAG");
int tgaIndex=findStopCodon(dna,startIndex,"TGA");
int minIndex=0;
if(taaIndex==-1 || (tgaIndex!=-1 && (tgaIndex<taaIndex))){
    minIndex=tgaIndex;
}
else{
    minIndex=taaIndex;
}
if(minIndex==-1 || (tagIndex!=-1 && (tagIndex<minIndex))){
    minIndex=tagIndex;
}
if(minIndex==-1){
    return " ";
}
return dna.substring(startIndex,minIndex+3);
}
public void testFindGene(){
String dna="ATGATCTAATTTATGCTGCAACGGTGAAGA";
String out=findGene(dna,0);
System.out.println(out);
}
public void printAllGenes(String dna){
int startIndex=0;
while(true){
    String currGene=findGene(dna,startIndex);
    if(currGene.isEmpty()){
        break;
    }
    System.out.println(currGene);
    startIndex=dna.indexOf(currGene,startIndex+1)+currGene.length();
}
}
public StorageResource getAllGenes(String dna){
StorageResource geneList = new StorageResource();
int startIndex=0;
while(true){
    String currGene=findGene(dna,startIndex);
    if(currGene.isEmpty()){
        break;
    }
    geneList.add(currGene);
    startIndex=dna.indexOf(currGene,startIndex)+currGene.length();
}
return geneList;
}
/*public void testOn(String dna){
System.out.println("\nTesting printAllGenes on " +dna);
printAllGenes(dna);
}**/
public void testOn1(String dna){
System.out.println("\nTesting getAllGenes on " +dna);
getAllGenes(dna);
}
/*public void test(){
testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
testOn("");
testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
}**/
//public void test1(){
//testOn1("ATGCCCCGGTAA");
//testOn1("ATGCCCGGGTTTTTTTTTTTTTTTTAA");
//testOn1("ATGTTTTTTTTTTTTTTTTAA");
//}
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

public void processGenes(StorageResource sr){
        int count = 0;
        int count1 = 0;
        String longest="";
        int length=longest.length();
        double ratio=0.35d;
        for(String gene : sr.data()){
            System.out.println("Gene above 9 char= ");
            if (gene.length() > 9){
                System.out.println(gene);
                count = count +1;
                
            }
            
            System.out.println(" Gene above 0.35 cgratio= ");
            if (cgRatio(gene) > ratio){
                System.out.println(gene);
                count1=count1+1;
                
            }
            
            if (gene.length() > length){
                 length= gene.length();
            }
        }
        System.out.println("Number of strings above 9= " + count);
        System.out.println("Number of strings above 0.35= " + count1);
        System.out.println("Length of the longest gene= " + length);
    }
public void testProcess(){
       StorageResource srt=new StorageResource();
       srt.add("ATGCCCCGGTAA");
       srt.add("ATGCCCGGGTTTTTTTTTTTTTTTTAA");
       srt.add("ATGTTTTTTTTTTTTTTTTAA");
       //FileResource fr=new FileResource();
       //String dna=fr.asString();
       processGenes(srt);
    }       
}