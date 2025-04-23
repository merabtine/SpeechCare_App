package TP.Noyau;
import java.util.*;

public class TestQuestion extends Test{
private List<QCU> qcus;
    public TestQuestion() {
        this.qcus = new ArrayList<>();
        QCU question1 = new QCU("Question : Quel son entendez-vous dans le mot \"pomme\" ?", new ArrayList<>(List.of("p", "m", "a")),1);
        QCU question2 = new QCU("Question : Parmi ces mots, lequel commence par le son \"ch\" ?", new ArrayList<>(List.of("Chat", "Table", "Lapin")),3);
        QCU question3 = new QCU("Question : Quel mot rime avec \"bateau\" ?", new ArrayList<>(List.of("Gâteau", "Chapeau", "Plumeau")),1);
        QCU question4 = new QCU("Question : Quel son entendez-vous à la fin du mot \"vélo\" ?", new ArrayList<>(List.of("v", "l", "o")),2);
        QCU question5 = new QCU("Question : Quel mot ne commence pas par la lettre \"s\" ?", new ArrayList<>(List.of("Soleil", "Serpent", "Fleur")),3);
        QCU question6 = new QCU("Question : Parmi ces mots, lequel contient le son \"ou\" ?", new ArrayList<>(List.of("Pomme", "Boule", "Chapeau")),2);
        QCU question7 = new QCU("Question : Quel mot contient le son \"in\" ?", new ArrayList<>(List.of("Chat", "Bain", "Plume")),2);
        QCU question8 = new QCU("Question : Quel est le son principal dans le mot \"plume\" ?", new ArrayList<>(List.of("p", "l", "u")),3);
        QCU question9 = new QCU("Question : Quel mot rime avec \"lapin\" ?", new ArrayList<>(List.of("Sapin", "Patin", "Bain")),2);
        QCU question10 = new QCU("Question : Parmi ces mots, lequel contient le son \"on\" ?", new ArrayList<>(List.of("Bonjour", "Soleil", "Porte")),1);

        // Ajout des questions à l'ensemble
        qcus.add(question1);
        qcus.add(question2);
        qcus.add(question3);
        qcus.add(question4);
        qcus.add(question5);
        qcus.add(question6);
        qcus.add(question7);
        qcus.add(question8);
        qcus.add(question9);
        qcus.add(question10);
    }
    public List<QCU> getQCU(){
        return qcus;
    }
    public void AfficherQCU(){
        for (QCU qcu : qcus) {
            System.out.println("Question : " + qcu.getEnonce());
            System.out.println("Options de réponse : " + qcu.getOptionsReponses());
            int index = 1;
            for (String reponse : qcu.getOptionsReponses()) {

                // Marquage de la bonne réponse
                if (index == qcu.getBonneReponseIndex()) {
                   System.out.println("Bonne réponse : "+reponse);
                }

                index++;
            }
        }
    }


}
