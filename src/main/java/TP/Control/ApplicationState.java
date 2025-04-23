package TP.Control;
import java.time.LocalDate;
import java.time.LocalTime;

import TP.Noyau.CompteOrthophoniste;

public class ApplicationState {

    private static ApplicationState instance;
    private CompteOrthophoniste orthophoniste;
    private LocalDate date;
    private LocalTime heure;
    private int[] selectedAnswersIndices;
    private int num;

    private ApplicationState() {
        // Private constructor to prevent instantiation
    }

    public static ApplicationState getInstance() {
        if (instance == null) {
            instance = new ApplicationState();
        }
        return instance;
    }

    public CompteOrthophoniste getOrthophoniste() {
        return this.orthophoniste;
    }

    public void setOrthophoniste(CompteOrthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    public LocalDate getRDVDate() {
        return this.date;
    }
    public void setRDVDate(LocalDate date) {
        this.date=date;
    }

    public LocalTime getRDVTime() {
        return this.heure;
    }
    public void setRDVTime(LocalTime heure) {
        this.heure=heure;
    }
    public int[] getSelectedReponses(){
        return this.selectedAnswersIndices;
    }
    public void setSelectedReponses(int[] selectedAnswersIndices){
        this.selectedAnswersIndices=selectedAnswersIndices;
    }
    public void setnumDossier(int num){
         this.num=num;
    }
    public int getnumDossier(){
        return num;
    }
    
}

