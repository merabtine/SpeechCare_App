package TP.Noyau;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationUtil {

    public static void serialize(CompteOrthophoniste compte, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(compte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void serializePatient(Patient patient, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(patient);
            System.out.println("Patient sérialisé dans " + filename);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static void serializeEnfant(Enfant patient, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(patient);
            System.out.println("Patient sérialisé dans " + filename);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static void serializeAdulte(Adulte patient, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(patient);
            System.out.println("Patient sérialisé dans " + filename);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}

