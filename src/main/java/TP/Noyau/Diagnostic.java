package TP.Noyau;
import java.util.Set;
public class Diagnostic {
private Set<Trouble> troubles;

public Diagnostic(Set <Trouble> troubles){
    this.troubles=troubles;
}

public void setTroubles(Set<Trouble> troubles){
   this.troubles=troubles;
   System.out.println(this.troubles);
}
}
