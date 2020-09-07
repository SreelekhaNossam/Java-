
/**
 * Write a description of q3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class q3 {
    public double avgTempInFile(CSVParser parser){
        double avgTemp=0.0;
        double num=0.0;
        for(CSVRecord currentTemp:parser){
            double temp=Double.parseDouble(currentTemp.get("TemperatureF"));
            avgTemp=avgTemp+temp;
            num++;
        }
        avgTemp=avgTemp/num;
        return avgTemp;
}
public double avgTempWithHighHumi(CSVParser parser, int value){
    double avgTemp=0.0;
    double num=0.0;
    for(CSVRecord currTemp:parser){
        double humi=Double.parseDouble(currTemp.get("Humidity"));
        if(humi>=value){
            double temp=Double.parseDouble(currTemp.get("TemperatureF"));
            avgTemp=avgTemp+temp;
            num++;   
        }
    } 
    avgTemp=avgTemp/num;
    return avgTemp;
    }
public void testTempWithHumi() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = avgTempWithHighHumi(parser,80);
        System.out.println("Average temperature with Humidity is " + avgTemp);
    }
public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = avgTempInFile(parser);
        System.out.println("Average temperature in file is " + avgTemp);
    }
}
