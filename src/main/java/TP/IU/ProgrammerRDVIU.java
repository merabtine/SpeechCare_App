package TP.IU;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
public class ProgrammerRDVIU extends Stage{
    public ProgrammerRDVIU() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgrammerRendezvous.fxml"));
        BorderPane root = loader.load();
        System.out.println(root);
        Scene scene = new Scene(root);
        this.setScene(scene);
    }
}
