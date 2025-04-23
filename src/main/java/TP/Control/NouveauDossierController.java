package TP.Control;
import TP.IU.FicheSuivi;
import TP.IU.Menu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NouveauDossierController {

    @FXML
    private TextField ageField;

    @FXML
    private Button nouvelleFicheButton;

    @FXML
    private void initialize() {
        // Vous pouvez ajouter du code d'initialisation ici
    }

    // Méthode appelée lorsque le bouton "Nouvelle fiche de suivi" est cliqué
    @FXML
    private void handleNouvelleFicheSuivi() throws Exception{
        String numeroDossier = ageField.getText();
        ApplicationState.getInstance().setnumDossier(Integer.parseInt(numeroDossier));
        Stage stage = (Stage) nouvelleFicheButton.getScene().getWindow(); // Récupère la scène actuelle
        FicheSuivi page = new FicheSuivi(); // Crée la page Menu
        page.show(); // Affiche la page Menu
        stage.close();
        // Vous pouvez ajouter votre logique ici pour traiter le numéro de dossier
        System.out.println("Numéro de dossier : " + numeroDossier);
    }
}
