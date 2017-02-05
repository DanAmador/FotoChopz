package Helpers;

import javafx.scene.image.Image;

import java.util.Stack;

/**
 * Created by Dan Amador on 2/5/2017.
 */
public class FilteredImage {
    private Image image;
    private Stack<String> filtersUsed;

    public FilteredImage() {
        filtersUsed = new Stack<String>();
    }

    public void setImage(Image image){
        this.image = image;
    }

    public Image getImage(){
        return image;
    }
    public void addFilter(String filterUsed){
        filtersUsed.add(filterUsed);
    }
    public Stack<String> getFiltersUsed(){
        return filtersUsed;
    }
}
