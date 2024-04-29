/*
--------------------------------------------------------------
   student name: Souhail Daoudi
   student number: 300135458
--------------------------------------------------------------
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class SimilaritySearch {

public static void main(String [] args) throws FileNotFoundException, IOException{
   double [] datahistogram = new double [512];
   double [] datahis = new double [512];
   

// call ColorImage 
     ColorImage colorimage = new ColorImage("queryImages/"+args[0]);
  
       colorimage.readImageQueery("queryImages/"+args[0]);
       colorimage.reduceColor(3);
       colorimage.getPixel(0,0);
   
   
// call ColorHistogram
           ColorHistogram colorhistogramFile= new ColorHistogram("queryImages/"+args[0]); 

                    colorhistogramFile.setImage(colorimage);
                    colorhistogramFile.getHistogram();
      
// loop through image data set
        File directory = new File(args[1]);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".jpg.txt")) {
                    ColorImage colorImagedata = new ColorImage(file.getAbsolutePath());
                    try {
                     datahistogram=colorImagedata.readImageData(file.getAbsolutePath());
                  } 
                  catch (IOException e) {
                
                  }

// store the histogram of the dataset into datahis
               ColorHistogram colorhistogramBits0 = new ColorHistogram(3,datahistogram);
               datahis=colorhistogramBits0.getdatahistogram();

// compare histogram of the query image with the datasetimages
                    ColorHistogram colorhistogramBits = new ColorHistogram(datahis,file.getAbsolutePath());
                    colorhistogramFile.compare(colorhistogramBits);
     
                }
            }
        } else {
            System.out.println("Specified directory does not exist or is not a directory.");
        }

// return the name of the 5 most similar images to the query image 
        colorhistogramFile.fiveimages();


       







}

}