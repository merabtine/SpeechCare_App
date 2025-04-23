package TP.Noyau;
import java.time.LocalDate;
import java.time.LocalTime;
public class SuiviRDV extends RDV {
   private final LocalTime duree=LocalTime.of(1, 0);
   private int numDossier;
   private boolean presentiel;
   public SuiviRDV(LocalDate date,LocalTime heure,int numDossier,boolean presentiel){
      super(date, heure);
      this.numDossier=numDossier;
      this.presentiel=presentiel;
   }

}
