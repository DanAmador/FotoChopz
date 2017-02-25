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
        double[][] kernel = allEdgesKernel(kernel_size);
        return convolutionMaskTranslation(image,kernel, 1);
    }

    public static Image sharpenEdges(Image image, int kernel_size){
        int kernel_center = kernel_size / 2;
        double[][] kernel = allEdgesKernel(kernel_size);
        kernel[kernel_center][kernel_center] += 1;
        return convolutionMaskTranslation(image,kernel, 1);
    }

    private static double[][] allEdgesKernel(int kernel_size){
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
        return kernel;
    }

    public static Image excessSharpness(Image image, int kernel_size) {
        double[][] kernel = allEdgesKernel(kernel_size);
        for (int i=0; i < kernel.length; i++ ){
            for (int j = 0 ; j < kernel[i].length; j++){
                kernel[i][j] = -kernel[i][j];
            }
        }
        kernel[kernel_size/2][kernel_size/2] +=1;
        return convolutionMaskTranslation(image,kernel,1);
    }

    public static Image subtleSharpness(Image image, int kernel_size) {
        double[][] kernel = allEdgesKernel(kernel_size);
        int kernel_center = kernel_size /2;
        double center_value = kernel[kernel_center][kernel_center];
        double free_spaces = (Math.pow(kernel_size, 2) - Math.pow(kernel_size-1,2)) -1;
        double filling_value =  (center_value - (center_value / (kernel_center+ 1)) ) /  free_spaces;

        for (int i=0; i < kernel_size; i++ ){
            for (int j = 0 ; j < kernel_size; j++){
                if (!(i == 0 || j == 0 || j == kernel_size - 1 || i == kernel_size - 1)){
                    kernel[i][j] = filling_value ;

                }
            }
        }
        kernel[kernel_center][kernel_center] = center_value / (kernel_center+ 1);
        return convolutionMaskTranslation(image,kernel,1.0/8.0);

    }
}
