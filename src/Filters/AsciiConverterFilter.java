package Filters;

import Helpers.AsciiPixelHelper;
import Helpers.AsciiPixelStructure;
import Helpers.ImageCharacteristics;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Created by Dan Amador on 3/5/2017.
 */
public class AsciiConverterFilter {

    public static String AsciiHtml(Image image, int mosaic_area, Boolean withColor) {
        ImageCharacteristics ic = new ImageCharacteristics(image);
        String html_text = "<!DOCTYPE html><html><head><style>p{font-weight:bold;margin:0}</style><title>Image to Html</title><body>%s</body></html>";
        String currentText = "";

        for (int i = 0; i < ic.height; i += mosaic_area) {
            currentText +="<p>";
            for (int j = 0; j < ic.width; j += mosaic_area) {
                Color color = ic.pr.getColor(j,i);
                double colorValue = ColorFilters.lightness(color).getRed() * 255;
                String letter = AsciiPixelHelper.getCharFromGrayValue(colorValue);
                AsciiPixelStructure asciiLetter = new AsciiPixelStructure(letter, color);
                currentText += withColor ? asciiLetter.getLetterWithColor() : asciiLetter.getLetter();
            }
            currentText += "</p>";
        }
        return String.format(html_text, currentText);
    }
}

