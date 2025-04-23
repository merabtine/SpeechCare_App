package TP.Noyau;
public class Objectif {
   private String Nom;
   private Terme terme;
   public Objectif(String Nom,Terme terme){
    this.Nom=Nom;
    this.terme=terme;
    System.out.println("Objectif cree avec succes");
   }
}
