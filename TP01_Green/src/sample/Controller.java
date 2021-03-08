package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML Button btnGrayScale;
    @FXML Button btnSepia;
    @FXML Button btnSurprise;
    @FXML ImageView imgBefore;
    @FXML ImageView imgAfter;

    //Import Image with ImagePicker there's an example of it on D2l

    //Convert before image to Gray Scale in the After Image
    public void convertGrayScale(ActionEvent actionEvent) {
    }
hadihv
    //Convert Image to Sepia in the After Image
    public void convertSepia(ActionEvent actionEvent) {
    }

    //Convert Image to whatever you want in the After Image
    public void convertSurprise(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set Images

        /*
        I assume that setting the images needs to be done here
        If not just take out the implements Initializable thing
         */
    }


}
