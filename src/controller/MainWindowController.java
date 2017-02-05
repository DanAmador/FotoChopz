package controller;

import helpers.ImageHistogram;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;



public class MainWindowController{
    @FXML private MenuItem menu_file_load;
    @FXML private Window main_stage;
    @FXML private ImageView main_image;
    @FXML private LineChart main_histogram;
    private FileChooser image_chooser = new FileChooser();


    @FXML
    private void handleLoadAction(final ActionEvent event){
        image_chooser.setTitle("Load Image");
        Image image_file = new Image(String.valueOf(image_chooser.showOpenDialog(main_stage).toURI()));
        ImageHistogram imageHistogram = new ImageHistogram(image_file);
        main_image.setImage(image_file);
        main_histogram.getData().clear();
        if(imageHistogram.isSuccess()){
            main_histogram.getData().addAll(imageHistogram.getSeriesRed(), imageHistogram.getSeriesGreen(),imageHistogram.getSeriesBlue());
        }
    }

}
