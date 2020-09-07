
/**
 * Write a description of q2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class q2 {
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
  //To return lowest humidity in a file
  CSVRecord lowestHumidity = null;
  int i = 0;
  for (CSVRecord currentRow: parser) {

   if (lowestHumidity == null) {
    lowestHumidity = currentRow;
   } 
   else {
        try{
           double currentH1 = Double.parseDouble(currentRow.get("Humidity"));
           double largetsH = Double.parseDouble(lowestHumidity.get("Humidity"));
           if (currentH1 < largetsH) {
               lowestHumidity = currentRow;
            }
           }
        catch(NumberFormatException e){
            continue;
        }
    }   
}
  return lowestHumidity;
 }
 public void tester_humidity() {
  //prints lowest humidity in a file
  FileResource fr = new FileResource();
  CSVRecord largest = lowestHumidityInFile(fr.getCSVParser());
  System.out.println("Lowest Humidity was : " + largest.get("Humidity") + " at : " + largest.get("DateUTC") + " at Temperature : " + largest.get("TemperatureF"));

 }
 public CSVRecord lowest_humidity_in_multiple() {
  //returns lowest humidity in multiple files
  CSVRecord lowestsofar = null;
  DirectoryResource dr = new DirectoryResource();
  for (File f: dr.selectedFiles()) {
   FileResource fr = new FileResource(f);
   CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());

   if (lowestsofar == null) {
    lowestsofar = currentRow;
   } else {
    double currentH = Double.parseDouble(currentRow.get("Humidity"));
    double lowestHH = Double.parseDouble(lowestsofar.get("Humidity"));
    if (currentH < lowestHH) {
     lowestsofar = currentRow;
    }


   }




  }
  return lowestsofar;

 }
 public void tester_humidity_many() {
  //prints lowest humidity in multiple files
  CSVRecord lowest = lowest_humidity_in_multiple();
  System.out.println("Lowest Humidity was : " + lowest.get("Humidity") + " at : " + lowest.get("DateUTC"));

 }
}
