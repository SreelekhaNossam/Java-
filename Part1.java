
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        String result=" ";
        int startIndex=dna.indexOf("ATG");
        if(startIndex==-1){
            return " ";
        }
        int stopIndex=dna.indexOf("TAA",startIndex+3);
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
        String gene=findSimpleGene(dna);
        System.out.println("Gene is:  " +gene);
        String dna1="AATTCGATAATAAGGT";
        System.out.println("DNA Strand is:  "+dna1);
        String gene1=findSimpleGene(dna1);
        System.out.println("Gene is:  " +gene1);
        String dna2="AATGCAGTAAATAAGGT";
        System.out.println("DNA Strand is:  "+dna2);
        String gene2=findSimpleGene(dna2);
        System.out.println("Gene is:  " +gene2);
        String dna3="AATGCGGTAATATGGT";
        System.out.println("DNA Strand is:  "+dna3);
        String gene3=findSimpleGene(dna3);
        System.out.println("Gene is:  " +gene3);
}
public static void main (String[] args) {
        Part1 gene = new Part1();
        gene.testSimpleGene();
    }
}
