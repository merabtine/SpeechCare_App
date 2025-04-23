package TP.Control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import TP.IU.ConsultationIU;
import TP.IU.TestIU;
import TP.Noyau.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableCell;


public class ConsulterRDV implements Initializable{
     @FXML
    private TableView<RDV> RDVTableView;

    @FXML
    private TableColumn<RDV,String> TypeColumn;

    @FXML
    private TableColumn<RDV,LocalDate> DateColumn;

    @FXML
    private TableColumn<RDV,LocalTime> HeureColumn;

    @FXML
    private TableColumn<RDV, Void> EntamerColumn;

    @FXML
    private ImageView home;

    private CompteOrthophoniste compteOrthophoniste;

   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        

        // Configurer les colonnes du TableView
        TypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        DateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDate()));
        HeureColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getHeure()));

        EntamerColumn.setCellFactory(tc -> new TableCell<>() {
            private final Button button = new Button("Entamer");
            {
                button.setPrefWidth(112.0);
                button.setPrefHeight(25.0);
                button.setStyle("-fx-background-color: #5b3f5b; -fx-text-fill: #fffdfd;");
            }
    
            {
                // Ajouter un gestionnaire d'événements pour le bouton
                button.setOnAction(event -> {
    RDV rdv = getTableView().getItems().get(getIndex());
    if (rdv.getType().equals("Consultation")) {
        // Créer une boîte de dialogue de confirmation
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Confirmer l'entame de la séance pour le RDV de consultation");
        confirmation.setContentText("Voulez-vous entamer la séance pour ce RDV de consultation ?");

        // Ajouter les boutons OK et Annuler à la boîte de dialogue
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmation.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOK) {
            // Charger la page d'anamnèse ici
            try{ Stage stage = (Stage) button.getScene().getWindow(); 
                ConsultationIU page = new ConsultationIU(); 
                page.show(); 
                stage.close();}
                catch(Exception e){
                    System.out.println("Anamnese");
                }
           
        }
    } else if (rdv.getType().equals("SuiviRDV")) {
        // Créer une boîte de dialogue de confirmation
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Confirmer l'entame de la séance pour le RDV de suivi");
        confirmation.setContentText("Voulez-vous entamer la séance pour ce RDV de suivi ?");

        // Ajouter les boutons OK et Annuler à la boîte de dialogue
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmation.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOK) {
            // Charger la page d'anamnèse ici
            try{ Stage stage = (Stage) button.getScene().getWindow(); 
                TestIU page = new TestIU(); 
                page.show(); 
                stage.close();}
                catch(Exception e){
                    System.out.println("Tests");
                }
           
        }
    }else if (rdv.getType().equals("Atelier")) {
        // Créer une boîte de dialogue de confirmation
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation");
        confirmation.setHeaderText("Confirmer l'entame de la séance Atelier");
        confirmation.setContentText("Voulez-vous entamer la séance Atelier");

        // Ajouter les boutons OK et Annuler à la boîte de dialogue
        ButtonType buttonTypeOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmation.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeOK) {
            // Charger la page d'anamnèse ici
            try{ Stage stage = (Stage) button.getScene().getWindow(); 
                TestIU page = new TestIU(); 
                page.show(); 
                stage.close();}
                catch(Exception e){
                    System.out.println("Tests");
                }
           
        }
    }
});

            }
    
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null); // Ne rien afficher si la cellule est vide
                } else {
                    setGraphic(button); // Afficher le bouton dans la cellule
                }
            }
        });


        this.compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();
        
        Set<RDV> rdvsSet = compteOrthophoniste.getAgenda();
        ObservableList<RDV> rdvsList = FXCollections.observableArrayList(rdvsSet);
        System.out.println("Nombre de RDVs chargés : " + rdvsList.size());
        
        RDVTableView.setItems(rdvsList);
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
