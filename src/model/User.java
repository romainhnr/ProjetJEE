package model;
import java.util.*;

/**
 * 
 */
public class User {

    private UUID idUser;
    private String login;
    private String password;
    public String nom;
    private Role role;
    public ArrayList<Jeux> listeJeux;
    public ArrayList<Message> listeMessages;

    /**
     Enumération TypeInscription comportant : CERTAIN ou INCERTAIN concernant l'inscription à une séance *
     */
    private TypeInscription typeInscription;

    private Integer nbAdherentMinInscription;

    public enum Role {
        USER,
        ADMIN;
    }
    public enum TypeInscription {
        CERTAIN,
        INCERTAIN;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Jeux> getJeux() {
        return listeJeux;
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
        this.typeInscription = null;
        this.nbAdherentMinInscription = null;
    }

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