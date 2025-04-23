package TP.IU;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConsulterRDVIU extends Stage{
    public ConsulterRDVIU() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterRDV.fxml"));
        BorderPane root = loader.load();
        //CreerCompteController controller = loader.getController();
        Scene scene = new Scene(root);
        this.setScene(scene);
    }
}
