package TP.Noyau;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class QCU extends QRL {
      private List<String> optionsReponses;
      private int bonneReponseIndex;

    public QCU(String enonce, List<String> optionsReponses,int bonneReponseIndex) {
        super(enonce);
        this.optionsReponses = optionsReponses;
        this.bonneReponseIndex=bonneReponseIndex;
    }

    public List<String> getOptionsReponses() {
        return optionsReponses;
    }
    public int getBonneReponseIndex() {
        return bonneReponseIndex;
    }
}
