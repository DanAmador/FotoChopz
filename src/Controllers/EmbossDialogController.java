package Controllers;

import Filters.BlurFilters;
import Filters.EdgeDetectionFilters;
import Helpers.FilteredImage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 * Created by Dan Amador on 2/25/2017.
 */
public class EmbossDialogController {
    @FXML
    Slider slider_kernel_size;
    @FXML
    Button submit_button;
    @FXML
    Label label_info;
    private FilteredImage fullColorFiltered = FilteredImage.getInstance();

    @FXML
    public void submitValueRight() {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(EdgeDetectionFilters.embossImage(fullColorFiltered.getImage(),kernel_size, true));
        fullColorFiltered.addFilter(String.format("Right emboss with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void submitValueLeft() {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(EdgeDetectionFilters.embossImage(fullColorFiltered.getImage(),kernel_size, false));
        fullColorFiltered.addFilter(String.format("Left Emboss filter with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void updateLabel(){
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        label_info.setText(String.format("Kernel area: %dx%d",  kernel_size, kernel_size));
    }
}
