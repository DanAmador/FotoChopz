package Controllers;

import Filters.BlurFilters;
import Filters.MosaicFilters;
import Helpers.FilteredImage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 * Created by Dan Amador on 2/18/2017.
 */


public class BlurDialogController {
    @FXML
    Slider slider_kernel_size;
    @FXML
    Button submit_button;
    @FXML
    Label label_info;
    private FilteredImage fullColorFiltered = FilteredImage.getInstance();

    @FXML
    public void submitValueAverageBlur() {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(BlurFilters.averageBlur(fullColorFiltered.getImage(),kernel_size));
        fullColorFiltered.addFilter(String.format("Average filter with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void submitValueMotionBlur() {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(BlurFilters.motionBlur(fullColorFiltered.getImage(),kernel_size));
        fullColorFiltered.addFilter(String.format("Motion filter with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void updateLabel(){
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        label_info.setText(String.format("Kernel area: %dx%d",  kernel_size, kernel_size));
    }
}
