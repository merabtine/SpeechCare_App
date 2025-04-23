package TP.Control;
import TP.IU.Acceuil;
import TP.IU.Menu;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.DeserializationUtil;
import TP.Noyau.SerializationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class CreerCompteController{
    @FXML
    private TextField Nom;
    @FXML
    private TextField Prenom;
    @FXML
    private TextField Adresse;
    @FXML
    private TextField NumTel;
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;

    @FXML
    private Button button;

    @FXML
    void Compte(ActionEvent event) throws Exception{
        String nomText = Nom.getText();
        String prenomText = Prenom.getText();
        String AdresseText = Adresse.getText();
        String NumTelText = NumTel.getText();
        String EmailText = Email.getText();
        String PasswordText=Password.getText();
        CompteOrthophoniste compte=new CompteOrthophoniste(nomText,prenomText,AdresseText, NumTelText, EmailText, PasswordText);
        SerializationUtil.serialize(compte, "compte.ser");
        ApplicationState.getInstance().setOrthophoniste(compte);

        // Redirection vers la page Menu
        Stage stage = (Stage) button.getScene().getWindow(); // Récupère la scène actuelle
        Menu page = new Menu(); // Crée la page Menu
        page.show(); // Affiche la page Menu
        stage.close();
    }
     @FXML
     void Connexion(ActionEvent event) throws Exception{

        Stage stage = (Stage) button.getScene().getWindow(); // Récupère la scène actuelle
        Acceuil page = new Acceuil(); // Crée la page Menu
        page.show(); // Affiche la page Menu
        stage.close();

     }
}