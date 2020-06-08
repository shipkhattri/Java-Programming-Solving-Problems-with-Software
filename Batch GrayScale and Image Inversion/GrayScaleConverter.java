
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            int avg = (inPixel.getRed()+inPixel.getBlue()+inPixel.getGreen())/3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImage;
    }
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray= makeGray(inImage);
            String fname=inImage.getFileName();
            String newName = "gray-"+fname;
            gray.setFileName(newName);
            //inImage.draw();
            gray.save();
            //gray.draw();
        }
    }
    public void testGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray=makeGray(ir);
        gray.draw();
    
    }
}
