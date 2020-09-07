
/**
 * Write a description of BatchInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class BatchGrey {
     public ImageResource makeGrey(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //The inverted value is 255 - each rgb component of the pixel.
            int avg=(inPixel.getRed()+inPixel.getGreen()+inPixel.getBlue())/3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        //outImage is your answer
        return outImage;
    }
    
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource greyed = makeGrey(inImage);
            String fname = inImage.getFileName();
            String newName="grey-"+fname;
            greyed.setFileName(newName);
            greyed.draw();
            greyed.save();
        }
    }
    
}
