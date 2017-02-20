package Filters;

import Helpers.ConvolutionHelpers;
import javafx.scene.image.Image;

import static Helpers.ConvolutionHelpers.convolutionMaskTranslation;

/**
 * Created by Dan Amador on 2/19/2017.
 */
public class EdgeDetectionFilters {
    public static Image edge45(Image image, int kernel_size) {
        double[][] kernel = new double[kernel_size][kernel_size];
        int kernel_center = kernel_size / 2;
        int counter = 0;
        for(int y = 0; y < kernel_size; y++){
            for (int x = 0 ; x < kernel_size ; x ++){
                if (y == x) {
                    int edgeValue = (x > kernel_center ? x - kernel_center : kernel_center - x);
                    kernel[y][x] = -edgeValue;
                    counter+=edgeValue;
                }else{
                    kernel[y][x] = 0;
                }

            }
        }
        kernel[kernel_center][kernel_center] = counter;
        return convolutionMaskTranslation(image,kernel, 1);
    }

    public static Image horizontalEdge(Image image, int kernel_size) {
        double[][] kernel = new double[kernel_size][kernel_size];
        int kernel_center = kernel_size / 2;
        int counter = 0;
        for(int y = 0; y < kernel_size; y++){
            for (int x = 0 ; x < kernel_size ; x ++){
                if (y < kernel_center  && x == kernel_center) {
                    kernel[y][x] = -1 ;
                    counter+=1;
                }else{
                    kernel[y][x] = 0;
                }

            }
        }
        kernel[kernel_center][kernel_center] = counter;
        return convolutionMaskTranslation(image,kernel, 1);
    }

    public static Image verticalEdge(Image image, int kernel_size) {
        double[][] kernel = new double[kernel_size][kernel_size];
        int kernel_center = kernel_size / 2;
        int counter = 0;
        for(int y = 0; y < kernel_size; y++){
            for (int x = 0 ; x < kernel_size ; x ++){
                if (x == kernel_center && y != kernel_center) {
                    kernel[y][x] = -1 ;
                    counter+=1;
                }else{
                    kernel[y][x] = 0;
                }

            }
        }
        kernel[kernel_center][kernel_center] = counter;
        return convolutionMaskTranslation(image,kernel, 1);
    }

    public static Image allEdges(Image image, int kernel_size){
        double[][] kernel = new double[kernel_size][kernel_size];
        int kernel_center = kernel_size / 2;
        int counter = 0;
        for(int y = 0; y < kernel_size; y++){
            for (int x = 0 ; x < kernel_size ; x ++){

                if (!(x == kernel_center && y == kernel_center)) {
                    kernel[y][x] = -1;
                    counter+=1;
                }else{
                    kernel[y][x] = 0;
                }

            }
        }
        kernel[kernel_center][kernel_center] = counter;
        return convolutionMaskTranslation(image,kernel, 1);
    }
}
