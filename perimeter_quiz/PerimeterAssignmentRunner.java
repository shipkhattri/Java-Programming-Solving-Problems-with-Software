import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count=0;
        for(Point p: s.getPoints())
            count++;
        return count;
    }

    public double getAverageLength(Shape s) {
        double perim=getPerimeter(s);
        int points=getNumPoints(s);
        double avg=perim/points;
        return avg;
    }

    public double getLargestSide(Shape s) {
        double largestSide=0.0;
        Point prevPt=s.getLastPoint();
        for(Point currPt: s.getPoints()){
            double dist=prevPt.distance(currPt);
            prevPt=currPt;
            if(largestSide<dist)
                largestSide=dist;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX=0.0;
        for(Point p: s.getPoints())
        {
            double x=p.getX();
            if(largestX<x)
                largestX=x;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerim=0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length=getPerimeter(s);
            if(largestPerim<length)
                largestPerim=length;
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        double largestPerim=0.0;
        File temp=null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length=getPerimeter(s);
            if(largestPerim<length){
                largestPerim=length;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points = "+ getNumPoints(s));
        System.out.println("Average length = " + getAverageLength(s));
        System.out.println("Largest side = " + getLargestSide(s));
        System.out.println("Largest X = " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerim = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter among files = " + largestPerim);
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("Name of file with Largest perimeter = " + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
