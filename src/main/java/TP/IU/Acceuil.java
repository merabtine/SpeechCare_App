package TP.IU;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.DeserializationUtil;
import TP.Noyau.SerializationUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Acceuil extends Stage {

    public Acceuil() throws Exception {
        CompteOrthophoniste orthophoniste = DeserializationUtil.deserialize("compte.ser");
        // CompteOrthophoniste orthophoniste = new CompteOrthophoniste("Mohammed", "Youcef", "123 Rue des Roses", "0555123421", "youcef@gmail.com", "password");
        orthophoniste.setAnamneseEnfant();
        orthophoniste.setAnamneseAdulte();

        orthophoniste.creerQuestionsQRL();
        orthophoniste.creerAnamnese();

        //ApplicationState.getInstance().setOrthophoniste(orthophoniste);
        SerializationUtil.serialize((orthophoniste), "compte.ser");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Connexion.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root);
        this.setScene(scene);
    }
}
