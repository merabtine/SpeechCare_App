package TP.Noyau;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CompteOrthophoniste implements Serializable{
  private static final long serialVersionUID = -6831406218528548894L;
private String Nom;
private String Prenom;
private String Adresse;
private String NumTel;
private String Email;
private String Password;
private Set<Patient> patients;
private Anamnese<CategorieQRLEnfant> anamneseEnfant;
private Anamnese<CategorieQRLAdulte> anamneseAdulte;
private ArrayList<QRL> questionsQRL;
private Set<RDV> Agenda;
private TestQuestion test1;
 public void setAnamneseEnfant(){
     this.anamneseEnfant = new Anamnese<>(); 
 }
 public void setAnamneseAdulte(){
  this.anamneseAdulte = new Anamnese<>(); 
}

//private Patient[] patients
//private Agenda agenda
//private Test[] tests; 
//private Question[] questions
//private Exercice[] questions
//private Dossier[] questions

  public CompteOrthophoniste(String Nom,String Prenom,String Adresse,String NumTel,String Email,String Password){
     this.Nom=Nom;
     this.Prenom=Prenom;
     this.Adresse=Adresse;
     this.NumTel=NumTel;
     this.Email=Email;
     this.Password=Password;
     this.patients = new HashSet<>();
     this.anamneseEnfant = new Anamnese<>(); 
     this.anamneseAdulte = new Anamnese<>(); 
     this.Agenda = new HashSet<>();
     this.questionsQRL= new ArrayList<QRL>();
  }
  public void ajouterPatient(Patient patient) {
    this.patients.add(patient);
  }
  public void setTestQuestion(TestQuestion test){
    this.test1=test;
  }
  public TestQuestion getTestQuestion(){
    return test1;
  }
  public Set<Patient> getPatients(){
    return this.patients;
  }
  public Patient consulterPatient(String nom, String prenom, LocalDate dateNaissance, String lieuNaissance) {
        int age = Period.between(dateNaissance, LocalDate.now()).getYears();
        
        if (age < 18) {
          System.out.println("c un enfant");
          return new Enfant(nom, prenom, dateNaissance, lieuNaissance, null, null);

        } else {
            System.out.println("c un adulte");
          return new Adulte(nom, prenom, dateNaissance, lieuNaissance, null, null,null);

        }

    }
    
    public void afficherPatients() {
      for (Patient patient : patients) {
          String type = (patient instanceof Enfant) ? "Enfant" : "Adulte";
          String numTel = (patient instanceof Enfant) ? ((Enfant) patient).getNumTelParents() : ((Adulte) patient).getNumTel();
          System.out.println("Nom: " + patient.getNom());
          System.out.println("Prenom: " + patient.getPrenom());
          System.out.println("Date de naissance: " + patient.getDateNaissance());
          System.out.println("Lieu de naissance: " + patient.getLieuNaissance());
          System.out.println("Numéro de téléphone: " + numTel);
          System.out.println("Type: " + type);
          System.out.println();
      }
  }

  public void creerAnamnese(){
    // Modèle Anamnese Enfant
    this.anamneseEnfant.ajouterQuestion("Quelle est la composition de votre famille?", CategorieQRLEnfant.STRUCTURE_FAMILIALE);
    this.anamneseEnfant.ajouterQuestion("Comment décririez-vous les relations entre les membres de la famille?", CategorieQRLEnfant.DYNAMIQUE_FAMILIALE);
    this.anamneseEnfant.ajouterQuestion("Y a-t-il des antécédents médicaux ou psychologiques importants dans la famille?", CategorieQRLEnfant.ANTECEDENTS_FAMILIAUX);
    this.anamneseEnfant.ajouterQuestion("Y a-t-il eu des complications lors de la naissance?", CategorieQRLEnfant.CONDITIONS_NATALES);
    this.anamneseEnfant.ajouterQuestion("À quel âge l'enfant a-t-il commencé à marcher?", CategorieQRLEnfant.DEVELOPPEMENT_PSYCHOMOTEUR);
    this.anamneseEnfant.ajouterQuestion("À quel âge l'enfant a-t-il commencé à parler?", CategorieQRLEnfant.DEVELOPPEMENT_LANGAGIER);
    this.anamneseEnfant.ajouterQuestion("L'enfant présente-t-il des comportements particuliers ou inhabituels?", CategorieQRLEnfant.CARACTERE_ET_COMPORTEMENT);
    // Modèle Ananmnese Adulte
    this.anamneseAdulte.ajouterQuestion("Pouvez-vous décrire l'historique de votre maladie?", CategorieQRLAdulte.HISTORIQUE_MALADIE);
    this.anamneseAdulte.ajouterQuestion("Y a-t-il eu des épisodes similaires dans le passé?", CategorieQRLAdulte.HISTORIQUE_MALADIE);
    this.anamneseAdulte.ajouterQuestion("Avez-vous des antécédents médicaux familiaux liés à votre condition?", CategorieQRLAdulte.HISTORIQUE_MALADIE);
    this.anamneseAdulte.ajouterQuestion("Quels médecins ou spécialistes avez-vous consultés pour cette condition?", CategorieQRLAdulte.SUIVI_MEDICAL);
    this.anamneseAdulte.ajouterQuestion("Quels traitements ou médicaments avez-vous suivis?", CategorieQRLAdulte.SUIVI_MEDICAL);
    this.anamneseAdulte.ajouterQuestion("Avez-vous des rendez-vous médicaux réguliers pour le suivi de votre condition?", CategorieQRLAdulte.SUIVI_MEDICAL);
    this.anamneseAdulte.ajouterQuestion("Comment évaluez-vous l'efficacité des traitements suivis jusqu'à présent?", CategorieQRLAdulte.SUIVI_MEDICAL);
    }
    public void ajouterQuestionAnamneseEnfant(String enonce, CategorieQRLEnfant categorie) {
      this.anamneseEnfant.ajouterQuestion(enonce, categorie);
  }
  public void ajouterQuestionAnamneseAdulte(String enonce, CategorieQRLAdulte categorie) {
    this.anamneseAdulte.ajouterQuestion(enonce, categorie);
}
  
    public void afficherAnamneseEnfant() {
      System.out.println("Anamnèse de l'enfant :");
      for (QuestionAnamnese<CategorieQRLEnfant> question : this.anamneseEnfant.getQuestions()) {
          System.out.println(question.getEnonce());
      }
  }

  public void afficherAnamneseAdulte() {
      System.out.println("Anamnèse de l'adulte :");
      for (QuestionAnamnese<CategorieQRLAdulte> question : this.anamneseAdulte.getQuestions()) {
          System.out.println(question.getEnonce());
      }
  }

  public void sauvegarderAnamneseEnfant(Enfant enfant) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Réponses à l'anamnèse de l'enfant :");

        for (QuestionAnamnese<CategorieQRLEnfant> question : this.anamneseEnfant.getQuestions()) {
            System.out.println(question.getEnonce());
            String reponse = scanner.nextLine();
            enfant.getReponsesAnamnese().put(question, reponse);
        }

        System.out.println("Anamnèse de l'enfant enregistrée avec les réponses.");
    }
    public void sauvegarderAnamneseAdulte(Adulte adulte) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Réponses à l'anamnèse de l'enfant :");

      for (QuestionAnamnese<CategorieQRLAdulte> question : this.anamneseAdulte.getQuestions()) {
          System.out.println(question.getEnonce());
          String reponse = scanner.nextLine();
          adulte.getReponsesAnamnese().put(question, reponse);
      }

      System.out.println("Anamnèse de l'enfant enregistrée avec les réponses.");
  }
  public Anamnese<CategorieQRLEnfant> getAnamneseEnfant(){
    return this.anamneseEnfant;
  }
  public Anamnese<CategorieQRLAdulte> getAnamneseAdulte(){
    return this.anamneseAdulte;
  }
  public void ajouterQRL(String enonce) {
        if (enonce != null && !enonce.isEmpty()) {
            QRL qrl = new QRL(enonce);
            questionsQRL.add(qrl);
        } else {
            System.out.println("Erreur : l'énoncé de la question ne peut pas être null ou vide.");
        }
    }

    public List<QRL> getQrlListe() {
        return this.questionsQRL;
    }
    public void creerQuestionsQRL() {
      this.questionsQRL= new ArrayList<QRL>();
      this.questionsQRL.add(new QRL("Avez-vous des frères ou sœurs? Si oui, combien?"));
      this.questionsQRL.add(new QRL("Comment décririez-vous votre personnalité?"));
      this.questionsQRL.add(new QRL("Quel est votre plat préféré?"));
      this.questionsQRL.add(new QRL("Qu'est-ce que vous aimez faire pendant votre temps libre?"));
      this.questionsQRL.add(new QRL("Quel est votre sujet préféré à l'école?"));
      this.questionsQRL.add(new QRL("Est-ce que vous avez des amis proches?"));
      this.questionsQRL.add(new QRL("Décrivez un moment où vous étiez très heureux."));
      this.questionsQRL.add(new QRL("Avez-vous des objectifs que vous voulez atteindre? Lesquels?"));
      this.questionsQRL.add(new QRL("Comment vous sentez-vous aujourd'hui?"));
    }
    public void afficherQuestionsQRL() {
      System.out.println("Liste des questions QRL :");
      for (QRL qrl : questionsQRL) {
          System.out.println(qrl.getEnonce());
      }
  }
    public void Afficher(){
    System.out.println(Nom);
    System.out.println(Prenom);
    System.out.println(Adresse);
    System.out.println(NumTel);
    System.out.println(Email);
    System.out.println(Password);
  }
  public void ProgrammerConsultation(LocalDate Date,LocalTime Heure,String Nom,String Prenom,int Age){
    Consultation Rendezvous = new Consultation(Date, Heure, Nom, Prenom, Age);
    try {
      Agenda.add(Rendezvous);
      System.out.println("Consultation programmée avec succès !");
  } catch (Exception e) {
      System.err.println("Erreur lors de la programmation de la consultation : " + e.getMessage());
      e.printStackTrace();
  }
  }
  public void ProgrammerSuiviRDV(LocalDate date,LocalTime heure,int numDossier,boolean presentiel){
          SuiviRDV Rendezvous=new SuiviRDV(date, heure, numDossier, presentiel);
          try {
            Agenda.add(Rendezvous);
            System.out.println("RDV Suivi programmée avec succès !");
        } catch (Exception e) {
            System.err.println("Erreur lors de la programmation du RDV de suivi: " + e.getMessage());
            e.printStackTrace();
        }
  }

  public void ProgrammerAtelier(LocalDate Date,LocalTime Heure,Set<Integer> numDossier,String thematique){
          Atelier Rendezvous=new Atelier(Date, Heure, numDossier, thematique);
          try {
            Agenda.add(Rendezvous);
            System.out.println("Atelier programmée avec succès !");
          } catch (Exception e) {
            System.err.println("Erreur lors de la programmation de l'Atelier : " + e.getMessage());
            e.printStackTrace();
          }
  }
  public void PoserDiagnostic(){}//new Diagnostic + setdiagnnostic de BO
  /*public void EtablirFicheSuivi(ArrayList<Objectif> objectifs){
        FicheSuivi NouvelleFiche=new FicheSuivi(objectifs);
        System.out.println("Fiche de suivi cree avec succes");
  }*/
  public Set<RDV> getAgenda() {
    return Agenda;
  }

  public void setAgenda(Set<RDV> Agenda){
    this.Agenda=Agenda;
  }
  public void afficherTypeRDV() {
    for (RDV rdv : getAgenda()) {
        String typeRDV = rdv.getClass().getSimpleName();
        System.out.println("Type de RDV : " + typeRDV);
    }
  }
  public String getEmail(){
    return Email;
  }
  public String getPassowrd(){
    return Password;
  }
  public void setEmail(String email){
    Email=email;
  }
  public void setPassword(String password){
    Password=password;
  }
  

}
