package TP.Noyau;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class Anamnese<T extends Serializable> implements Serializable{
    private List<QuestionAnamnese<T>> questions;

    public Anamnese() {
        this.questions = new ArrayList<>();
    }

    public void ajouterQuestion(String enonce, T categorie) {
        questions.add(new QuestionAnamnese<>(enonce, categorie));
    }

    public List<QuestionAnamnese<T>> getQuestions() {
        return this.questions;
    }
}
