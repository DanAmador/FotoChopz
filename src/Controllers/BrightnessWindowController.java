package Controllers;

import Filters.ColorFilters;
import Helpers.FilteredImage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;


/**
 * Created by Dan Amador on 2/5/2017.
 */
public class BrightnessWindowController {
    @FXML private Slider slider_brightness;
    @FXML private Button submit_button;
    @FXML private Label label_brightness;
    private FilteredImage fullColorFiltered = FilteredImage.getInstance();

    @FXML
    public void submitValue(){
        fullColorFiltered.pushImage(ColorFilters.chooseBrightness(fullColorFiltered.getImage(),slider_brightness.getValue()));
        fullColorFiltered.addFilter(String.format("Brightness with value %f brightness", slider_brightness.getValue() ));
        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void updateLabel(){
        label_brightness.setText(String.format("Brightness level: %d", (int) slider_brightness.getValue()));
    }
}


