package TP.Control;

import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.DeserializationUtil;
import TP.Noyau.SerializationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import TP.IU.CreerCompte;
import TP.IU.*;


public class Connexion {
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;

    @FXML
    private Button button;

    @FXML
    void Compte(ActionEvent event) throws Exception{
        String EmailText = Email.getText();
        String PasswordText=Password.getText();
        CompteOrthophoniste orthophoniste = DeserializationUtil.deserialize("compte.ser");
        ApplicationState.getInstance().setOrthophoniste(orthophoniste);
        System.out.println(orthophoniste.getEmail());
        System.out.println(orthophoniste.getPassowrd());
        System.out.println(orthophoniste.getPatients());
        // Vérification de l'e-mail
    if (orthophoniste != null && orthophoniste.getEmail().equals(EmailText) && orthophoniste.getPassowrd().equals(PasswordText)) {
        // Redirection vers la page ProgrammerRDV
        Stage stage = (Stage) button.getScene().getWindow(); 
        Menu page = new Menu(); 
        page.show(); 
        stage.close();
        orthophoniste.afficherTypeRDV();
    } else {
        // Créer une boîte de dialogue personnalisée
        Dialog<Void> dialog = new Dialog<>();
        dialog.initOwner(button.getScene().getWindow());
        dialog.setTitle("Attention");

        // Créer une boîte de dialogue personnalisée avec un bouton "Réessayer"
        DialogPane dialogPane = new DialogPane() {
            {
                getButtonTypes().clear(); // Supprimer les boutons par défaut
                setContentText("E-mail ou Mot de passe incorrect. Veuillez réessayer.");

                ButtonType retryButtonType = new ButtonType("Réessayer", ButtonBar.ButtonData.OK_DONE);
                getButtonTypes().add(retryButtonType);

            }
        };

        dialog.setDialogPane(dialogPane);
        dialog.showAndWait();
    
    }
}
   @FXML
   void Creer(ActionEvent event) throws Exception{
    Stage stage = (Stage) button.getScene().getWindow(); // Récupère la scène actuelle
    CreerCompte page = new CreerCompte(); // Crée la page Menu
    page.show(); // Affiche la page Menu
    stage.close();
   }
}
