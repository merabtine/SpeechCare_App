package TP.Control;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import TP.IU.Acceuil;
import TP.IU.QCUIScore;
import TP.Noyau.*;

public class QCUController implements Initializable {

    @FXML
    private Label questionLabel;

    @FXML
    private AnchorPane questionsContainer;
    
    //TestQuestion test=new TestQuestion();
    private List<QCU> questions ;
     // Supposons que vous avez une liste de questions

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CompteOrthophoniste compte=ApplicationState.getInstance().getOrthophoniste();
        TestQuestion test=compte.getTestQuestion();
        questions =test.getQCU();
        //compte.setTestQuestion(test);
        //SerializationUtil.serialize(compte, "compte.ser");
        //ApplicationState.getInstance().setOrthophoniste(compte);
        double currentY = 250; // Position Y initiale
        double currentYRadioButton = 285;
        int[] selectedAnswersIndices = new int[questions.size()];
        for (int i = 0; i < questions.size(); i++) {
            /// Créer un Label pour la question
        QCU question = questions.get(i);
        Label questionLabel1 = new Label(question.getEnonce());
        questionLabel1.setLayoutY(currentY);
        questionLabel1.setLayoutX(120); // Même valeur que le layoutX dans le code FXML
        questionLabel1.setFont(Font.font("System Bold Italic", 15)); // Définit la police et la taille de la police
        questionLabel1.setUnderline(true); // Souligne le texte
        //questionLabel1.setTextFill(Color.web("#5b3f5b")); // Définit la couleur du texte

        // Ajouter le Label à votre conteneur
        questionsContainer.getChildren().add(questionLabel1);

        // Créer un groupe de ToggleButton pour les réponses
        ToggleGroup toggleGroup = new ToggleGroup();
        double currentX = 150; // Position X initiale, même valeur que le layoutX dans le code FXML // Position Y initiale pour les RadioButtons
        for (int j = 0; j < question.getOptionsReponses().size(); j++) {
            final int index = j;
            final int cpt=i;
            RadioButton radioButton = new RadioButton(question.getOptionsReponses().get(j));
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setLayoutX(currentX);
            radioButton.setLayoutY(currentYRadioButton);
            radioButton.setFont(Font.font("System Bold", 14)); // Définit la couleur du texte // Espacement vertical entre les réponses
            radioButton.setOnAction(event -> {
                selectedAnswersIndices[cpt] = index+1; 
                System.out.println(cpt);
                System.out.println(index+1);// Met à jour l'indice de la réponse sélectionnée pour cette question
            });
            questionsContainer.getChildren().add(radioButton);
            currentYRadioButton += 24; // Espacement vertical entre les RadioButtons
        }
    currentY+=120;
    currentYRadioButton +=48;
            }
        // Créer le bouton "Valider"
        ApplicationState.getInstance().setSelectedReponses(selectedAnswersIndices);
        Button validerButton = new Button("Voir bonnes réponses");
        validerButton.setLayoutX(250); // Position X
        validerButton.setLayoutY(currentY+10); // Position Y
        validerButton.setPrefWidth(147); // Largeur préférée
        validerButton.setPrefHeight(25); // Hauteur préférée
        validerButton.setStyle("-fx-background-color:  #ede5ff; -fx-text-fill:  black;"); // Style CSS pour le fond et la couleur du texte

        // Définir l'action du bouton
        validerButton.setOnAction(event -> {
            Stage stage = (Stage) validerButton.getScene().getWindow(); // Récupère la scène actuelle
            try {
                QCUIScore page = new QCUIScore();
                page.show(); // Affiche la page Menu
                stage.close();
            } catch (Exception e) {
                // Handle the exception here
                e.printStackTrace(); // This is a simple way to print the stack trace of the exception
            }
        });

        // Ajouter le bouton à votre conteneur
        questionsContainer.getChildren().add(validerButton);


        Rectangle espaceBas = new Rectangle();
        espaceBas.setWidth(1); // Largeur arbitraire
        espaceBas.setHeight(50); // Hauteur de l'espace en bas de la page
        espaceBas.setFill(Color.TRANSPARENT); // Remplissage transparent

        // Positionner le Rectangle en bas de la page
        espaceBas.setLayoutY(currentY + 20); // Ajoutez un espace supplémentaire pour le bouton

        // Ajouter le Rectangle à votre conteneur
        questionsContainer.getChildren().add(espaceBas);
        }

        /*public void initialize(URL url, ResourceBundle resourceBundle) {{
            // Pour chaque question
            for (int i = 0; i < questions.size(); i++) {
                QCU question = questions.get(i);
        
                // Afficher la question
                Label questionLabel = new Label(question.getEnonce());
                questionLabel.setLayoutY(100 + i * 120); // Position Y de la question
                questionLabel.setLayoutX(70); // Position X de la question
                questionLabel.setFont(Font.font("System Bold Italic", 19)); // Police et taille de police
                questionLabel.setTextFill(Color.web("#5b3f5b")); // Couleur du texte
                questionsContainer.getChildren().add(questionLabel);
        
                double currentX = 99; // Position X initiale des boutons radio
                double currentY = 135 + i * 120; // Position Y initiale des boutons radio
                for (int j = 0; j < question.getOptionsReponses().size(); j++) {
                    String reponse = question.getOptionsReponses().get(j);
                    Label radioButton = new Label(reponse);
                    radioButton.setLayoutX(currentX);
                    radioButton.setLayoutY(currentY);
                    radioButton.setFont(Font.font("System Bold Italic", 16)); // Police et taille de police
                    Circle cercle = new Circle();
            cercle.setRadius(5); // Rayon du cercle
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
        
        
                    currentY += 24; // Espacement vertical entre les réponses
                }
            }
        }*/
        
        }

