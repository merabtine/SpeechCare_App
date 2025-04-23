package TP.Control;
import java.io.IOException;

import TP.Noyau.CategorieQRLAdulte;
import TP.Noyau.CategorieQRLEnfant;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.SerializationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AjouterQuestionAnamneseController {

    @FXML
    private RadioButton enfantRadioButton;

    @FXML
    private RadioButton adulteRadioButton;

    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    @FXML
    private TextField questionTextField1;

    @FXML
    private Button confirmer;

    @FXML
    private ImageView home;

    private CompteOrthophoniste compteOrthophoniste;

    private final String[] categoriesEnfant = {
            "Structure Familiale",
            "Dynamique Familiale",
            "Antecedents Familiaux",
            "Conditions Natales",
            "Developpement Psychomoteur",
            "Developpement Langagier",
            "Caractere et Comportement"
    };

    private final String[] categoriesAdulte = {
            "Historique Maladie",
            "Suivi Médical"
    };

    

    @FXML
    public void initialize() {
        ToggleGroup toggleGroup = new ToggleGroup();
        enfantRadioButton.setToggleGroup(toggleGroup);
        adulteRadioButton.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> updateCategoryChoices());

        enfantRadioButton.setSelected(true);  // Default selection
        updateCategoryChoices(); // Initialize category choices
    }

    private void updateCategoryChoices() {
        categoryChoiceBox.getItems().clear();
        if (enfantRadioButton.isSelected()) {
            categoryChoiceBox.getItems().addAll(categoriesEnfant);
        } else if (adulteRadioButton.isSelected()) {
            categoryChoiceBox.getItems().addAll(categoriesAdulte);
        }
    }

    @FXML
    void handleConfirmerButton(ActionEvent event) {
        String categorie = categoryChoiceBox.getValue();
        String enonce = questionTextField1.getText();
        this.compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();

        if (enonce != null && !enonce.isEmpty() && categorie != null && !categorie.isEmpty()) {
            if (enfantRadioButton.isSelected()) {
                CategorieQRLEnfant categorieQRLEnfant = CategorieQRLEnfant.valueOf(categorie.toUpperCase().replace(" ", "_"));
                compteOrthophoniste.ajouterQuestionAnamneseEnfant(enonce, categorieQRLEnfant);
                System.out.println("Question ajoutée à l'anamnèse de l'enfant.");
            } else if (adulteRadioButton.isSelected()) {
                CategorieQRLAdulte categorieQRLAdulte = CategorieQRLAdulte.valueOf(categorie.toUpperCase().replace(" ", "_"));
                compteOrthophoniste.ajouterQuestionAnamneseAdulte(enonce, categorieQRLAdulte);
                System.out.println("Question ajoutée à l'anamnèse de l'adulte.");
            }

            // Clear fields after adding question
            questionTextField1.clear();
            categoryChoiceBox.getSelectionModel().clearSelection();

            // Serialize the orthophoniste object
            SerializationUtil.serialize(this.compteOrthophoniste, "compte.ser");
            System.out.println("Compte orthophoniste serialized successfully.");
             compteOrthophoniste.afficherAnamneseEnfant();
        } else {
            System.out.println("Veuillez remplir tous les champs.");
        }
    }
     @FXML
    void handleButtonMenu(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/Menu.fxml"));
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