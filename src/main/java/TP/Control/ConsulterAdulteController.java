package TP.Control;

import java.io.IOException;
import java.time.LocalDate;

import TP.Noyau.Adulte;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.SerializationUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConsulterAdulteController {

    @FXML
    private TextField numTelTextField;

    @FXML
    private TextField diplomeTextField;

    @FXML
    private TextField professionTextField;

    @FXML
    private Button validerButton;

    @FXML
    private ImageView home;

    @FXML
    private Text adulteText;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String lieuNaissance;

    private Adulte adulte;
    private CompteOrthophoniste compteOrthophoniste;

    

    public BorderPane loadFXMLAndSetData(Adulte adulte) throws IOException {
        this.adulte = adulte;
        adulte.setPatientDetails(adulte.getNom(), adulte.getPrenom(), adulte.getDateNaissance(), adulte.getLieuNaissance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/ConsulterAdulte.fxml"));
        loader.setController(this);
        try {
            BorderPane root = loader.load();
            System.out.println("FXML loaded successfully.");

            numTelTextField.setText(adulte.getNumTel());
            diplomeTextField.setText(adulte.getDiplome());
            professionTextField.setText(adulte.getProfession());

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

        String numTel = numTelTextField.getText();
        String diplome = diplomeTextField.getText();
        String profession = professionTextField.getText();

    if (numTel.isEmpty() || diplome.isEmpty() || profession.isEmpty()) {
        System.out.println("Erreur de saisie. Tous les champs doivent être remplis.");
        return;
    }

    if (!numTel.matches("\\d{10}")) {
        System.out.println("Format incorrect. Le numéro de téléphone doit être au format 10 chiffres.");
        return;
    }
        adulte.setNumTel(numTelTextField.getText());
        adulte.setDiplome(diplomeTextField.getText());
        adulte.setProfession(professionTextField.getText());

        System.out.println("Nom: " + adulte.getNom());
        System.out.println("Prenom: " + adulte.getPrenom());
        System.out.println("Date de naissance: " + adulte.getDateNaissance().toString());
        System.out.println("Lieu de naissance: " + adulte.getLieuNaissance());
        System.out.println("Numéro de téléphone: " + adulte.getNumTel());
        System.out.println("Diplôme: " + adulte.getDiplome());
        System.out.println("Profession: " + adulte.getProfession());
        //Ajouter l'enfant à la liste des patients
        compteOrthophoniste.ajouterPatient(this.adulte);
        SerializationUtil.serialize(this.compteOrthophoniste, "compte.ser");
        
         try {
            
            
            SauvegarderAnamneseAdulteController anamneseAdulteController = new SauvegarderAnamneseAdulteController();
            BorderPane root = anamneseAdulteController.loadFXMLAndSetData(this.adulte);
            Scene scene = new Scene(root);
            Stage stage = (Stage) validerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading SauvegarderAnamneseAdulte.fxml: " + e.getMessage());
            e.printStackTrace();
        }
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
