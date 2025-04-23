package TP.Noyau;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Enfant extends Patient {
    private String numTelParents;
    private String classeEtude;
    private Map<QuestionAnamnese<CategorieQRLEnfant>, String> reponsesAnamnese;

    public Enfant(String nom, String prenom, LocalDate dateNaissance, String lieuNaissance, String numTelParents, String classeEtude) {
        super(nom, prenom, dateNaissance, lieuNaissance);
        this.numTelParents = numTelParents;
        this.classeEtude = classeEtude;
        this.reponsesAnamnese = new HashMap<>();

    }

    public String getNumTelParents() {
        return this.numTelParents;
    }

    public void setNumTelParents(String numTelParents) {
        this.numTelParents = numTelParents;
    }

    public String getClasseEtude() {
        return this.classeEtude;
    }

    public void setClasseEtude(String classeEtude) {
        this.classeEtude = classeEtude;
    }
    public String getNumTel() {
        return numTelParents;
    }

    public String getType() {
        return "Enfant";
    }

    public Map<QuestionAnamnese<CategorieQRLEnfant>, String> getReponsesAnamnese() {
        return this.reponsesAnamnese;
    }
    public void afficherReponsesAnamnese() {
        System.out.println("Réponses de l'anamnèse de l'enfant :");
        for (Map.Entry<QuestionAnamnese<CategorieQRLEnfant>, String> entry : reponsesAnamnese.entrySet()) {
            QuestionAnamnese<CategorieQRLEnfant> question = entry.getKey();
            String reponse = entry.getValue();
            System.out.println(question.getEnonce() + ": " + reponse);
        }
    }
    public void afficherReponsesAnamneseEnfant(Enfant enfant) {
        System.out.println("Réponses à l'anamnèse de l'adulte :");
        for (Map.Entry<QuestionAnamnese<CategorieQRLEnfant>, String> entry : enfant.getReponsesAnamnese().entrySet()) {
            System.out.println(entry.getKey().getEnonce() + " : " + entry.getValue());
        }
    }

}
