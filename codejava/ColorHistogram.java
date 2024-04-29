
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



class ColorHistogram{

// class variables
private String       filename;
private double []    histogram;
private int          pixelNumber;
private int          indexHistogramme;
private double []    histogramcompared;
private double []    histogrameNormalized;
private double       max=0.0;
private int          N ;
private double []    datahistogram ;
private List<String> thefilesnames= new ArrayList ();
private List<String> fivenameshistogram= new ArrayList();



    public ColorHistogram (int d, double [] datahistogram){
     this.datahistogram=datahistogram;
     N =(int)Math.pow(2, 3*3);

    }
    public ColorHistogram ( double [] histogram,String filename){
      this.histogram=histogram;
      this.filename=filename;
 
     }
    public ColorHistogram (String filename){
        this.filename=filename;
    }

    public double [] getdatahistogram(){
      double [] datahistogrameNormalized= new double [N];
// normalize histogram of dataset image
      for(int i=0;i<N;i++){
           datahistogrameNormalized[i]=datahistogram[i]/(480*360);

      }
      return datahistogrameNormalized;
    }
    public void setImage(ColorImage image){
       N =(int)Math.pow(2, 3*3);
      histogram = new double [N] ;
      histogramcompared=new double[N];

      this.pixelNumber=image.pixelNumber;
    // System.out.println(pixelNumber);

// Count how many pixels of each color are contained in the reduced histogram 
// and create the histogram
      for(int i=0;i<image.pixelNumber-1;i++){
          indexHistogramme=((image.intArrayPixels[i][0]<<(2*3))+(image.intArrayPixels[i][1]<<3)+image.intArrayPixels[i][2]);
         histogram[indexHistogramme]++;   
   
    }
  
  }

    public double[] getHistogram() {
  //normalize the histogram of query image and return the normalized histogram
      histogrameNormalized= new double [N] ;
      for(int i=0;i<N;i++){
           histogrameNormalized[i]=histogram[i]/(pixelNumber);
      }     
      return histogrameNormalized;
    }

    public double []compare(ColorHistogram hist){
 // compare the histogram of query image with histograms of dataset image  
      double somme=0.0;
      
    
    for(int g=0;g<histogram.length;g++){
      
      if(histogrameNormalized[g]<hist.histogram[g]){
             histogramcompared[g]=histogrameNormalized[g];
             }

      else{
             histogramcompared[g]=hist.histogram[g];
             }

       somme=somme+histogramcompared[g];
  // Find the maximum values of the compared histogram and store the name of its file.
  // The five last values of "thefilesnames" are the files names with maximum 
  // value of intersection between the two histograms
   if(max<somme){
       max=somme;
       if(!(thefilesnames.contains(hist.filename))){
       thefilesnames.add(hist.filename);
       }
     }

    }
return histogramcompared;
         }

public List<String> fiveimages(){
// add last 5 elements of "thefilesnames"
        for(int p=1;p<6;p++){
               fivenameshistogram.add(thefilesnames.get(thefilesnames.size()-p));
                }

//print the  name of the 5 similar images
        System.out.println("");
        System.out.println("The name of the 5 most similar images to the query image : ");
        System.out.println("");

        for(String fivenames: fivenameshistogram){
          System.out.println(fivenames);
           }

return fivenameshistogram;
}
        

   public void ColorHistogram (String filename){
 try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (double value : histogram) {
                writer.println(value);
            }
    
        } catch (IOException e) {
      
        }
    }


   
}
