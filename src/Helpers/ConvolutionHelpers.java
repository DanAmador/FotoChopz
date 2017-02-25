package Helpers;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import static Helpers.BasicImageHelper.boundaryCheck;

/**
 * Created by Dan Amador on 2/19/2017.
 */
public class ConvolutionHelpers {
    private static ImageCharacteristics ic;
    public static Image convolutionMaskTranslation(Image image, double[][] kernel, double factor) {
        int kernelCenter = (kernel.length/2);
        ic = new ImageCharacteristics(image);
        for (int x = 0; x < ic.width; x++) {
            for (int y = 0; y < ic.height; y++) {

                ic.pw.setColor(x, y,
                        kernelMagic(kernel,x-kernelCenter,y-kernelCenter, ic.pr, factor));
            }
        }
        return ic.wImage;
    }

    public static Color kernelMagic(double[][] kernel, int diffX, int diffY, PixelReader pr, double factor){
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
        return Color.color(boundaryCheck(avgR * factor), boundaryCheck(avgG * factor),boundaryCheck(avgB * factor));
    }
}
