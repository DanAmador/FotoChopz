package Controllers;

import Filters.ColorFilters;
import Helpers.FilteredImage;
import Helpers.ImageHistogram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.util.HashMap;


public class MainWindowController {
    @FXML private MenuItem menu_file_load, menu_brightness, menu_mosaic,menu_undo;
    @FXML private Window main_stage;
    @FXML private ImageView main_image, filter_image;
    @FXML private LineChart main_histogram, filteredHistogram;
    @FXML private CheckMenuItem check_red, check_green, check_blue, check_grayscale, check_contrast_inverse, check_contrast;
    @FXML private RadioMenuItem radio_average, radio_lightness, radio_weight;
    private FilteredImage fullColorFiltered = FilteredImage.getInstance();
    private FileChooser image_chooser = new FileChooser();

    @FXML
    private void changeBrightness() throws IOException {
        openDialog("/Views/BrightnessDialog.fxml");
        filter_image.setImage(fullColorFiltered.getImage());
        updateHistogram(fullColorFiltered.getImage());
    }

    @FXML
    private void mosaicFilter() throws IOException {
        openDialog("/Views/MosaicDialog.fxml");
        filter_image.setImage(fullColorFiltered.getImage());
        updateHistogram(fullColorFiltered.getImage());
    }

    @FXML
    private void get_last(){
        fullColorFiltered.popLast();
        updateHistogram(fullColorFiltered.getImage());
    }
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
    private void changeContrast(){
        Image colorImage = ColorFilters.chooseContrast(fullColorFiltered.getImage(),check_contrast_inverse.isSelected());
        filter_image.setImage(colorImage);
        updateHistogram(colorImage);
    }
    @FXML
    private void changeColorModel() {
        HashMap<String, Boolean> params = new HashMap<String,Boolean>();
        Image colorImage;
        if (!check_grayscale.isSelected()) {
            params.put("red", check_red.isSelected());
            params.put("green", check_green.isSelected());
            params.put("blue", check_blue.isSelected());
            colorImage = ColorFilters.chooseColorChannel(fullColorFiltered.getImage(),params);
            filter_image.setImage(colorImage);
            updateHistogram(colorImage);
        } else {
            params.put("lightness", radio_lightness.isSelected() );
            params.put("average", radio_average.isSelected());
            params.put("weight", radio_weight.isSelected());
            colorImage = ColorFilters.chooseGrayscaleType(fullColorFiltered.getImage(), params);
            filter_image.setImage(colorImage);
            updateHistogram(colorImage);
        }
    }

    private void openDialog(String dialogRoute) throws IOException {
        Stage dialog = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(dialogRoute));
        dialog.initStyle(StageStyle.UTILITY);
        Scene scene = new Scene(root, 200,200);
        dialog.setScene(scene);
        dialog.showAndWait();
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

