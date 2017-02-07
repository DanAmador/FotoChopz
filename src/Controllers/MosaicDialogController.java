package Controllers;

import Filters.MosaicFilters;
import Helpers.FilteredImage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 * Created by Dan Amador on 2/6/2017.
 */
public class MosaicDialogController {
    @FXML Slider slider_height, slider_width;
    @FXML Button submit_button;
    @FXML Label label_info;
    private FilteredImage fullColorFiltered = FilteredImage.getInstance();

    @FXML
    public void submitValue() {
        int height = (int)slider_height.getValue();
        int width = (int)slider_width.getValue();
        fullColorFiltered.setImage(MosaicFilters.chooseMosaic(fullColorFiltered.getImage(),height,width ));
        fullColorFiltered.addFilter(String.format("Mosaic filter with area: %dx%d", height,width));
        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void updateLabel(){
     label_info.setText(String.format("Mosaic area: %dx%d", (int)slider_height.getValue(), (int) slider_width.getValue()));
    }
}
