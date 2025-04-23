package TP.Control;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.util.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import TP.IU.*;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.Consultation;
import TP.Noyau.DeserializationUtil;
import TP.Noyau.RDV;
import TP.Noyau.SerializationUtil;
import javafx.event.ActionEvent;

public class ProgrammerRDV {
    private CompteOrthophoniste orthophoniste;
    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField timeTextField;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField durationText;

    @FXML
    private Button submitButton;

    @FXML
    private ImageView home;

    @FXML
    public void initialize() throws Exception{
        // Initialize the choiceBox with some values
        choiceBox.getItems().addAll("Consultation", "Rendez-vous de suivi", "Atelier");

       /* Set<RDV> agenda = new HashSet<> (Set.of(
                new Consultation(LocalDate.of(2024, 5, 21),LocalTime.of(14,30),"Ghecham","Sarah",18),
                new Consultation(LocalDate.of(2024, 5, 27),LocalTime.of(14,30),"Ghecham","Sarah",18),
                new Consultation(LocalDate.of(2024, 5, 15),LocalTime.of(14,30),"Ghecham","Sarah",18)
        ));*/
        //agenda.add(new Consultation(LocalDate.of(2024, 5, 21),LocalTime.of(14,30),"Ghecham","Sarah",18));
        
        orthophoniste = ApplicationState.getInstance().getOrthophoniste();
        //orthophoniste.setAgenda(agenda);
        //SerializationUtil.serialize(orthophoniste, "compte.ser");
        orthophoniste.Afficher();
        //orthophoniste.ProgrammerConsultation(LocalDate.of(2024, 5, 22),LocalTime.of(14,30),"Ghecham","Sarah",18);
        //SerializationUtil.serialize(orthophoniste, "compte.ser");
        // Highlight the dates in the DatePicker
        highlightDatesInDatePicker();

        // Set the action for the submit button
        // submitButton.setOnAction(this::handleSubmitButtonAction);
    }

    @FXML
    private void highlightDatesInDatePicker() throws Exception{

        Set<LocalDate> rdvDates = orthophoniste.getAgenda().stream()
                .map(RDV::getDate)
                .collect(Collectors.toSet());

        Callback<DatePicker, DateCell> dayCellFactory = new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (rdvDates.contains(item)) {
                            this.setStyle("-fx-background-color: #5b3f5b; -fx-text-fill: white;");
                        }
                    }
                };
            }
        };

        datePicker.setDayCellFactory(dayCellFactory);
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) throws Exception{
        // Get the selected date
        java.time.LocalDate selectedDate = datePicker.getValue();

        
        // Get the selected time
        String timeText = timeTextField.getText();
        String dureeText = durationText.getText();
        LocalTime selectedTime1 = null;
        LocalTime selectedTime2 = null;
        try {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            selectedTime1 = LocalTime.parse(timeText, timeFormatter);
            selectedTime2 = LocalTime.parse(dureeText, timeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Heure invalide. Veuillez entrer une heure au format HH:mm.");
            return;
        }

        // Get the selected type of appointment
        String selectedType = choiceBox.getValue();


        ApplicationState.getInstance().setRDVDate(selectedDate);
        ApplicationState.getInstance().setRDVTime(selectedTime1);

        if(selectedType=="Consultation"){
            LocalTime heureSpec = LocalTime.of(1, 30);
            if(selectedTime2.isBefore(heureSpec)){
                Dialog<Void> dialog = new Dialog<>();
            dialog.initOwner(submitButton.getScene().getWindow());
            dialog.setTitle("Attention");

            // Créer une boîte de dialogue personnalisée avec un bouton "Réessayer"
            DialogPane dialogPane = new DialogPane() {
                {
                    getButtonTypes().clear(); // Supprimer les boutons par défaut
                    setContentText("Durée trop courte. Veuillez réessayer.");

                    ButtonType retryButtonType = new ButtonType("Réessayer", ButtonBar.ButtonData.OK_DONE);
                    getButtonTypes().add(retryButtonType);

                }
            };

            dialog.setDialogPane(dialogPane);
            dialog.showAndWait();
        }else{
            Stage stage = (Stage) submitButton.getScene().getWindow(); // Récupère la scène actuelle
            PConsultationIU page = new PConsultationIU(); // Crée la page Menu
            page.show(); // Affiche la page Menu
            stage.close();
        }
        }
        else if(selectedType=="Rendez-vous de suivi"){
            LocalTime heureSpecifique = LocalTime.of(1, 0);
            if(selectedTime2.isBefore(heureSpecifique)){
                Dialog<Void> dialog = new Dialog<>();
            dialog.initOwner(submitButton.getScene().getWindow());
            dialog.setTitle("Attention");

            // Créer une boîte de dialogue personnalisée avec un bouton "Réessayer"
            DialogPane dialogPane = new DialogPane() {
                {
                    getButtonTypes().clear(); // Supprimer les boutons par défaut
                    setContentText("Durée trop courte. Veuillez réessayer.");

                    ButtonType retryButtonType = new ButtonType("Réessayer", ButtonBar.ButtonData.OK_DONE);
                    getButtonTypes().add(retryButtonType);

                }
            };

            dialog.setDialogPane(dialogPane);
            dialog.showAndWait();
    
            }
            else{
                Stage stage = (Stage) submitButton.getScene().getWindow(); // Récupère la scène actuelle
                RDVSuivi page = new RDVSuivi(); // Crée la page Menu
                page.show(); // Affiche la page Menu
                stage.close();
            }
        }else if(selectedType=="Atelier"){
            LocalTime heureSpecifique = LocalTime.of(1, 0);
            if(selectedTime2.isBefore(heureSpecifique)){
                Dialog<Void> dialog = new Dialog<>();
            dialog.initOwner(submitButton.getScene().getWindow());
            dialog.setTitle("Attention");

            // Créer une boîte de dialogue personnalisée avec un bouton "Réessayer"
            DialogPane dialogPane = new DialogPane() {
                {
                    getButtonTypes().clear(); // Supprimer les boutons par défaut
                    setContentText("Durée trop courte. Veuillez réessayer.");

                    ButtonType retryButtonType = new ButtonType("Réessayer", ButtonBar.ButtonData.OK_DONE);
                    getButtonTypes().add(retryButtonType);

                }
            };

            dialog.setDialogPane(dialogPane);
            dialog.showAndWait();
    
            }
            else{
                Stage stage = (Stage) submitButton.getScene().getWindow(); // Récupère la scène actuelle
                AtelierIU page = new AtelierIU(); // Crée la page Menu
                page.show(); // Affiche la page Menu
                stage.close();
            }
        }
        

        // Print the selected values
        System.out.println("Date sélectionnée: " + selectedDate);
        System.out.println("Heure sélectionnée: " + selectedTime1);
        System.out.println("Type de rendez-vous: " + selectedType);
        System.out.println("Duree sélectionnée: " + selectedTime2);
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

