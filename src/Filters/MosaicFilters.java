package Filters;

import Helpers.ImageCharacteristics;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Created by Dan Amador on 2/6/2017.
 */
public class MosaicFilters {
    private static ImageCharacteristics ic;
    public static Image chooseMosaic(Image image, int mosaic_height, int mosaic_width) {
        ic = new ImageCharacteristics(image);

        final int image_X = (int) ic.wImage.getWidth();
        final int image_Y = (int) ic.wImage.getHeight();
        int t = (image_X / mosaic_width) + (image_X % mosaic_width == 0 ? 0 : 1);
        int r = (image_Y / mosaic_height) + (image_Y % mosaic_height == 0 ? 0 : 1);

        int area = mosaic_height * mosaic_width;
        int area_Y_reduced = mosaic_width * (image_Y % mosaic_height);
        int area_X_reduced = mosaic_height * (image_X % mosaic_width);
        int area_XY_reduced = (image_X % mosaic_width) * (image_Y % mosaic_height);

        int [][] reds=new int[t][r];
        int [][] greens=new int[t][r];
        int [][] blues=new int[t][r];
        Color rgb_pixel;

        for(int i=0, o=0; i<image_X; i++) {
            if(i % mosaic_width == 0 && i>0) { o++; }
            for(int j=0, p=0; j<image_Y; j++) {
                if (j % mosaic_height == 0 && j>0) { p++; }
                rgb_pixel = ic.pr.getColor(i, j);

                reds[o][p] = reds[o][p] + (int) (255 * rgb_pixel.getRed());
                greens[o][p] = greens[o][p] + (int) (255 * rgb_pixel.getGreen());
                blues[o][p] = blues[o][p] + (int) (255 *rgb_pixel.getBlue());

                if (i % mosaic_width == mosaic_width-1 && j % mosaic_height == mosaic_height-1) {
                    reds[o][p] = reds[o][p] / area;
                    greens[o][p] = greens[o][p] / area;
                    blues[o][p] = blues[o][p] / area;
                } else if (i % mosaic_width == mosaic_width-1 && j == image_Y-1) {
                    reds[o][p] = reds[o][p] / area_Y_reduced;
                    greens[o][p] = greens[o][p] / area_Y_reduced;
                    blues[o][p] = blues[o][p] / area_Y_reduced;
                } else if (j % mosaic_height == mosaic_height-1 && i == image_X-1) {
                    reds[o][p] = reds[o][p] / area_X_reduced;
                    greens[o][p] = greens[o][p] / area_X_reduced;
                    blues[o][p] = blues[o][p] / area_X_reduced;
                } else if (i == image_X-1 && j == image_Y-1) {
                    reds[o][p] = reds[o][p] / area_XY_reduced;
                    greens[o][p] = greens[o][p] / area_XY_reduced;
                    blues[o][p] = blues[o][p] / area_XY_reduced;
                }
            }
        }

        for(int i=0, o=0; i < image_X; i++){
            if (i % mosaic_width == 0 && i>0) o++;
            for(int j=0, p=0; j<image_Y; j++) {
                if(j % mosaic_height == 0 && j>0) p++;
                ic.pw.setColor(i,j, Color.rgb(
                        reds[o][p], greens[o][p], blues[o][p]
                ));
            }
        }
        return ic.wImage;
    }
}
