/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part1 {
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
        startIndex=dna.indexOf(currGene,startIndex)+currGene.length();
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
public void testOn(String dna){ //Printing//
    System.out.println("\nTesting printAllGenes on " +dna);
    printAllGenes(dna);
}
public void testGet(String dna){     // get genes//
    System.out.println("\nTesting getAllGenes on " +dna);
    StorageResource gene=getAllGenes(dna);
    for(String g:gene.data()){
        System.out.println(g);
    }
}
public void test(){
    testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn("");
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
}
public void testG(){
    testGet("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testGet("");
    testGet("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
}
}
