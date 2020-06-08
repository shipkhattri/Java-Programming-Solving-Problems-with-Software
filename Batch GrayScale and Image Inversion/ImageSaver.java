
/**
 * Write a description of ImageSaver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class ImageSaver {
    
    public void doSave(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            String fname=inImage.getFileName();
            String newName = "gray-"+fname;
            inImage.setFileName(newName);
            inImage.draw();
            inImage.save();
        }
    }
}
