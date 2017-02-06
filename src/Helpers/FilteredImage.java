package Helpers;

import javafx.scene.image.Image;

import java.util.Stack;

/**
 * Created by Dan Amador on 2/5/2017.
 */
public class FilteredImage {
    private Image image;
    private Stack<String> filtersUsed;
    private static FilteredImage ourInstance = new FilteredImage();

    public static FilteredImage getInstance() {
        return ourInstance;
    }

    private FilteredImage() {
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
