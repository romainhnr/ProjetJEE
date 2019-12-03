package model;
import java.util.*;

/**
 * 
 */
public class User {

    /**
     * 
     */
    private UUID idUser;

    /**
     * 
     */
    private String login;

    /**
     * 
     */
    private String password;

    private Role role;

    /**
     * 
     */
    public String nom;

    /**
     * 
     */
    public List<Jeux> listeJeux;

    /**
     Enumération TypeInscription comportant : CERTAIN ou INCERTAIN concernant l'inscription à une séance *
     */
    private TypeInscription typeInscription;

    /**
     * 
     */
    private Integer nbAdherentMinInscription;

    public enum Role {
        USER,
        ADMIN;
    }

    public enum TypeInscription {
        CERTAIN,
        INCERTAIN;
    }

    /**
     * Default constructor
     */
    public User(String login, String nom, Role role,TypeInscription typeInscription, List<Jeux> listeJeux, Integer nbAdherentMinInscription) {
        this.idUser = UUID.randomUUID();
        this.login = login;
        this.role = role;
        this.password = generatePassword();
        this.nom = nom;
        this.listeJeux = listeJeux;
        this.typeInscription = typeInscription;

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