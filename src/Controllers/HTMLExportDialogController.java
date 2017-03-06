package Controllers;

import Filters.AsciiConverterFilter;
import Filters.MosaicFilters;
import Helpers.FilteredImage;
import Helpers.TimeWatch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dan Amador on 3/5/2017.
 */
public class HTMLExportDialogController {
    @FXML
    private Window main_stage;
    @FXML
    Slider slider_area;
    @FXML
    Button submit_button;
    @FXML
    Label label_info;
    @FXML
    CheckBox check_color;
    private FilteredImage fullColorFiltered = FilteredImage.getInstance();
    private FileChooser image_chooser = new FileChooser();

    @FXML
    public void submitValue() {
        int mosaic_area = (int) slider_area.getValue();
        TimeWatch watch = TimeWatch.start();

        Image toExportImg = null;
        if (!fullColorFiltered.getFiltersUsed().empty()) {
            if (fullColorFiltered.getFiltersUsed().peek().contains("Mosaic")) {
                String[] splits = fullColorFiltered.getFiltersUsed().peek().split("x");
                toExportImg = fullColorFiltered.getImage();
                mosaic_area = Integer.parseInt(splits[1]);
            } else {
                toExportImg = MosaicFilters.chooseMosaic(fullColorFiltered.getImage(), mosaic_area, mosaic_area);
            }
        }
        String toExport = AsciiConverterFilter.AsciiHtml(toExportImg, mosaic_area,check_color.isSelected());
        System.out.println(String.format("Successfully created file content in %d seconds", watch.time(TimeUnit.SECONDS)));

        File file = image_chooser.showSaveDialog(main_stage);
        if (file != null) {
            try {
                String name = file.getAbsolutePath();
                name = name.contains(".html") ? name : name + ".html";
                BufferedWriter out = new BufferedWriter(new FileWriter(name));
                out.write(toExport);
                out.close();
                System.out.println("File saved at: " + name);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }


        Stage stage = (Stage) submit_button.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void updateLabel() {
        label_info.setText(String.format("Mosaic area: %dx%d", (int) slider_area.getValue(), (int) slider_area.getValue()));
    }
}
