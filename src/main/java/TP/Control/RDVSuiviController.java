package TP.Control;

import java.time.LocalDate;
import java.time.LocalTime;

import TP.IU.ConsulterRDVIU;
import TP.Noyau.CompteOrthophoniste;
import TP.Noyau.SerializationUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RDVSuiviController {
    @FXML
    private TextField numDossier;
    
    @FXML
    private TextField Presentiel;
    
    @FXML
    private Button suivantButton;

    private CompteOrthophoniste compteOrthophoniste;
    
    
    @FXML
    private void handleSuivantButton() throws Exception{
        int num=Integer.parseInt(numDossier.getText());
        boolean presence = Presentiel.getText().equals("Presentiel");
        System.out.println(num);
        System.out.println(Presentiel.getText());
        System.out.println(presence);
        this.compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();
        if (compteOrthophoniste == null) {
            return;
        }
        LocalDate dateRDV=ApplicationState.getInstance().getRDVDate();
            LocalTime heureRDV=ApplicationState.getInstance().getRDVTime();
            System.out.println(dateRDV);
            System.out.println(heureRDV);
        compteOrthophoniste.ProgrammerSuiviRDV(dateRDV, heureRDV, num, presence);
        SerializationUtil.serialize(compteOrthophoniste, "compte.ser");
        Stage stage = (Stage) suivantButton.getScene().getWindow(); 
        ConsulterRDVIU page = new ConsulterRDVIU(); 
        page.show(); 
        stage.close();
        
    }
}
