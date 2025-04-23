package TP.Noyau;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CompteRendu implements Serializable{
    private Map<QCU, Integer> scores;

    public CompteRendu() {
        scores = new HashMap<>();
    }

    public void ajouterScore(QCU question, int score) {
        scores.put(question, score);
    }

    public int getScore(QCU question) {
        return scores.getOrDefault(question, 0); // Si le score n'est pas trouvé, retourne 0 par défaut
    }
}
