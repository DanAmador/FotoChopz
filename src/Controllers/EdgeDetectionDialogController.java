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

import Filters.BlurFilters;
import Filters.MosaicFilters;
import Helpers.FilteredImage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
/**
 * Created by Dan Amador on 2/19/2017.
 */
public class EdgeDetectionDialogController {


/**
 * Created by Dan Amador on 2/18/2017.
 */


    @FXML
    Slider slider_kernel_size;
    @FXML
    Button submit_vertical, submit_horizontal, submit_45;
    @FXML
    Label label_info;
    private FilteredImage fullColorFiltered = FilteredImage.getInstance();

    @FXML
    public void submitValueVerticalEdgeDetection() {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(EdgeDetectionFilters.verticalEdge(fullColorFiltered.getImage(),kernel_size));
        fullColorFiltered.addFilter(String.format("Vertical edge detection with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_vertical.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void submitValueHorizontalEdgeDetection() {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(EdgeDetectionFilters.horizontalEdge(fullColorFiltered.getImage(),kernel_size));
        fullColorFiltered.addFilter(String.format("Horizontal Edge detection with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_horizontal.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void submitValue45EdgeDetection() {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(EdgeDetectionFilters.edge45(fullColorFiltered.getImage(),kernel_size));
        fullColorFiltered.addFilter(String.format("Horizontal Edge detection with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_horizontal.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void submitValueAllEdgeDetection(ActionEvent event) {
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        fullColorFiltered.pushImage(EdgeDetectionFilters.allEdges(fullColorFiltered.getImage(),kernel_size));
        fullColorFiltered.addFilter(String.format("All Edge detection with kernel: %dx%d", kernel_size,kernel_size));
        Stage stage = (Stage) submit_horizontal.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void updateLabel(){
        int kernel_size = (int)(Math.ceil(slider_kernel_size.getValue()) * 2) +1 ;
        label_info.setText(String.format("Kernel area: %dx%d",  kernel_size, kernel_size));
    }


}
