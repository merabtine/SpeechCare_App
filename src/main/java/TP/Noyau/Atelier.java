package TP.Noyau;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class Atelier extends RDV{
  private final LocalTime duree=LocalTime.of(1, 0);
  private Set<Integer> numDossiers;
  private String thematique;
public Atelier(LocalDate Date,LocalTime Heure,Set<Integer> numDossiers,String thematique){
   super(Date, Heure);
   this.numDossiers=numDossiers;
   this.thematique=thematique;
}

}
