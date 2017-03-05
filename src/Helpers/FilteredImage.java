package Helpers;

import Filters.ColorFilters;
import Filters.MosaicFilters;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Stack;
import java.util.Vector;

/**
 * Created by Dan Amador on 2/5/2017.
 */
public class FilteredImage {
    public static FilteredImage getInstance() {
        return ourInstance;
    }

    private static FilteredImage ourInstance = new FilteredImage();

    private Stack<Image> history_stack;
    private Stack<String> filtersUsed;
    private Vector<Vector<AsciiPixelStructure>> html_text_vector;

    private FilteredImage() {
        html_text_vector = new Vector<>();
        filtersUsed = new Stack<String>();
        history_stack = new Stack<Image>();
    }

    public void popLast() {
        if (this.history_stack.size() > 1) {
            this.history_stack.pop();
        }
    }


    public void fillAsciiVector(int mosaic_area) {
        ImageCharacteristics ic;
            if (this.getFiltersUsed().peek().contains("Mosaic")) {
                ic = new ImageCharacteristics(this.getImage());
            } else {
                ic = new ImageCharacteristics((MosaicFilters.chooseMosaic(this.getImage(), mosaic_area, mosaic_area)));
            }
            for (int i = 0; i < ic.height; i += mosaic_area/2) {
                Vector<AsciiPixelStructure> line = new Vector<>();
                for (int j = 0; j < ic.width; j += mosaic_area/2) {
                    Color color = ic.pr.getColor(j,i);
                    double colorValue = ColorFilters.average(color).getRed() * 255;
                    char letter = AsciiPixelHelper.getCharFromGrayValue(colorValue);
                    line.add(new AsciiPixelStructure(letter, color));
                }
                this.html_text_vector.add(line);
            }
    }

    public Vector<Vector<AsciiPixelStructure>> getVector() {
        return html_text_vector;
    }

    public void clearVector() {
        html_text_vector.clear();
    }

    public Image getImage() {
        return history_stack.peek();
    }

    public Stack<String> getFiltersUsed() {
        return filtersUsed;
    }

    public void pushImage(Image image) {
        this.history_stack.push(image);
    }

    public void addFilter(String filterUsed) {
        filtersUsed.add(filterUsed);
    }
}
