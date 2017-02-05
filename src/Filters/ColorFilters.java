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
    public static Image chooseColorChannel(Image image, HashMap<String, Boolean> params) {
        ic = new ImageCharacteristics(image);

        for (int x = 0; x < ic.width; x++) {
            for (int y = 0; y < ic.height; y++) {
                Color color = ic.pr.getColor(x, y);

                ic.pw.setColor(x, y,
                        Color.color(
                                params.get("red") ? color.getRed() : 0,
                                params.get("green") ? color.getGreen() : 0,
                                params.get("blue") ? color.getBlue() : 0
                        ));
            }
        }

        return ic.wImage;
    }

    public static Image chooseGrayscaleType(Image image, HashMap<String, Boolean> params) {
        ic = new ImageCharacteristics(image);
        for (int x = 0; x < ic.width; x++) {
            for (int y = 0; y < ic.height; y++) {
                Color color = ic.pr.getColor(x,y);

                ic.pw.setColor(x,y,
                        params.get("average") ? average(color) :
                                (params.get("weight") ? weight(color) :
                                        (params.get("lightness") ? lightness(color) : lightness(color))));
            }
        }
        return ic.wImage;
    }

    private static Color lightness(Color color) {
        double lightVal = (Math.min(Math.min(color.getRed(), color.getGreen()), color.getBlue())+ Math.max(Math.max(color.getRed(), color.getGreen()), color.getBlue()))/2;
        return Color.color(lightVal,lightVal,lightVal);
    }

    private static Color weight(Color color) {
        double weightVal = color.getRed() * 0.21 +  color.getGreen() * 0.72 + color.getBlue() * 0.07;
        return Color.color(weightVal,weightVal,weightVal);
    }

    private static Color average(Color c){
        double av =(c.getRed() + c.getBlue() + c.getGreen())/3;
        return Color.color(av,av,av);
    }
}
