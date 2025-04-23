package TP.Noyau;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationUtil {

    public static CompteOrthophoniste deserialize(String fileName) {
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (CompteOrthophoniste) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Deserialization failed: " + e.getMessage());
            return null;
        }
    }
    public static Patient deserializePatient(String filename) {
        Patient patient = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            patient = (Patient) in.readObject();
            System.out.println("Patient désérialisé depuis " + filename);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Classe Patient non trouvée");
            c.printStackTrace();
        }
        return patient;
    }
}

