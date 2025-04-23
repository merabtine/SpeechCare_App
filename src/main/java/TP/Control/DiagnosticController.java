package TP.Control;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import TP.Noyau.CategorieTrouble;
import TP.Noyau.Diagnostic;
import TP.Noyau.Trouble;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DiagnosticController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private BorderPane border;

    private Set<Trouble> troubles = new HashSet<>();
    private Diagnostic diagnostic = new Diagnostic(troubles);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxWidth(700);
        scrollPane.setStyle("-fx-background-color: transparent;");
        Label label = new Label("Ajouter des troubles");
        label.setFont(Font.font("System Bold Italic", 30));
        label.setLayoutX(0);
        label.setLayoutY(0);
        root.getChildren().add(scrollPane);
        root.setAlignment(Pos.CENTER);

        VBox titledPaneContainer = new VBox();
        titledPaneContainer.setAlignment(Pos.CENTER);
        titledPaneContainer.setMaxWidth(700);
        scrollPane.setContent(titledPaneContainer);

        titledPaneContainer.getChildren().add(label);
        titledPaneContainer.getChildren().add(createTitledPane("Ajouter un trouble", titledPaneContainer));
    }

    private AnchorPane createTitledPane(String title, VBox titledPaneContainer) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText(title);
        titledPane.setAnimated(false);
        titledPane.setLayoutX(45.0);
        titledPane.setLayoutY(23.0);
        titledPane.setPrefHeight(189.0);
        titledPane.setPrefWidth(600.0);

        AnchorPane contentPane = new AnchorPane();
        contentPane.setMinHeight(0.0);
        contentPane.setMinWidth(0.0);
        contentPane.setPrefHeight(171.0);
        contentPane.setPrefWidth(600.0);

        Label label1 = new Label("Nom du trouble");
        Label label2 = new Label("Catégorie du trouble");
        TextField textField = new TextField();
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Déglutition", "Neuro-développementaux", "Cognitifs");
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
            String selectedType = choiceBox.getValue();
            if (selectedType != null && !textField.getText().isEmpty()) {
                CategorieTrouble troubleSelectionne = convertirEnTrouble(selectedType);
                Trouble trouble = new Trouble(textField.getText(), troubleSelectionne);
                troubles.add(trouble);
                System.out.println("Troubles actuels : " + troubles);
                diagnostic.setTroubles(troubles);
                titledPaneContainer.getChildren().add(createTitledPane("Ajouter un trouble", titledPaneContainer));
            } else {
                try{
                    System.out.println("Veuillez remplir tous les champs.");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/TP/IU/ProjetTherapeutique.fxml"));
                    Parent root = loader.load();
                    Scene scene = button.getScene();
                    Stage stage = (Stage) scene.getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                }catch(IOException e) {
                    System.err.println("Error loading SauvegarderAnamneseEnfant.fxml: " + e.getMessage());
                    e.printStackTrace();

                }
            }
        });

        contentPane.getChildren().addAll(label1, label2, textField, choiceBox, button);
        titledPane.setContent(contentPane);

        return contentPane;
    }

    private CategorieTrouble convertirEnTrouble(String troubleString) {
        switch (troubleString) {
            case "Déglutition":
                return CategorieTrouble.Deglutition;
            case "Neuro-développementaux":
                return CategorieTrouble.NeuroDev;
            case "Cognitifs":
                return CategorieTrouble.Cognitifs;
            default:
                throw new IllegalArgumentException("Trouble inconnu: " + troubleString);
        }
    }
}
