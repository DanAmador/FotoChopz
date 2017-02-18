package Helpers;

import javafx.scene.image.Image;

import java.util.Stack;

/**
 * Created by Dan Amador on 2/5/2017.
 */
public class FilteredImage {
    private Stack<Image> history_stack;
    private Stack<String> filtersUsed;
    private static FilteredImage ourInstance = new FilteredImage();

    public static FilteredImage getInstance() {
        return ourInstance;
    }

    private FilteredImage() {
        filtersUsed = new Stack<String>();
        history_stack = new Stack<Image>();
    }

    public void pushImage(Image image){
        this.history_stack.push(image);
    }

    public void popLast(){
        if (this.history_stack.size() > 1){
            this.history_stack.pop();
        }
    }

    public Image getImage(){
        return history_stack.peek();
    }
    public void addFilter(String filterUsed){
        filtersUsed.add(filterUsed);
    }
    public Stack<String> getFiltersUsed(){
        return filtersUsed;
    }
}
