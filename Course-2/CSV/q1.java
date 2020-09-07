
/**
 * Write a description of q1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class q1 { //weather-2014-10-17.csv
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar=null;
        for(CSVRecord currentRow:parser){
            if(smallestSoFar ==null){
                smallestSoFar=currentRow;
            }
            else{
                
                double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
                double smallTemp=Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if(currentTemp<smallTemp){
                        if(currentTemp!=(-9999)){
                        smallestSoFar=currentRow;
                    }
                    else{
                        smallestSoFar=smallestSoFar;
                    }
                }
                }
            }
        return smallestSoFar;
}
public CSVRecord fileWithColdestTemp(){
    String fileName=null;
    DirectoryResource dr=new DirectoryResource();
    CSVRecord smallestSoFar=null;
    for (File f:dr.selectedFiles()){
        FileResource fr=new FileResource(f);
        CSVRecord currentRow=coldestHourInFile(fr.getCSVParser());
        if(smallestSoFar==null){
            smallestSoFar=currentRow;
            fileName=f.getName();
        }
        else{
            double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
            double smallTemp=Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if(smallTemp!=-9999 && (currentTemp<smallTemp)){
                        smallestSoFar=currentRow;
                        fileName=f.getName();
                }
        }
    }
    System.out.println("File having most coldest temperature " + fileName);
    return smallestSoFar;
}
 /*public CSVRecord lowestHumidity(CSVParser parser){
        CSVRecord smallestSoFar=null;
        for(CSVRecord currentRow:parser){
            if(smallestSoFar ==null){
                smallestSoFar=currentRow;
            }
            else{
                double currentHumi=Double.parseDouble(currentRow.get("Humidity"));
                double smallHumi=Double.parseDouble(smallestSoFar.get("Humidity"));
                if(smallHumi!=(-9999)){
                    if(currentHumi<smallHumi){
                        smallestSoFar=currentRow;
                    }
                }
            }
}
return smallestSoFar;
}**/
/*public void testLowest(){
    FileResource fr = new FileResource();
    CSVRecord lowest = lowestHumidity(fr.getCSVParser());
    System.out.println("Lowest Humidity was " + lowest.get("Humidity")+" on " + lowest.get("DateUTC"));
}**/
public void testSmall(){
    FileResource fr=new FileResource();
    CSVRecord smallest=coldestHourInFile(fr.getCSVParser());
    System.out.println("Coldest Temperature was " + smallest.get("TemperatureF")+" on " + smallest.get("DateUTC") + " at " + smallest.get("TimeEST"));
}
public void testSmallFile(){
    
    CSVRecord smallest=fileWithColdestTemp();
    System.out.println("Coldest Temperature was " + smallest.get("TemperatureF")+" on " + smallest.get("DateUTC") + " at " + smallest.get("TimeEST"));
    System.out.println("All of the temperatures on the coldest day were:");
}
}
