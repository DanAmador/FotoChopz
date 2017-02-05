package controller;

import helpers.ImageHistogram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;


public class MainWindowController {
    @FXML
    private MenuItem menu_file_load;
    @FXML
    private Window main_stage;
    @FXML
    private ImageView main_image, filter_image;
    @FXML
    private LineChart main_histogram;
    @FXML
    private CheckMenuItem check_red, check_green, check_blue;
    private Image fullColorFiltered;
    private FileChooser image_chooser = new FileChooser();


    @FXML
    private void handleLoadAction(final ActionEvent event) {
        image_chooser.setTitle("Load Image");
        fullColorFiltered = new Image(String.valueOf(image_chooser.showOpenDialog(main_stage).toURI()));
        ImageHistogram imageHistogram = new ImageHistogram(fullColorFiltered);
        main_image.setImage(fullColorFiltered);
        filter_image.setImage(fullColorFiltered);
        main_histogram.getData().clear();
        if (imageHistogram.isSuccess()) {
            main_histogram.getData().addAll(imageHistogram.getSeriesRed(), imageHistogram.getSeriesGreen(), imageHistogram.getSeriesBlue());
        }
    }

    @FXML
    private void changeColorModel(final ActionEvent event) {
        if (check_red.isSelected() || check_blue.isSelected() || check_green.isSelected()) {
            //TODO Color select shit
        } else {
            //TODO Grayscale shit
        }
    }
}

