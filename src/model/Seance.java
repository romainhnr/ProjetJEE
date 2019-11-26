package model;
import java.util.*;
import java.time.*;

/**
 Classe Séance*
 */
public class Seance {

    /**
     * 
     */
    private UUID idSeance;

    /**
     * 
     */
    public LocalDate date;

    /**
     * 
     */
    public LocalTime horaireDebut;

    /**
     * 
     */
    public LocalTime horaireFin;

    /**
     * 
     */
    public List<Adherent> listAdherentsInscrit;

    /**
     * Default constructor
     */
    public Seance(LocalDate date, LocalTime horaireDebut, LocalTime horaireFin) {
        this.idSeance = UUID.randomUUID();
        this.date = date;
        this.horaireDebut = horaireDebut;
        this.horaireFin= horaireFin;

    }


    /**
     * @param idAdherent
     */
    public void adherentEstInscrit(int idAdherent) {
        // TODO implement here
    }

    /**
     * @param idAdherent
     */
    public void adherentEstCertain(int idAdherent) {
        // TODO implement here
    }

    /**
     * @param idAdherent
     */
    public void adherentApporteJeux(int idAdherent) {
        // TODO implement here
    }

}