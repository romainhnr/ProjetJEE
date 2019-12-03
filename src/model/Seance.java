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
    public List<User> listUserInscrit;

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
     * @param idUser
     */
    public void UserEstInscrit(int idUser) {
        // TODO implement here
    }

    /**
     * @param idUser
     */
    public void UserEstCertain(int idUser) {
        // TODO implement here
    }

    /**
     * @param idUser
     */
    public void UserApporteJeux(int idUser) {
        // TODO implement here
    }

}