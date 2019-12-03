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
    public String duree;
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
    public Jeux() {
    }


}