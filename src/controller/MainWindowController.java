package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController{
    @FXML private MenuItem menu_file_load;
    @FXML private MenuBar menuBar;

    public MenuItem getMenu_file_load() {
        return menu_file_load;
    }

    @FXML
    private void handleFileAction(final ActionEvent event){
        System.out.println("hola");
    }

    @FXML
    private void handleLoadAction(final ActionEvent event){
        System.out.println("presionaste load");
    }

    //    private void handleMenuItemClicked(ActionEvent event){
//        if (event.getSource() == menu_file_load){
//            System.out.println("loaded");
//        }
//    }
}
