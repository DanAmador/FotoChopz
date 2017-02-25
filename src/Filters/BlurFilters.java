package Filters;

import javafx.scene.image.Image;

import static Helpers.ConvolutionHelpers.convolutionMaskTranslation;

/**
 * Created by Dan Amador on 2/18/2017.
 */
public class BlurFilters {
    public static Image averageBlur(Image image, int kernel_size){
        double[][] kernel = new double[kernel_size][kernel_size];
        for(int i = 0; i < kernel_size; i++){
            for (int j = 0 ; j < kernel_size ; j ++){
                kernel[i][j] = 1;

            }
        }
        return convolutionMaskTranslation(image, kernel, 1.0/(double)(kernel_size * kernel_size));
    }

    public static Image motionBlur(Image image, int kernel_size){
        double[][] kernel = new double[kernel_size][kernel_size];
        for(int i = 0; i < kernel_size; i++){
            for (int j = 0 ; j < kernel_size ; j ++){
                kernel[i][j] = (i == j ? 1 : 0);

            }
        }
        return convolutionMaskTranslation(image, kernel, 1.0/(double)( kernel_size));
    }

}
