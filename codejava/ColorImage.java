/*
--------------------------------------------------------------
   student name: Souhail Daoudi
   student number: 300135458
--------------------------------------------------------------
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ColorImage{
// class variables
     private   int width=0;
     private   int height=0;
     private   String [][] doubledimStringPixels;    
     protected int [] [] intArrayPixels;
     private   int[] pixel = new int [3];  
     protected int pixelNumber;         
     private   String filename;
     
// constructor    
public ColorImage(String filename){
      this.filename=filename;
       }

public  int [] getPixel(int i, int j){
//return RGB values at position (i,j)
      for(int k=0;k<3;k++){
         pixel[k]=Integer.parseInt(doubledimStringPixels[i][j].split(",")[k]);
         }
    return pixel;
        }

public void reduceColor(int d) {
// reduce the color space to a 3-bit representation to the array
    for(int i=0;i<pixelNumber-1;i++){  
        intArrayPixels[i][0]=  intArrayPixels[i][0]>>(8-d);
        intArrayPixels[i][1]=  intArrayPixels[i][1]>>(8-d);
        intArrayPixels[i][2]=  intArrayPixels[i][2]>>(8-d);

        }
  
    }

    public  double [] readImageData(String fileName)throws IOException, FileNotFoundException{
//read the file of dataset and store its histogram

        Scanner scanner = new Scanner(new File(fileName));
            int arraySize = scanner.nextInt();
            double[] doubleArray = new double[arraySize];

            for (int i = 0; i < arraySize; i++) {
                doubleArray[i] = scanner.nextDouble();
            }
            scanner.close();
        return doubleArray ;

    }
        public  String [][] readImageQueery(String fileName)throws IOException, FileNotFoundException{
               String [] singledimStringPixels;
//    read file of querry image and find pixel number of picture
            int index=0;
            int myindex=0;
                Scanner   sc = new Scanner(new FileReader(fileName));
                String str=" ";
                
                for(int i=0;i<8;i++){
                    if(i==5){
                       width=Integer.parseInt(sc.next());
                    }
                    else if(i==6){
                        height=Integer.parseInt(sc.next());
                    }
                    else{
                str=sc.next();
                    }
                }
//find pixel number of picture
          pixelNumber=width*height;

// store RGB values into single  string array

                singledimStringPixels= new String [pixelNumber*3]; 

                 while (sc.hasNext()) {
                      str = sc.next();
                      singledimStringPixels[index]=str;
                      index++;  
                }
                sc.close();
// convert single string array into 2 Dimension string array

                doubledimStringPixels = new String [pixelNumber/5][5] ;
                for(int i=0;i<pixelNumber-1;i++){
                    for(int j=0;j<5;j++){
                        if (myindex< singledimStringPixels.length-2) {
                          doubledimStringPixels[i][j]=singledimStringPixels[myindex]+","+singledimStringPixels[myindex+1]+","+singledimStringPixels[myindex+2];
                          myindex=myindex+3;
                        }       
                }
            }
         
// convert 2 Dimension string array into int list 
                List< int[]> intListPixels=new ArrayList();
                intArrayPixels = new int [pixelNumber-1] [3];
                for(int i=0;i<pixelNumber/5;i++){
                    for(int j=0;j<5;j++){
                        pixel[0]=Integer.parseInt(doubledimStringPixels[i][j].split(",")[0]);
                        pixel[1]=Integer.parseInt(doubledimStringPixels[i][j].split(",")[1]);
                        pixel[2]=Integer.parseInt(doubledimStringPixels[i][j].split(",")[2]);
                        intListPixels.add(pixel.clone());       
                        }
                    }
          
//convert int list into int array
                    for(int i=0;i<pixelNumber-1;i++){
     
                        intArrayPixels[i][0]=intListPixels.get(i)[0];   
                        intArrayPixels[i][1]=intListPixels.get(i)[1];
                        intArrayPixels[i][2]=intListPixels.get(i)[2];                   
                   }

        return doubledimStringPixels;
        }
        
    }
                
              
    

