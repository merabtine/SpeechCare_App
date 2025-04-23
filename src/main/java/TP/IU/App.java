package TP.IU;
import TP.Control.ApplicationState;
import TP.Noyau.CompteOrthophoniste;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
     @Override
     public void start(Stage primaryStage) throws Exception{
        Acceuil stage=new Acceuil();
        stage.show();
    }
    public static void main(String[] args) {
        launch();
        //TestQuestion test=new TestQuestion();
        //test.AfficherQCU();
         /*CompteOrthophoniste orthophoniste = new CompteOrthophoniste("Mohammed", "Youcef", "123 Rue des Roses", "0555123421", "youcef@gmail.com", "password");
         orthophoniste.creerAnamnese();
         orthophoniste.afficherAnamneseEnfant();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dateNaissance = LocalDate.parse("13-06-2007", formatter);
         Enfant enfant = new Enfant("Merabtine", "Maria", dateNaissance, "Beni messous", "0668242904", "2AS matheleme");
         orthophoniste.sauvegarderAnamneseEnfant(enfant);
         System.out.println();
         System.out.println();
         System.out.println();
         enfant.afficherReponsesAnamnese();
        //orthophoniste.afficherAnamneseEnfant();
        //System.out.println();
        //orthophoniste.afficherAnamneseAdulte();
        */
        //orthophoniste.creerQuestionsQRL();
        //orthophoniste.afficherQuestionsQRL();
        CompteOrthophoniste compteOrthophoniste = ApplicationState.getInstance().getOrthophoniste();
        compteOrthophoniste.afficherPatients();
        
    }

}