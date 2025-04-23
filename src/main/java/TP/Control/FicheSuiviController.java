package TP.Control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import TP.IU.TestIU;
import TP.IU.Menu;
import TP.Noyau.*;
import javafx.scene.layout.Region;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FicheSuiviController implements Initializable{


    @FXML
    private VBox root;
    @FXML
    private BorderPane border;

    @FXML
    private ImageView home;

    private ArrayList<Objectif> listeObjectifs = new ArrayList<>();
    private FicheSuivi fiche=new FicheSuivi();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //VBox root = new VBox();
        // Création de la ScrollPane pour contenir les TitledPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxWidth(700);
        scrollPane.setStyle("-fx-background-color: transparent;");
        Label label = new Label("Ajouter des objectifs");
        label.setFont(Font.font("System Bold Italic", 30));
        label.setLayoutX(0);
        label.setLayoutY(0);
        root.getChildren().add(scrollPane);
        root.setAlignment(Pos.CENTER);

        // Création d'un VBox pour contenir les TitledPane
        VBox titledPaneContainer = new VBox();
        titledPaneContainer.setAlignment(Pos.CENTER);
        titledPaneContainer.setMaxWidth(700);
        scrollPane.setContent(titledPaneContainer);

        // Ajout du premier TitledPane
        titledPaneContainer.getChildren().add(label);
        titledPaneContainer.getChildren().add(createTitledPane("Ajouter un objectif",titledPaneContainer));
    }
    private AnchorPane createTitledPane(String title,VBox titledPaneContainer) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText(title);
    titledPane.setAnimated(false); // Définir l'animation sur false
    titledPane.setLayoutX(45.0);
    titledPane.setLayoutY(23.0);
    titledPane.setPrefHeight(189.0);
    titledPane.setPrefWidth(600.0);
        //titledPane.setCollapsible(false);

        AnchorPane contentPane = new AnchorPane();
        contentPane.setMinHeight(0.0);
    contentPane.setMinWidth(0.0);
    contentPane.setPrefHeight(171.0);
    contentPane.setPrefWidth(600.0);
        Label label1 = new Label("Nom de l'objectif");
        Label label2 = new Label("Terme de l'objectif");
        TextField textField = new TextField();
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Court terme", "Moyen terme", "Long terme");
        Button button = new Button("Valider");

        label1.setLayoutX(50);
        label1.setLayoutY(20);
        label2.setLayoutX(50);
        label2.setLayoutY(85);
        textField.setLayoutX(50);
        textField.setLayoutY(48);
        textField.setPrefWidth(572);
        textField.setStyle("-fx-background-color: white; -fx-border-color: #5b3f5b; -fx-background-radius: 5; -fx-border-radius: 5;");
        choiceBox.setLayoutX(50);
        choiceBox.setLayoutY(104);
        choiceBox.setPrefWidth(572);
        choiceBox.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: #5b3f5b;");
        button.setLayoutX(280);
        button.setLayoutY(141);
        button.setPrefWidth(102);
        button.setStyle("-fx-background-color: #5b3f5b; -fx-border-color: #5b3f5b; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
        button.setOnAction(event -> {
            // Ajouter ici le code pour la fonctionnalité de validation de l'objectif
            System.out.println("Valider " + titledPane.getText() + ": " + textField.getText());
            String selectedType = choiceBox.getValue();
            System.out.println(selectedType);
            Terme termeSelectionne = convertirEnTerme(selectedType);
            Objectif objectif = new Objectif(textField.getText(),termeSelectionne);
            listeObjectifs.add(objectif);
            System.out.println(listeObjectifs);
            fiche.setObjectifs(listeObjectifs);
            titledPaneContainer.getChildren().add(createTitledPane("Ajouter un objectif",titledPaneContainer));
        });

        contentPane.getChildren().addAll(label1, label2, textField, choiceBox, button);
        titledPane.setContent(contentPane);
        Dossier dossier=new Dossier(ApplicationState.getInstance().getnumDossier());
        dossier.NouvelleFiche(fiche);

        return contentPane;
    }

    private Terme convertirEnTerme(String termeString) {
        switch (termeString) {
            case "Court terme":
                return Terme.court_terme;
            case "Moyen terme":
                return Terme.moyen_terme;
            case "Long terme":
                return Terme.long_terme;
            default:
                throw new IllegalArgumentException("Terme inconnu: " + termeString);
        }
    }
    @FXML
    void handleButtonMenu(MouseEvent event) {
        try {
            Stage stage = (Stage) home.getScene().getWindow(); 
                Menu page = new Menu(); 
                page.show(); 
                stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
