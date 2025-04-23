package TP.IU;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class PConsultationIU extends Stage{
    public PConsultationIU() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProgrammerConsultation.fxml"));
        BorderPane root = loader.load();
        //CreerCompteController controller = loader.getController();
        Scene scene = new Scene(root);
        this.setScene(scene);
    }
}
