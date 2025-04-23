package TP.Control;

import java.io.IOException;

import TP.IU.ConsulterRDVIU;
import TP.IU.ProgrammerRDVIU;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button buttonPatients;
    
    @FXML
    private Button buttonDossiers;
    
    @FXML
    private Button buttonRendezVous;
    
    @FXML
    private Button buttonTests;

    @FXML
    private Button AjoutQstAnamnese;
    
    @FXML
    private Text textPatients;
    
    @FXML
    private Text textDossiers;
    
    @FXML
    private Text textRendezVous;
    
    @FXML
    private Text textTests;
    
    @FXML
    private ImageView imgPatients;
    
    @FXML
    private ImageView imgDossiers;
    
    @FXML
    private ImageView imgRendezVous;
    
    @FXML
    private ImageView imgTests;

    @FXML
    private Text mainText;

    @FXML
    private ImageView imgOrtho;
    
    @FXML
    private ImageView imgUser;
    
    @FXML
    private ImageView imgFolder;
    
    @FXML
    private ImageView imgRdv;
    
    @FXML
    private ImageView imgQuest;
    
    @FXML
    private ImageView imgAgenda;
    
    @FXML
    private ImageView imgTest;

    @FXML
    private ImageView imgLogo1;
    
    @FXML
    private ImageView imgLogo2;
    
    @FXML
    private ImageView imgLogo3;
    
    @FXML
    private ImageView imgLogo4;

    @FXML
    private Button ProgrammerButton;


    @FXML
    void RDV(ActionEvent event) throws Exception{
        Stage stage = (Stage) ProgrammerButton.getScene().getWindow(); 
        ProgrammerRDVIU page = new ProgrammerRDVIU(); 
        page.show(); 
        stage.close();
    }

    
  
    @FXML
    void handleButtonPatients(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/ListePatients.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) buttonPatients.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load ListPatients.fxml");
        }
    }

    @FXML
    void handleButtonDossiers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/ListePatients.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) buttonPatients.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load ListPatients.fxml");
        }
    }
    @FXML
    void handleButtonRendezVous() throws Exception{
        try {
            Stage stage = (Stage) buttonRendezVous.getScene().getWindow(); 
            ConsulterRDVIU page = new ConsulterRDVIU(); 
            page.show(); 
            stage.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load ListPatients.fxml");
        }   
     }
    @FXML
    void handleButtonTests() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/ListePatients.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) buttonPatients.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load ListPatients.fxml");
        }
    }
    @FXML
    void handleButtonAnamnese() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/AjouterQuestionAnamnese.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) buttonPatients.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load ListPatients.fxml");
        }
    }
}

