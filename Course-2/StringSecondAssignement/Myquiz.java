
/**
 * Write a description of Myquiz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */ 
import edu.duke.*;
import java.io.*;
public class Myquiz {
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
public void testOn(String dna){
System.out.println("\nTesting printAllGenes on " +dna);
printAllGenes(dna);
}
public void testOn1(String dna){
System.out.println("\nTesting getAllGenes on " +dna);
getAllGenes(dna);
}
public void test(){
testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
testOn("");
testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
}
public void test1(){
testOn1("ATGCCCCGGTAA");
testOn1("ATGCCCGGGTTTTTTTTTTTTTTTTAA");
testOn1("ATGTTTTTTTTTTTTTTTTAA");
}
public double cgRatio(String dna) {
        
        double cgratio=0;
 
        int gIndex = dna.indexOf("G");
        StorageResource sr=getAllGenes(dna);
        
        for(String gene:sr.data()){
            int countC = 0;
            int countG = 0;
            int startIndexC=0;
            int startIndexG=0;
            double countcg=0;
        while(countC!=-1||countG!=-1){
            countC = dna.indexOf("C",startIndexC);
            if(countC != -1) {
                countcg++;
                startIndexC=countC+1;
            }
            countG=dna.indexOf("G",startIndexG);
            if(countG != -1) {
                countcg++;
                startIndexG=countG+1;
            }
        }
        double dnalength=dna.length();
        cgratio=countcg/dnalength;
        return cgratio;
    }
    return cgratio;
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
public void testcg(){
    FileResource fr = new FileResource();
     String dna = fr.asString();
     double out=cgRatio(dna);
     System.out.println(out);
    }
public void processGenes(StorageResource sr){
        int count = 0;
        int countcg = 0;
        String longest= "";
        int length=longest.length();
        for(String gene : sr.data()){
            System.out.println("Gene above 9 char= ");
            if (gene.length() > 60){
                System.out.println("The gene with a length longer then 60 is " +gene); 
                count = count+1;
            }
            double cgRatio=cgRatio(gene);
            if (cgRatio> 0.35){
                countcg=countcg+1;
                System.out.println("the "+countcg+" cg ratio is "+cgRatio);
                
            }
            
        
            if (gene.length() > length){
                 longest=gene;
                 length=gene.length();
                }
    }
        System.out.println("Number of strings above 60= " + count);
        System.out.println("Number of strings above 0.35= " + countcg);
        System.out.println("longest Gene: "+longest);
        System.out.println("Total Number of genes: "+sr.size());
        System.out.println("Length of the longest gene= " + length);
        //System.out.println("CTG Count: "+ ctg);
    }
public void testProcess(){
       //StorageResource srt=new StorageResource();
       //srt.add("ATGCCCCGGTAA");
       //srt.add("ATGCCCGGGTTTTTTTTTTTTTTTTAA");
       //srt.add("ATGTTTTTTTTTTTTTTTTAA");
     FileResource fr = new FileResource();
     String dna = fr.asString();
     System.out.println("DNA: " +dna.toUpperCase());
     System.out.println("Total CTGs: " + countCTG(dna));
     StorageResource sr = getAllGenes(dna);
     processGenes(sr);
    }       
}
