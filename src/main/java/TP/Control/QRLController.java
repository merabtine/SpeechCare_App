package TP.Control;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import TP.IU.TestIU;
import TP.Noyau.Adulte;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.DeserializationUtil;
import TP.Noyau.Enfant;
import TP.Noyau.Patient;
import TP.Noyau.QRL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class QRLController {

    @FXML
    private VBox questionsContainer;

    @FXML
    private ImageView home;

    private CompteOrthophoniste compteOrthophoniste;
    private List<QRL> questionsQRL;
    private Patient patient; 

    public void initialize() {
        this.compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();
        afficherQuestionsQRL();
    }

    private void afficherQuestionsQRL() {
        questionsQRL = compteOrthophoniste.getQrlListe();
        for (QRL qrl : questionsQRL) {
            VBox questionBox = new VBox(5);
            questionBox.setPadding(new Insets(10, 0, 10, 0));

            Text questionText = new Text("Question: " + qrl.getEnonce());
            questionText.setFill(javafx.scene.paint.Color.web("#000000"));
            questionText.setStyle("-fx-font-size: 16px;");

            TextField responseField = new TextField();
            responseField.setPrefWidth(650);
            responseField.setStyle("-fx-text-fill: #d14995;-fx-font-size: 16px; -fx-background-color: transparent; -fx-border-color: #4b48c6; -fx-border-width: 0px 0px 3px 0px;");

            TextField scoreField = new TextField();
            scoreField.setPrefWidth(50);
            scoreField.setStyle("-fx-text-fill: #d14995; -fx-font-size: 14px;");

            HBox responseBox = new HBox(10);
            responseBox.getChildren().addAll(responseField, scoreField);

            questionBox.getChildren().addAll(questionText, responseBox);
            questionsContainer.getChildren().add(questionBox);
        }
        Button sauvegarderButton = new Button("Sauvegarder");
        sauvegarderButton.setOnAction(event -> handleSauvegarder());
        sauvegarderButton.setStyle("-fx-background-color:  #4b48c6; -fx-text-fill: white;-fx-font-size: 14px;");

        HBox buttonContainer = new HBox(sauvegarderButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setStyle("-fx-padding: 10;");

        questionsContainer.getChildren().add(buttonContainer);
    }

    private void handleSauvegarder() {
        this.patient = DeserializationUtil.deserializePatient("AnamnesePatient.ser");
    
        if (this.patient instanceof Enfant) {
            this.patient = DeserializationUtil.deserializePatient("AnamneseEnfant.ser");
        } else if(this.patient instanceof Adulte) {
            this.patient = DeserializationUtil.deserializePatient("AnamneseAdulte.ser");
        }
    
        if (this.patient == null) {
            System.out.println("Patient non trouvé.");
            return;
        }
    
        ArrayList<QRL> reponsesQRL = new ArrayList<>();
        boolean hasInvalidScore = false;
    
        for (int i = 0; i < questionsQRL.size(); i++) {
            Node node = questionsContainer.getChildren().get(i);
    
            if (!(node instanceof VBox)) {
                System.out.println("Node is not a VBox at index " + i);
                continue;
            }
    
            VBox questionBox = (VBox) node;
            Node childNode = questionBox.getChildren().get(1);
    
            if (!(childNode instanceof HBox)) {
                System.out.println("Child node is not a HBox at index " + i);
                continue;
            }
    
            HBox responseBox = (HBox) childNode;
    
            Node responseFieldNode = responseBox.getChildren().get(0);
            if (!(responseFieldNode instanceof TextField)) {
                System.out.println("Response field node is not a TextField at index " + i);
                continue;
            }
            TextField responseField = (TextField) responseFieldNode;
    
            Node scoreFieldNode = responseBox.getChildren().get(1);
            if (!(scoreFieldNode instanceof TextField)) {
                System.out.println("Score field node is not a TextField at index " + i);
                continue;
            }
            TextField scoreField = (TextField) scoreFieldNode;
    
            String response = responseField.getText();
            String scoreStr = scoreField.getText();
            int score = 0;
            try {
                score = Integer.parseInt(scoreStr);
                if (score < 1 || score > 10) {
                    throw new InvalidScoreException("Le score doit être une valeur entre 1 et 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur de format de score pour la question " + (i + 1));
                showAlert("Score invalide", "Le score doit être un nombre valide entre 1 et 10.");
                hasInvalidScore = true;
                break; // Exit the loop if the score is invalid
            } catch (InvalidScoreException e) {
                System.out.println(e.getMessage());
                showAlert("Score invalide", "Le score doit être un nombre valide entre 1 et 10.");
                hasInvalidScore = true;
                break; // Exit the loop if the score is invalid
            }
    
            QRL qrl = new QRL(questionsQRL.get(i).getEnonce(), response, score);
            reponsesQRL.add(qrl);
        }
    
        if (hasInvalidScore) {
            return; // Stop processing if any invalid score was found
        }
    
        patient.setReponsesQRL(reponsesQRL);
    
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/Diagnostic.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) questionsContainer.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
    
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load Diagnostic.fxml");
        }
    }
    
    // Classe d'exception personnalisée pour un score invalide
    public static class InvalidScoreException extends Exception {
        public InvalidScoreException(String message) {
            super(message);
        }
    }
    @FXML
    void handleButtonMenu(MouseEvent event) {
        try {
            Stage stage = (Stage) home.getScene().getWindow(); 
                TestIU page = new TestIU(); 
                page.show(); 
                stage.close();

        } catch (Exception e) {
            e.printStackTrace();
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
