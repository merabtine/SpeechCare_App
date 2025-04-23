package TP.Control;

import java.io.IOException;

import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.Enfant;
import TP.Noyau.SerializationUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConsulterEnfantController extends Stage {

    @FXML
    private TextField numTelParentsTextField;

    @FXML
    private TextField classeEtudeTextField;

    @FXML
    private Button validerButton;

    @FXML
    private ImageView home;

    private Enfant enfant;
    private CompteOrthophoniste compteOrthophoniste;

    public BorderPane loadFXMLAndSetData(Enfant enfant) throws IOException {
        this.enfant = enfant;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/ConsulterEnfant.fxml"));
        loader.setController(this);
        try {
            BorderPane root = loader.load();
            numTelParentsTextField.setText(enfant.getNumTelParents());
            classeEtudeTextField.setText(enfant.getClasseEtude());
            return root;
        } catch (IOException e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
            throw e; 
        }
    }

    @FXML
    private void handleValiderButtonAction() {
        this.compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();

        String numTel = numTelParentsTextField.getText();
        String classeEtude = classeEtudeTextField.getText();

       
        if (!numTel.matches("\\d{10}")) { 
            showAlert("Format incorrect", "Le numéro de téléphone doit être au format 10 chiffres.");
            return;
        }
        

        enfant.setNumTelParents(numTel);
        enfant.setClasseEtude(classeEtude);

        //Ajouter l'enfant à la liste des patients
        compteOrthophoniste.ajouterPatient(this.enfant);
        compteOrthophoniste.afficherPatients();

        // Serialize the orthophoniste object
       SerializationUtil.serialize(this.compteOrthophoniste, "compte.ser");

        // Navigate to SauvegarderAnamnese
        try {
            SauvegarderAnamneseEnfantController anamneseEnfantController = new SauvegarderAnamneseEnfantController();

           

            BorderPane root = anamneseEnfantController.loadFXMLAndSetData(this.enfant);
            Scene scene = new Scene(root);
            Stage stage = (Stage) validerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading SauvegarderAnamneseEnfant.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    void handleButtonMenu(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/ConsulterPatient.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) home.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load Menu.fxml");
        }
    }
}
