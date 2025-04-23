package TP.Noyau;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class RDV implements Serializable{
    private static final long serialVersionUID = -585121212825507463L;
private LocalDate date;
private LocalTime heure;

public RDV(LocalDate date,LocalTime heure){
    this.date=date;
    this.heure=heure;
}
public LocalDate getDate() {
    return date;
}
public LocalTime getHeure(){
    return heure;
}
public String getType(){
    return  this.getClass().getSimpleName();
}

}
