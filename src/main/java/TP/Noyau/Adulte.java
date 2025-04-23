package TP.Noyau;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Adulte extends Patient {
    private String diplome;
    private String profession;
    private String numTel;
    private Map<QuestionAnamnese<CategorieQRLAdulte>, String> reponsesAnamnese;

    public Adulte(String nom, String prenom, LocalDate dateNaissance, String lieuNaissance, String diplome, String profession, String numTel) {
        super(nom, prenom, dateNaissance, lieuNaissance);
        this.diplome = diplome;
        this.profession = profession;
        this.numTel = numTel;
        this.reponsesAnamnese = new HashMap<>();

    }

    public String getDiplome() {
        return this.diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getNumTel() {
        return this.numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
   
    public String getType() {
        return "Adulte";
    }
    public Map<QuestionAnamnese<CategorieQRLAdulte>, String> getReponsesAnamnese() {
        return this.reponsesAnamnese;
    }
    public void afficherReponsesAnamneseAdulte(Adulte adulte) {
        System.out.println("Réponses à l'anamnèse de l'adulte :");
        for (Map.Entry<QuestionAnamnese<CategorieQRLAdulte>, String> entry : adulte.getReponsesAnamnese().entrySet()) {
            System.out.println(entry.getKey().getEnonce() + " : " + entry.getValue());
        }
    }
    
}
