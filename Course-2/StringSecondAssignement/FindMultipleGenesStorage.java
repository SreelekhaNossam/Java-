import edu.duke.*;

import java.io.File;

public class FindMultipleGenesStorage {
   public int findStopIndex(String dna, int index) {
       int tgaIndex = dna.indexOf("tga", index);
       if ( tgaIndex == -1 || ( tgaIndex-index ) % 3 != 0 ) {
           tgaIndex = dna.length();
       }
       int taaIndex = dna.indexOf("taa", index);
       if ( taaIndex == -1 || ( taaIndex-index ) % 3 != 0 ) {
           taaIndex = dna.length();
       }
       int tagIndex = dna.indexOf("tag", index);
       if ( tagIndex == -1 || ( tagIndex-index ) % 3 != 0 ) {
           tagIndex = dna.length();
       }
       return Math.min( tgaIndex, Math.min(taaIndex, tagIndex) );
   }
   public StorageResource storeAll(String dna) {
       String dnaLow = dna.toLowerCase();
       int start = 0;
       StorageResource genes = new StorageResource();
       while (true) {
           int startIndex = dnaLow.indexOf( "atg", start );
           if ( startIndex == -1 ) {
               break;
           }
           int stop = findStopIndex( dnaLow, startIndex+3 );
           if ( stop != dna.length() ) {
               genes.add( dna.substring(startIndex, stop+3) );
               start = stop + 3;
           } else {
               start = start + 3;
          }             
       }       
       return genes;
   }
   public void testStorageFinder() {
       FileResource dnaFile = new FileResource();
       StorageResource genesFound = storeAll( dnaFile.asString() );
       System.out.println( "Number of genes found: "+genesFound.size() );
       printGenes( genesFound );
   }
   public float cgRatio( String dna ) {
       String dnaLow = dna.toLowerCase();
       int cgCount = 0;
       int start = 0;       
       while (true) {
           int pos = dnaLow.indexOf("c", start);
           if (pos == -1) {
               start = 0;
               break;
           }
           cgCount += 1;
           start = pos + 1;
       }
       while (true) {
           int pos = dnaLow.indexOf("g", start);
           if (pos == -1) {
               start = 0;
               break;
           }
           cgCount += 1;
           start = pos + 1;
       }
       return ( (float) cgCount ) / dna.length();
   }
   public void printGenes( StorageResource sr ) {
       int sixtyCharQty = 0;
       int highCgRatioQty = 0;
       float cgRatioConst = (float) 0.35;       
       for ( String s : sr.data() ) {          
            if ( s.length() > 60 ) {
                System.out.println( "String longer than 60 characters: "+s );
                sixtyCharQty++;
            }
            if ( cgRatio(s) > cgRatioConst ) {
               System.out.println( "String with C-G-ratio higher than 0.35: "+s );
               highCgRatioQty++;
            }
       }
       System.out.println( "60 characters qty: "+sixtyCharQty );
       System.out.println( "Strings with C-G-ratio higher than 0.35: "+highCgRatioQty );
   }
}