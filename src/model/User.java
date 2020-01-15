package model;
import java.util.*;

/**
 * 
 */
public class User {

    private UUID idUser;
    private String login;
    private String password;
    private String nom;
    private Role role;
    private List<Jeux> listeJeux;
    private List<Message> listeMessages;
    private Integer nbAdherentMinInscription;

    public enum Role {
        USER,
        ADMIN;
    }


    /**
     * Default constructor
     */
    public User(String login, String password, String nom, Role role) {
        this.idUser = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.role = role;
        this.listeJeux = new ArrayList<Jeux>();
        this.listeMessages = new ArrayList<Message>();
        this.nbAdherentMinInscription = null;
    }

    public UUID getIdUser() { return idUser; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public List<Jeux> getJeux() { return listeJeux; }
    public void addListeJeux(Jeux jeu) { this.listeJeux.add(jeu); }
    public void removeListeJeux(Jeux jeu) { this.listeJeux.remove(jeu); }
    public List<Message> getListeMessages() { return listeMessages; }
    public void addListeMessages(Message message) { this.listeMessages.add(message); }
    public Integer getNbAdherentMinInscription() { return nbAdherentMinInscription; }
    public void setNbAdherentMinInscription(Integer nbAdherentMinInscription) { this.nbAdherentMinInscription = nbAdherentMinInscription; }


    private String generatePassword() {
        // TODO à générer aléatoirement
        // TODO ou classe Authentification réunissant login et mdp des admins et users
        return "toto";
    }


    /**
     * @param idSeance
     */
    public void InscriptionSeance(UUID idSeance) {
        // TODO implement here
    }

    /**
     * @param idSeance
     */
    public void DesinscriptionSeance(UUID idSeance) {
        // TODO implement here
    }

    /**
     * 
     */
    public void ModificationTypeInscription() {
        // TODO implement here
    }

    /**
     * @param idUser
     * @param idJeux
     */
    public void DemandeApportJeux(UUID idUser, UUID idJeux) {
        // TODO implement here
    }

}