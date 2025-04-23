package TP.Control;
import java.io.IOException;

import TP.IU.QCUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestController {

    @FXML
    private Text headerText;

    @FXML
    private ImageView headerImage;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private ImageView imageView1;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private Text text3;

    @FXML
    private Text text4;

    @FXML
    private Text text5;

    @FXML
    private Text text6;

    @FXML
    private Text text7;

    @FXML
    private Text text8;

    @FXML
    private Text text9;

    @FXML
    private Text text10;

    @FXML
    private Text text11;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private ImageView imageView4;

    @FXML
    private void initialize() {
        headerText.setText("Passez aux tests");
    }

     @FXML
    void handleButtonQRL(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/QRL.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) button1.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load QRL.fxml");
        }
    }

    @FXML
    void handleButtonQCU(ActionEvent event) {
        try {
            Stage stage = (Stage) button2.getScene().getWindow(); 
        QCUI page = new QCUI(); 
        page.show(); 
        stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load QCU.fxml");
        }
    }
    
}
