package TP.Noyau;

import java.io.Serializable;

public class QRL implements Serializable{
    private static final long serialVersionUID = -57902670976497263L;
    private String enonce;
    private String reponse;
    private int score;
    public QRL(String enonce){
        this.enonce=enonce;
        this.score=0;
        this.reponse="";
    }
    public QRL(String enonce, String reponse, int score){
        this.enonce=enonce;
        this.score=score;
        this.reponse=reponse;
    }
    public String getEnonce(){
        return this.enonce;
    }
    public void setScore(int n){
        this.score=n;
    }
    public int getScore(){
        return this.score;
    }
    public String getReponse() {
        return this.reponse;
    }

  
}
