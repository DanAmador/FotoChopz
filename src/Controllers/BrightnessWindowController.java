package Controllers;

import Filters.ColorFilters;
import Helpers.FilteredImage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;


/**
 * Created by Dan Amador on 2/5/2017.
 */
public class BrightnessWindowController {
    @FXML private Slider slider_brightness;
    @FXML private Button button_brightness;
    private FilteredImage fullColorFiltered = FilteredImage.getInstance();

    @FXML
    public void submitValue(){
        fullColorFiltered.setImage(ColorFilters.chooseBrightness(fullColorFiltered.getImage(),slider_brightness.getValue()));
        fullColorFiltered.addFilter(String.format("Brightness with value %f brightness", slider_brightness.getValue() ));
    }

}


