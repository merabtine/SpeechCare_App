package TP.IU;

import TP.Control.ApplicationState;
import TP.Noyau.CompteOrthophoniste;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class QCUIScore extends Stage {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private BorderPane borderPane;

    public QCUIScore() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QCUScore.fxml"));
        ScrollPane root = loader.load();
        //CreerCompteController controller = loader.getController();
        Scene scene = new Scene(root);
        this.setScene(scene);
    }

}
