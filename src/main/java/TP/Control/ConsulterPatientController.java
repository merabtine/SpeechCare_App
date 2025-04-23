package TP.Control;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import TP.Noyau.Adulte;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.Enfant;
import TP.Noyau.Patient;
import TP.Noyau.SerializationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConsulterPatientController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField dateNaissanceField;

    @FXML
    private TextField lieuNaissanceField;

    @FXML
    private TextField ageField;

    @FXML
    private Button suivantButton;

    @FXML
    private Text consultationText;

    @FXML
    private ImageView home;

    private CompteOrthophoniste compteOrthophoniste;

    @FXML
    void handleSuivantButton(ActionEvent event) {
        this.compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();
        if (compteOrthophoniste == null) {
            consultationText.setText("Erreur : compteOrthophoniste n'est pas défini.");
            return;
        }

        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String dateNaissanceStr = dateNaissanceField.getText();
        String lieuNaissance = lieuNaissanceField.getText();
        String age = ageField.getText();

        if (nom.isEmpty() || prenom.isEmpty() || dateNaissanceStr.isEmpty() || lieuNaissance.isEmpty() || age.isEmpty()) {
            showAlert("Erreur de saisie", "Tous les champs doivent être remplis.");
            return;
        }
        try {
            LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            

            Patient patient = compteOrthophoniste.consulterPatient(nom, prenom, dateNaissance, lieuNaissance);
            SerializationUtil.serialize(this.compteOrthophoniste, "compte.ser");

            Stage stage = (Stage) suivantButton.getScene().getWindow();
            BorderPane rootPatient = null;

            if (patient instanceof Enfant) {
                ConsulterEnfantController enfantController = new ConsulterEnfantController();
                rootPatient = enfantController.loadFXMLAndSetData((Enfant) patient);
            } else if (patient instanceof Adulte) {
                ConsulterAdulteController adulteController = new ConsulterAdulteController();
                rootPatient = adulteController.loadFXMLAndSetData((Adulte) patient);
            }

            Scene scene = new Scene(rootPatient);
            stage.setScene(scene);
            stage.show();

        } catch (DateTimeParseException e) {
            showAlert("Format de date invalide", "Utilisez le format dd-MM-yyyy pour la date de naissance.");
        } catch (IOException e) {
            consultationText.setText("Erreur lors du chargement du formulaire.");
        }
    }
    @FXML
    void handleButtonMenu(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/Menu.fxml"));
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
    private void showAlert(String title, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
}