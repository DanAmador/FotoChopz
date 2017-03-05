package Filters;

import Helpers.AsciiPixelStructure;
import Helpers.FilteredImage;

import java.util.Vector;

/**
 * Created by Dan Amador on 3/5/2017.
 */
public class AsciiConverterFilter {

    public static String AsciiHtml(Vector<Vector<AsciiPixelStructure>> char_Vector,String title, Boolean color) {
        String html_text = "<!DOCTYPE html><html><head><title>%s</title><body>%s</body></html>";
        String currentText = "";
        for (Vector<AsciiPixelStructure> line : char_Vector) {
            currentText +="<p>";
            for (AsciiPixelStructure letter : line) {
                currentText += color ? letter.getLetterWithColor() : letter.getLetter();
            }
            currentText += "</p>";
        }
        return String.format(html_text, title, currentText);
    }
}

