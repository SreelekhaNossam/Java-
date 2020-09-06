
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna,String startCodon, String stopCodon){
        String result=" ";
        startCodon="ATG";
        stopCodon="TAA";
        if( Character.isUpperCase(dna.charAt(0)) ) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int startIndex=dna.indexOf(startCodon);
        if(startIndex==-1){
            return " ";
        }
        int stopIndex=dna.indexOf(stopCodon,startIndex+3);
        if(stopIndex==-1){
            return " ";
        }
        int length=stopIndex-startIndex;
        if (length%3==0){
            result= dna.substring(startIndex,stopIndex+3);
        }
        return result;
}
    public void testSimpleGene(){
        String dna="AATGCGTATATGGT";
        System.out.println("DNA Strand is:  "+dna);
        String gene=findSimpleGene(dna,"startCodon","stopCodon");
        System.out.println("Gene is:  " +gene);
        String dna1="AATTCGATAATAAGGT";
        System.out.println("DNA Strand is:  "+dna1);
        String gene1=findSimpleGene(dna1,"startCodon","stopCodon");
        System.out.println("Gene is:  " +gene1);
        String dna2="Aatgcagtaaataagat";
        System.out.println("DNA Strand is:  "+dna2);
        String gene2=findSimpleGene(dna2,"startCodon2","stopCodon2");
        System.out.println("Gene is:  " +gene2);
        String dna3="AATGCGGTAATATGGT";
        System.out.println("DNA Strand is:  "+dna3);
        String gene3=findSimpleGene(dna3,"startCodon3","stopCodon3");
        System.out.println("Gene is:  " +gene3);
}
public static void main (String[] args) {
        Part2 gene = new Part2();
        gene.testSimpleGene();
    }
}