package model;
import java.util.*;

// Classe jeux pour la liste des jeux des utilisateurs
public class Jeux {

    private UUID idJeux;
    private String titre;
    private String description;
    private Theme theme;
    private Integer duree;
    private Integer nbJoueurMin;
    private Integer nbJoueurMax;

    public enum Theme{
        ROLE,
        AMBIANCE,
        STRATEGIE;

    }

    // getter & setter
    public UUID getIdJeux() {
        return idJeux;
    }
    public String getTitre() {
        return titre;
    }
    public String getDescription() {
        return description;
    }
    public Theme getTheme() {
        return theme;
    }
    public Integer getDuree() {
        return duree;
    }
    public Integer getNbJoueurMin() {
        return nbJoueurMin;
    }
    public Integer getNbJoueurMax() {
        return nbJoueurMax;
    }


    // constructeur
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