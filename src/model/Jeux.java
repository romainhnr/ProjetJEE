package model;
import java.util.*;

/**
 * 
 */
public class Jeux {

    private UUID idJeux;
    public String titre;
    public String description;
    public Theme theme;
    public Integer duree;
    public Integer nbJoueurMin;
    public Integer nbJoueurMax;

    public enum Theme{
        ROLE,
        AMBIANCE,
        STRATEGIE;


    }

    /**
     * Default constructor
     */
    public Jeux(String titre, String description, Theme theme, Integer duree, Integer nbJoueurMin, Integer nbJoueurMax) {
        this.idJeux = UUID.randomUUID();
        this.titre = titre;
        this.description = description;
        this.theme = theme;
        this.duree = duree;
        this.nbJoueurMin = nbJoueurMin;
        this.nbJoueurMax = nbJoueurMax;
    }


}