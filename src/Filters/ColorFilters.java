package Filters;

import Helpers.ImageCharacteristics;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.HashMap;

/**
 * Created by Dan Amador on 2/5/2017.
 */
public class ColorFilters {
    private static ImageCharacteristics ic;
    /*
    @param image is the image to the processed
    @param params is a map containing the RGB values to process
     */
    public static Image chooseColorChannel(Image image, HashMap params) {
        ic = new ImageCharacteristics(image);

        for (int x = 0; x < ic.width; x++){
            for (int y = 0; y < ic.height; y++){
                Color color = ic.pr.getColor(x,y);

                ic.pw.setColor(x,y,
                        Color.color(
                        (boolean)params.get("red") ?  color.getRed() : 0 ,
                        (boolean) params.get("green") ? color.getGreen() : 0,
                        (boolean)params.get("blue") ? color.getBlue() : 0
                ));
            }
        }

        return ic.wImage;
    }
}
