package TP.Noyau;
import java.time.LocalDate;
import java.time.LocalTime;

public class Consultation extends RDV{
    private String Nom;
    private String Prenom;
    private int Age;
    private final LocalTime DureeEnfant=LocalTime.of(2, 30);
    private final LocalTime DureeAdulte=LocalTime.of(1, 30);
    
    public Consultation(LocalDate Date,LocalTime Heure,String Nom,String Prenom,int Age){
        super(Date,Heure);
        this.Nom=Nom;
        this.Prenom=Prenom;
        this.Age=Age;
    }

}
