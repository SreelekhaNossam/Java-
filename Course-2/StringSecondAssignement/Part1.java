
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part1 {
    public int findStopCodon(String dna,int startIndex,String stopCodon){
        int currIndex=dna.indexOf(stopCodon,startIndex+3);
        while (currIndex != -1){
            int diff=currIndex-startIndex;
            if(diff%3==0){
                return currIndex;
            }
            else{return currIndex=dna.indexOf(stopCodon,currIndex+1);
    }
}
return dna.length();
}
//public void testFindStop(){
  //  String dna="xxxyyyzzzTAAxxxyyyzzzTAAxx";
  //  int dex=findStopCodon(dna,0,"TAA");
  //  if (dex!=9){
  //      System.out.println("Error on 9");
  //  }
  //  dex=findStopCodon(dna,9,"TAA");
  //  if(dex!=21){
  //      System.out.println("Error on 21");
  //  }
  //  dex=findStopCodon(dna,21,"TAA");
  //  if (dex!=26){
  //      System.out.println("Error on 26");
  //  }
  //  dex=findStopCodon(dna,0,"TAG");
  //  if(dex!=26){
  //      System.out.println("Error on 26 TAA");
  //  }
  //  System.out.println("Tests Finished");
//}
public String findGene(String dna,int where){
    int startIndex=dna.indexOf("ATG",where);
    if(startIndex==-1){
        return "";
    }
    int taaIndex=findStopCodon(dna,startIndex,"TAA");
    int tagIndex=findStopCodon(dna,startIndex,"TAG");
    int tgaIndex=findStopCodon(dna,startIndex,"TGA");
    int temp=Math.min(taaIndex,tagIndex);
    int minIndex=Math.min(temp,tgaIndex);
    if(minIndex == dna.length()){
        return " ";
    }
    return dna.substring(startIndex,minIndex+3);
}
public void testFindGene(){
   FileResource file=new FileResource();
   String dna=file.asString();
   findGene(dna,0);
}
public void printAllGenes(String dna){
    int startIndex=0;
    while (true){
      String currGene=findGene(dna,startIndex);
      if(currGene.isEmpty()){
         break;
       }
      System.out.println(currGene);
      startIndex=dna.indexOf(currGene,startIndex)+currGene.length();
    }
}
public void testOn(String dna){
    System.out.println("Testing printAllGenes on "+dna);
    printAllGenes(dna);
}
public void test(){
    testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    testOn(" ");
    testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    testOn("ATGTAATTATGTCCTAGGGATGCCCTAGT");
    //testOn("ATGTAAGATGCCCTAGT");
    //testOn("ATGTAAGATGCCCTTGT");
}
}
