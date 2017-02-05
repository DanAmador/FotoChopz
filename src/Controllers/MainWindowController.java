package Controllers;

import Filters.ColorFilters;
import Helpers.FilteredImage;
import Helpers.ImageHistogram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.util.HashMap;


public class MainWindowController {
    @FXML private MenuItem menu_file_load;
    @FXML private Window main_stage;
    @FXML private ImageView main_image, filter_image;
    @FXML private LineChart main_histogram, filteredHistogram;
    @FXML private CheckMenuItem check_red, check_green, check_blue, check_grayscale;
    private FilteredImage fullColorFiltered = new FilteredImage();
    private FileChooser image_chooser = new FileChooser();


    @FXML
    private void handleLoadAction(final ActionEvent event) {
        image_chooser.setTitle("Load Image");
        fullColorFiltered.setImage(new Image(String.valueOf(image_chooser.showOpenDialog(main_stage).toURI())));
        ImageHistogram imageHistogram = new ImageHistogram(fullColorFiltered.getImage());

        main_image.setImage(fullColorFiltered.getImage());
        main_histogram.getData().clear();

        if (imageHistogram.isSuccess()) {
            main_histogram.getData().addAll(imageHistogram.getSeriesRed(), imageHistogram.getSeriesGreen(), imageHistogram.getSeriesBlue());
        }
        updateHistogram(fullColorFiltered.getImage());
    }

    @FXML
    private void changeColorModel() {

        if (!check_grayscale.isSelected()) {
            HashMap<String, Boolean> params = new HashMap<String,Boolean>();
            params.put("red", check_red.isSelected());
            params.put("green", check_green.isSelected());
            params.put("blue", check_blue.isSelected());
            Image colorImage = ColorFilters.chooseColorChannel(fullColorFiltered.getImage(),params);
            filter_image.setImage(colorImage);
            updateHistogram(colorImage);
        } else {
            //TODO Grayscale shit
        }
    }

    private void updateHistogram(Image image_2_histogram){
        ImageHistogram filteredHistogramObj = new ImageHistogram(image_2_histogram);
        filter_image.setImage(image_2_histogram);
        filteredHistogram.getData().clear();
        if (filteredHistogramObj.isSuccess()) {
            filteredHistogram.getData().addAll(filteredHistogramObj.getSeriesRed(), filteredHistogramObj.getSeriesGreen(), filteredHistogramObj.getSeriesBlue());
        }
    }
}

