package TP.Noyau;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Set;
public class Dossier {
private int num;
private ArrayList<RDV> rdvs;
private ArrayList<BO> bos;
private ArrayList<FicheSuivi> fiches;
private Patient patient;
    public Dossier(int num){
        this.num=num;
        bos= new ArrayList<BO>();
        rdvs=new ArrayList<RDV>();
        fiches=new ArrayList<FicheSuivi>();
    }
    public void NouvelleFiche(FicheSuivi fiche){
        fiches.add(fiche);
    }
}
