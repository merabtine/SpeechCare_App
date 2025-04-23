package TP.Control;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import TP.IU.TestIU;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.CompteRendu;
import TP.Noyau.QCU;
import TP.Noyau.TestQuestion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class QCUScore implements Initializable{
    @FXML
    private Label questionLabel;

    @FXML
    private AnchorPane questionsContainer;

    @FXML
    private ImageView home;
    
    private List<QCU> questions ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {{
            CompteRendu compteRendu = new CompteRendu();
            CompteOrthophoniste compte=ApplicationState.getInstance().getOrthophoniste();
            TestQuestion test=compte.getTestQuestion();
            questions =test.getQCU();
             int[] SelectedIndex=ApplicationState.getInstance().getSelectedReponses();
            // Pour chaque question
            double currentY =0;
            for (int i = 0; i < questions.size(); i++) {
                QCU question = questions.get(i);
        
                // Afficher la question
                Label questionLabel = new Label(question.getEnonce());
                questionLabel.setLayoutY(250 + i * 170); // Position Y de la question
                questionLabel.setLayoutX(120); // Même valeur que le layoutX dans le code FXML
                questionLabel.setFont(Font.font("System Bold Italic", 15)); // Définit la police et la taille de la police
                questionLabel.setUnderline(true);
                questionsContainer.getChildren().add(questionLabel);
        
                double currentX = 150; // Position X initiale des boutons radio
                currentY = 285 + i * 170; // Position Y initiale des boutons radio
                for (int j = 0; j < question.getOptionsReponses().size(); j++) {
                    int x=SelectedIndex[i];
                    String reponse = question.getOptionsReponses().get(j);
                    Label radioButton = new Label(reponse);
                    radioButton.setLayoutX(currentX);
                    radioButton.setLayoutY(currentY);
                    radioButton.setFont(Font.font("System Bold Italic", 14)); // Police et taille de police
                    Circle cercle = new Circle();
                    cercle.setRadius(2); // Rayon du cercle
                    cercle.setFill(Color.TRANSPARENT); // Remplissage transparent par défaut
                    cercle.setStroke(Color.BLACK); // Bordure noire
                    cercle.setLayoutX(currentX - 15); // Position X du cercle
                    cercle.setLayoutY(currentY + 13); // Position Y du cercle

                    // Ajouter le cercle à votre conteneur
                    questionsContainer.getChildren().add(cercle);
                    questionsContainer.getChildren().add(radioButton);
        
                    // Si la réponse est la bonne réponse, changer sa couleur
                    if (j+1 == question.getBonneReponseIndex()) {
                        radioButton.setTextFill(Color.GREEN); // Couleur de la bonne réponse
                    }
                    if (j+1 == x && j+1!=question.getBonneReponseIndex()) {
                        radioButton.setTextFill(Color.RED); // Couleur de la bonne réponse
                    }

                    
        
                    currentY += 24; // Espacement vertical entre les réponses
                }

                Label Score = new Label("Score");
                Score.setLayoutY(currentY+5); // Position Y de la question
                Score.setLayoutX(currentX); // Position X de la question
                Score.setFont(Font.font("System Bold", 16)); // Police et taille de police
                Score.setTextFill(Color.web("#166059")); // Couleur du texte
                questionsContainer.getChildren().add(Score);
                currentY+=30;

                Spinner<Integer> spinner = new Spinner<>(1, 10, 1); // Plage de valeurs de 1 à 10, valeur initiale 1
                spinner.setLayoutX(currentX);
                spinner.setLayoutY(currentY);
                spinner.setStyle("-fx-background-color: transparent; -fx-border-color: #62ffab; -fx-background-radius: 5; -fx-border-radius: 5;");

            // Ajouter le Spinner à votre conteneur
                questionsContainer.getChildren().add(spinner);

                // Gestionnaire d'événements pour la modification de la valeur dans le Spinner
                
                spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                    if (newValue < 1) {
                        spinner.getValueFactory().setValue(1); // Réglez la valeur sur 1 si elle est inférieure à 1
                    } else if (newValue > 10) {
                        spinner.getValueFactory().setValue(10); // Réglez la valeur sur 10 si elle est supérieure à 10
                    }
                    final int score=spinner.getValue();
                    compteRendu.ajouterScore(question, score);
                });
             
                System.out.println("Score"+compteRendu.getScore(question));
            }


            Button validerButton = new Button("Passer au diagnostic");
        validerButton.setLayoutX(250); // Position X
        validerButton.setLayoutY(currentY+50); // Position Y
        validerButton.setPrefWidth(147); // Largeur préférée
        validerButton.setPrefHeight(25); // Hauteur préférée
        validerButton.setStyle("-fx-background-color:  #ede5ff; -fx-text-fill:  black;"); // Style CSS pour le fond et la couleur du texte

        // Définir l'action du bouton
        validerButton.setOnAction(event -> {
try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/Diagnostic.fxml"));
    Parent root = loader.load();
    Stage stage = (Stage) questionsContainer.getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load ListPatients.fxml");
        }
    
            
        });

        // Ajouter le bouton à votre conteneur
        questionsContainer.getChildren().add(validerButton);
            

            Rectangle espaceBas = new Rectangle();
        espaceBas.setWidth(1); // Largeur arbitraire
        espaceBas.setHeight(50); // Hauteur de l'espace en bas de la page
        espaceBas.setFill(Color.TRANSPARENT); // Remplissage transparent

        // Positionner le Rectangle en bas de la page
        espaceBas.setLayoutY(currentY + 60); // Ajoutez un espace supplémentaire pour le bouton

        // Ajouter le Rectangle à votre conteneur
        questionsContainer.getChildren().add(espaceBas);
            
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
}
