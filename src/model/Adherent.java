package model;
import java.util.*;

/**
 * 
 */
public class Adherent {

    /**
     * 
     */
    private UUID idAdherent;

    /**
     * 
     */
    private String login;

    /**
     * 
     */
    private String motdepasse;

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



    public enum TypeInscription {
        CERTAIN,
        INCERTAIN;
    }

    /**
     * Default constructor
     */
    public Adherent(String login, String nom, TypeInscription typeInscription, List<Jeux> listeJeux, Integer nbAdherentMinInscription) {
        this.idAdherent = UUID.randomUUID();
        this.login = login;
        this.motdepasse = generatePassword();
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
     * @param idAdherent 
     * @param idJeux
     */
    public void DemandeApportJeux(UUID idAdherent, UUID idJeux) {
        // TODO implement here
    }

}