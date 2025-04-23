package TP.Noyau;

import java.io.Serializable;

public class QuestionAnamnese<T extends Serializable> implements Serializable{
    private String enonce;
    private  T categorie;
    public QuestionAnamnese(String enonce, T categorie){
        this.enonce=enonce;
        this.categorie=categorie;
    }
    public String getEnonce() {
        return this.enonce;
    }

    public T getCategorie() {
        return this.categorie;
    }

    public void setEnonce(String enonce) {
        this.enonce = enonce;
    }

    public void setCategorie(T categorie) {
        this.categorie = categorie;
    }
}
