package Filters;

import Helpers.ImageCharacteristics;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

/**
 * Created by Dan Amador on 2/18/2017.
 */
public class BlurFilters {
    private static ImageCharacteristics ic;

    public static Image averageBlur(Image image, int kernel_size){
        double[][] kernel = new double[kernel_size][kernel_size];
        for(int i = 0; i < kernel_size; i++){
            for (int j = 0 ; j < kernel_size ; j ++){
                kernel[i][j] = 1;

            }
        }
        return convolutionBlur(image, kernel, kernel_size * kernel_size);
    }

    public static Image motionBlur(Image image, int kernel_size){
        double[][] kernel = new double[kernel_size][kernel_size];
        for(int i = 0; i < kernel_size; i++){
            for (int j = 0 ; j < kernel_size ; j ++){
                kernel[i][j] = (i == j ? 1 : 0)/(double)(kernel_size * kernel_size);

            }
        }
        return convolutionBlur(image, kernel, kernel_size * kernel_size);
    }
    public static Image convolutionBlur(Image image, double[][] kernel, double bias) {
        int kernelCenter = (kernel.length/2);
        ic = new ImageCharacteristics(image);
        for (int x = 0; x < ic.width; x++) {
            for (int y = 0; y < ic.height; y++) {

                ic.pw.setColor(x, y,
                        kernelMagic(kernel,x-kernelCenter,y-kernelCenter, ic.pr, bias));

            }
        }
        return ic.wImage;
    }



    public static Color kernelMagic(double[][] kernel, int diffX, int diffY, PixelReader pr, double bias){
        double avgR = 0, avgG= 0 ,avgB=0;
        for (int i = 0 ; i < kernel.length; i++){
            for (int j = 0 ; j < kernel.length; j++){
                try{
                    Color color = pr.getColor(diffX + i, diffY+j);
                    avgR += (kernel[i][j] * ( color.getRed()));
                    avgG += (kernel[i][j] * (color.getGreen()));
                    avgB += (kernel[i][j] * (color.getBlue()));
                }catch(IndexOutOfBoundsException e){
                    continue;
                }
            }
        }
        return Color.color(boundaryCheck(avgR / bias), boundaryCheck(avgG/ bias),boundaryCheck(avgB/ bias));
    }

    private static double boundaryCheck(double value){
        if (value > 1) return 1;
        else if (value < 0) return 0;
        return value;
    }
}
