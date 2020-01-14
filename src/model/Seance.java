package model;
import java.util.*;
import java.time.*;

/**
 Classe Séance*
 */
public class Seance {

    private UUID idSeance;
    private LocalDate date;
    private LocalTime horaireDebut;
    private LocalTime horaireFin;
    private List<User> listUserInscritCertain;
    private List<User> listUserInscritIncertain;

    /**
     * Default constructor
     */
    public Seance(LocalDate date, LocalTime horaireDebut, LocalTime horaireFin) {
        this.idSeance = UUID.randomUUID();
        this.date = date;
        this.horaireDebut = horaireDebut;
        this.horaireFin= horaireFin;
        this.listUserInscritCertain = new ArrayList<>();
        this.listUserInscritIncertain = new ArrayList<>();

    }

    public UUID getIdSeance() { return idSeance; }
    public LocalDate getDate() { return date; }
    public LocalTime getHoraireDebut() { return horaireDebut; }
    public LocalTime getHoraireFin() { return horaireFin; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setHoraireDebut(LocalTime horaireDebut) { this.horaireDebut = horaireDebut; }
    public void setHoraireFin(LocalTime horaireFin) { this.horaireFin = horaireFin; }
    public List<User> getListUserInscritCertain() { return listUserInscritCertain; }
    public void addListUserInscritCertain(User userInscritCertain) { this.listUserInscritCertain.add(userInscritCertain); }
    public void removeListUserInscritCertain(User userInscritCertain) { this.listUserInscritCertain.remove(userInscritCertain); }
    public List<User> getListUserInscritIncertain() { return listUserInscritIncertain; }
    public void addListUserInscritIncertain(User userInscritIncertain) { this.listUserInscritIncertain.add(userInscritIncertain); }
    public void removeListUserInscritIncertain(User userInscritIncertain) { this.listUserInscritIncertain.remove(userInscritIncertain); }


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

