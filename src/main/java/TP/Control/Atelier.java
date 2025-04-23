package TP.Control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import TP.IU.ConsulterRDVIU;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.SerializationUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Atelier {
    @FXML
    private ScrollPane rightScrollPane;

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Button suivantButton1;

    @FXML
    private Button submitButton;

    @FXML
    private TextField numDossier;

    private Set<Integer> numDossiers = new HashSet<>();
    private CompteOrthophoniste compteOrthophoniste;


    private double currentY = 243.0; // Position Y actuelle pour les nouveaux éléments

    @FXML
    public void initialize() {
        if (!numDossier.getText().isEmpty()) {
            numDossiers.add(Integer.parseInt(numDossier.getText()));
        }
        suivantButton1.setOnAction(event -> {
            // Créer et ajouter dynamiquement un champ texte
            TextField textField = new TextField();
            textField.setStyle(numDossier.getStyle());
            textField.setLayoutX(263); // Position X
            textField.setLayoutY(currentY); // Position Y
            textField.setPromptText("Numero de dossier");
            textField.setFont(numDossier.getFont());
            anchorPane.getChildren().add(textField);
           
            // Mettre à jour la position Y pour les prochains éléments
            currentY += 70;
        });

    }
    @FXML
    public void ProgrammerAtelier() throws Exception{
        String theme="Theme";

        for (Node node : anchorPane.getChildren()) {
            if (node instanceof TextField) {
                TextField textField1 = (TextField) node;
                String texte = textField1.getText();
                System.out.println("Texte du champ texte : " + texte);
                try {
                    int numeroDossier = Integer.parseInt(texte);
                    numDossiers.add(numeroDossier);
                } catch (NumberFormatException e) {
                    System.out.println(texte);
                    theme=texte;
                }
                
            }
        }
        System.out.println(numDossiers);
        this.compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();
        if (compteOrthophoniste == null) {
            return;
        }
        LocalDate dateRDV=ApplicationState.getInstance().getRDVDate();
            LocalTime heureRDV=ApplicationState.getInstance().getRDVTime();
            System.out.println(dateRDV);
            System.out.println(heureRDV);
        compteOrthophoniste.ProgrammerAtelier(dateRDV, heureRDV,numDossiers,theme);
        SerializationUtil.serialize(compteOrthophoniste, "compte.ser");
        Stage stage = (Stage) submitButton.getScene().getWindow(); 
        ConsulterRDVIU page = new ConsulterRDVIU(); 
        page.show(); 
        stage.close();
    }
}
