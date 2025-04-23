package TP.Noyau;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Patient implements Serializable{
private String nom;
private String prenom;
private LocalDate dateNaissance;
private String lieuNaissance;
private ArrayList<QRL> reponsesQRL;
private Dossier dossier;
public Patient(String nom, String prenom,LocalDate dateNaissance,String LieuNaissance){
    this.nom=nom;
    this.prenom=prenom;
    this.dateNaissance=dateNaissance;
    this.lieuNaissance=LieuNaissance;


}
public void setPatientDetails(String nom, String prenom, LocalDate dateNaissance, String lieuNaissance) {
    this.nom = nom;
    this.prenom = prenom;
    this.dateNaissance = dateNaissance;
    this.lieuNaissance = lieuNaissance;
}
public String getNom(){
    return this.nom;
}
public String getPrenom(){
    return this.prenom;
}
public LocalDate getDateNaissance(){
    return this.dateNaissance;
}
public String getLieuNaissance(){
    return this.lieuNaissance;
}
public abstract String getNumTel();

public abstract String getType();
public void setReponsesQRL(ArrayList<QRL> reponsesQRL){
this.reponsesQRL=reponsesQRL;
}
public void afficherReponsesQRL() {
    if (reponsesQRL == null || reponsesQRL.isEmpty()) {
        System.out.println("Aucune réponse enregistrée.");
        return;
    }

    for (QRL qrl : reponsesQRL) {
        System.out.println("Question: " + qrl.getEnonce());
        System.out.println("Réponse: " + qrl.getReponse());
        System.out.println("Score: " + qrl.getScore());
        System.out.println("---------------------------");
    }
}
}

