


    package TP.Control;

    import TP.IU.Dossier;
import javafx.event.Event;
import javafx.fxml.FXML;
    import javafx.scene.control.Button;
    import javafx.scene.control.TextArea;
    import javafx.scene.image.ImageView;
    import javafx.scene.text.Text;
    import javafx.stage.Stage;
    
    public class ProjetTherapeutiqueController {

        @FXML
        private Text projectText;
    
        @FXML
        private Text therapeuticText;
    
        @FXML
        private ImageView logoImage;
    
        @FXML
        private Text instructionText1;
    
        @FXML
        private Text instructionText2;
    
        @FXML
        private TextArea procedureTextArea;
    
        @FXML
        private Button confirmButton;
    
        @FXML
        private void initialize() {
            // Initialize your UI components if needed
        }
    
        @FXML
        private void handleConfirmButtonAction() throws Exception{
            Stage stage = (Stage) confirmButton.getScene().getWindow(); 
            Dossier page = new Dossier(); 
            page.show(); 
            stage.close();
        }
    }
    
