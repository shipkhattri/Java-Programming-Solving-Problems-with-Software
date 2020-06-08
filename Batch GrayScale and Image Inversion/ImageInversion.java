
/**
 * Write a description of ImageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class ImageInversion {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            pixel.setRed(255-inPixel.getRed());
            pixel.setGreen(255-inPixel.getGreen());
            pixel.setBlue(255-inPixel.getBlue());
        }
        return outImage;
    }
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource invert= makeGray(inImage);
            String fname=inImage.getFileName();
            String newName = "inverted-"+fname;
            invert.setFileName(newName);
            //inImage.draw();
            invert.save();
            invert.draw();
        }
    }
}
