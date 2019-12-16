package model;
import java.util.*;
import java.time.*;

/**
 Classe Séance*
 */
public class Seance {

    private UUID idSeance;
    public LocalDate date;
    public LocalTime horaireDebut;
    public LocalTime horaireFin;
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
    public UUID getIdSeance() {
        return idSeance;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getHoraireDebut() {
        return horaireDebut;
    }

    public void setHoraireDebut(LocalTime horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public LocalTime getHoraireFin() {
        return horaireFin;
    }

    public void setHoraireFin(LocalTime horaireFin) {
        this.horaireFin = horaireFin;
    }
/*
    public Seance getSeanceParId(String id, List<Seance> seances) {
        Seance seance = null;
        for (Seance s : seances) {
            if (s.getIdSeance().equals(id)) {
                seance = s;
                break;
            }
        }
        return seance;
    }
*/

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

