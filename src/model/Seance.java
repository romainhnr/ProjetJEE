package model;
import java.util.*;
import java.time.*;

// Classe séance
public class Seance {

    private UUID idSeance;
    private LocalDate date;
    private LocalTime horaireDebut;
    private LocalTime horaireFin;
    private List<User> listUserInscritCertain;
    private List<User> listUserInscritIncertain;
    private List<Jeux> seanceListeJeux;

// constructeur
    public Seance(LocalDate date, LocalTime horaireDebut, LocalTime horaireFin) {
        this.idSeance = UUID.randomUUID();
        this.date = date;
        this.horaireDebut = horaireDebut;
        this.horaireFin= horaireFin;
        this.listUserInscritCertain = new ArrayList<>();
        this.listUserInscritIncertain = new ArrayList<>();
        this.seanceListeJeux = new ArrayList<>();
    }


    // getter & setter
    public UUID getIdSeance() { return idSeance; }
    public LocalDate getDate() { return date; }
    public LocalTime getHoraireDebut() { return horaireDebut; }
    public LocalTime getHoraireFin() { return horaireFin; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setHoraireDebut(LocalTime horaireDebut) { this.horaireDebut = horaireDebut; }
    public void setHoraireFin(LocalTime horaireFin) { this.horaireFin = horaireFin; }

    public List<User> getListUserInscritCertain() {
        return listUserInscritCertain;
    }
    public void addListUserInscritCertain(User userInscritCertain) {
        this.listUserInscritCertain.add(userInscritCertain);
    }
    public void removeListUserInscritCertain(User userInscritCertain) {
        this.listUserInscritCertain.remove(userInscritCertain);
    }

    public List<User> getListUserInscritIncertain() {
        return listUserInscritIncertain;
    }
    public void addListUserInscritIncertain(User userInscritIncertain) {
        this.listUserInscritIncertain.add(userInscritIncertain);
    }
    public void removeListUserInscritIncertain(User userInscritIncertain) {
        this.listUserInscritIncertain.remove(userInscritIncertain);
    }

    public List<Jeux> getSeanceListeJeux() {
        return seanceListeJeux;
    }
    public void addSeanceListeJeux(Jeux jeu) {
        this.seanceListeJeux.add(jeu);
    }
    public void removeSeanceListeJeux(Jeux jeu) {
        this.seanceListeJeux.remove(jeu);
    }


}

