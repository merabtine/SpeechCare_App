package TP.Control;

import TP.IU.ConsulterRDVIU;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.SerializationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class ProgrammerConsult {
    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField ageField;

    @FXML
    private Button suivantButton;

    @FXML
    private Text consultationText;

    private CompteOrthophoniste compteOrthophoniste;
    @FXML
    void handleSuivantButton(ActionEvent event) throws Exception{
        this.compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();
        if (compteOrthophoniste == null) {
            consultationText.setText("Erreur : compteOrthophoniste n'est pas défini.");
            return;
        }

        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String age = ageField.getText();

        if (nom.isEmpty() || prenom.isEmpty() || age.isEmpty()) {
            System.out.println("Erreur de saisie. Tous les champs doivent être remplis.");
            return;
        }
            LocalDate dateRDV=ApplicationState.getInstance().getRDVDate();
            LocalTime heureRDV=ApplicationState.getInstance().getRDVTime();
            System.out.println(dateRDV);
            System.out.println(heureRDV);
            compteOrthophoniste.ProgrammerConsultation(dateRDV,heureRDV, nom, prenom,Integer.parseInt(age));
            SerializationUtil.serialize(compteOrthophoniste, "compte.ser");
            Stage stage = (Stage) suivantButton.getScene().getWindow(); 
            ConsulterRDVIU page = new ConsulterRDVIU(); 
            page.show(); 
            stage.close();
    }

}