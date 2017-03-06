package Helpers;

import javafx.scene.image.Image;

import java.util.Stack;

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


    private FilteredImage() {
        filtersUsed = new Stack<String>();
        history_stack = new Stack<Image>();
    }

    public void popLast() {
        if (this.history_stack.size() > 1) {
            this.history_stack.pop();
        }
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
