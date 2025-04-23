package TP.Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import TP.Noyau.CategorieQRLEnfant;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.Enfant;
import TP.Noyau.QuestionAnamnese;
import TP.Noyau.SerializationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SauvegarderAnamneseEnfantController {

    @FXML
    private VBox questionsContainer;

    private Enfant enfant;
    private CompteOrthophoniste compteOrthophoniste;
    
    private List<TextField> questionFields;

    public void initialize() {
        // Placeholder for dynamic initialization if needed
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }

    public BorderPane loadFXMLAndSetData(Enfant enfant) throws IOException {
        this.enfant = enfant;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/AnamneseEnfant.fxml"));
        loader.setController(this);
        try {
            BorderPane root = loader.load();
            
            // Dynamically add questions to the VBox
            addQuestions();
            
            return root;
        } catch (IOException e) {
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
            throw e; 
        }
    }

    private void addQuestions() {
       
       this.compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();

        questionsContainer.getChildren().clear();
        
        List<QuestionAnamnese<CategorieQRLEnfant>> questions = compteOrthophoniste.getAnamneseEnfant().getQuestions();
        
        questionFields = new ArrayList<>();
        
        for (QuestionAnamnese<CategorieQRLEnfant> question : questions) {
            Text questionText = new Text(question.getEnonce());
            questionText.setFont(new Font(14.0));
            
            TextField questionField = new TextField();
            questionField.setStyle("-fx-text-fill: #c7034b; -fx-background-color: transparent; -fx-border-color: #5b3f5b; -fx-border-width: 0px 0px 2px 0px;");
            questionField.setPrefWidth(617.0);
            
            questionsContainer.getChildren().addAll(questionText, questionField);
            questionFields.add(questionField);
        }
        
        Button sauvegarderButton = new Button("Sauvegarder");
        sauvegarderButton.setOnAction(this::handleSauvegarderButtonAction);
        sauvegarderButton.setStyle("-fx-background-color:  #5b3f5b; -fx-text-fill: white;-fx-font-size: 14px;");

        HBox buttonContainer = new HBox(sauvegarderButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setStyle("-fx-padding: 10;");

        questionsContainer.getChildren().add(buttonContainer);
        SerializationUtil.serialize(this.compteOrthophoniste, "compte.ser");

    }

    @FXML
    private void handleSauvegarderButtonAction(ActionEvent event) {
        if (this.enfant != null) {
            Map<QuestionAnamnese<CategorieQRLEnfant>, String> reponsesAnamnese = enfant.getReponsesAnamnese();
            
            for (int i = 0; i < questionFields.size(); i++) {
                String response = questionFields.get(i).getText();
                CategorieQRLEnfant categorie = CategorieQRLEnfant.values()[i]; 
                QuestionAnamnese<CategorieQRLEnfant> question = new QuestionAnamnese<>(response, categorie);
                reponsesAnamnese.put(question, response);
            }
           // SerializationUtil.serialize(this.compteOrthophoniste, "compte.ser");
            SerializationUtil.serializePatient(this.enfant, "AnamnesePatient.ser");
            SerializationUtil.serializeEnfant(this.enfant, "AnamneseEnfant.ser");

            System.out.println("Anamnèse de l'enfant enregistrée avec les réponses.");
     try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/Test.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) questionsContainer.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to load Test.fxml");
            }
    
        }
    }
}
