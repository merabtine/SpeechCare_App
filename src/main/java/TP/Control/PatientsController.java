package TP.Control;
import java.io.IOException;
import java.time.LocalDate;

import TP.Noyau.Adulte;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.Enfant;
import TP.Noyau.Patient;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PatientsController {


    @FXML
    private TableView<Patient> patientsTableView;

    @FXML
    private TableColumn<Patient, String> nomColumn;

    @FXML
    private TableColumn<Patient, String> prenomColumn;

    @FXML
    private TableColumn<Patient, LocalDate> dateNaissanceColumn;

    @FXML
    private TableColumn<Patient, String> lieuNaissanceColumn;

    @FXML
    private TableColumn<Patient, String> numTelColumn;

    @FXML
    private TableColumn<Patient, String> typeColumn;

    @FXML
    private ImageView home;

    private CompteOrthophoniste compteOrthophoniste;

    public PatientsController() {
        this.compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();
    }

    @FXML
    public void initialize() {
        

        // Configurer les colonnes du TableView
        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        prenomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
        dateNaissanceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDateNaissance()));
        lieuNaissanceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLieuNaissance()));
        numTelColumn.setCellValueFactory(cellData -> {
            Patient patient = cellData.getValue();
            if (patient instanceof Enfant) {
                return new SimpleStringProperty(((Enfant) patient).getNumTelParents());
            } else if (patient instanceof Adulte) {
                return new SimpleStringProperty(((Adulte) patient).getNumTel());
            }
            return null;
        });
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue() instanceof Enfant ? "Enfant" : "Adulte"));

        // Charger les patients depuis le compte orthophoniste
        patientsTableView.getItems().addAll(compteOrthophoniste.getPatients());
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
