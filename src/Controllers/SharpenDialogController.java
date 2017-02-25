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
public class SharpenDialogController {
    @FXML
    Slider slider_kernel_size;
    @FXML
    Button submit_button;
    @FXML
    Label label_info;
    private FilteredImage fullColorFiltered = FilteredImage.getInstance();



    @FXML
    public void submitSubtleSharpness() {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(EdgeDetectionFilters.subtleSharpness(fullColorFiltered.getImage(),kernel_size));
        fullColorFiltered.addFilter(String.format("Subtle Sharpness filter with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void submitSharpness() {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(EdgeDetectionFilters.sharpenEdges(fullColorFiltered.getImage(),kernel_size));
        fullColorFiltered.addFilter(String.format("Sharpness harpness filter with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void updateLabel(){
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        label_info.setText(String.format("Kernel area: %dx%d",  kernel_size, kernel_size));
    }

    public void submitExcessiveSharpness(ActionEvent event) {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(EdgeDetectionFilters.excessSharpness(fullColorFiltered.getImage(),kernel_size));
        fullColorFiltered.addFilter(String.format("Excessive sharpness filter with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }
}
