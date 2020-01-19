package servlet;

import model.Jeux;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

// Servlet des jeux gérant la création et la suppression d'un jeu
@WebServlet(name = "JeuxServlet")
public class JeuxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // création jeux
        // récupérations des champs du formulaire
        String choixTheme = request.getParameter("choixTheme");
        String titre = request.getParameter("titre");
        String duree = request.getParameter("duree");
        String nbmin = request.getParameter("nbmin");
        String nbmax = request.getParameter("nbmax");
        String description = request.getParameter("description");

        if (titre == null || titre.isBlank() || titre.isEmpty() || description == null || description.isBlank() || description.isEmpty() || duree == null || duree.isBlank() || duree.isEmpty() || nbmin == null || nbmin.isBlank() || nbmin.isEmpty() || nbmax == null || nbmax.isBlank() || nbmax.isEmpty() || choixTheme == null || choixTheme.isBlank() || choixTheme.isEmpty()) {
            String error_message_jeu_creation = "Les champs doivent être remplis";
            request.setAttribute("message_jeu", error_message_jeu_creation);
            this.getServletContext().getRequestDispatcher("/jeuxAdd.jsp").forward(request, response);
        }
        String titreJeu = titre;
        String descriptionJeu = description;
        Integer dureeJeu = Integer.parseInt(duree);
        Integer nbminJeu = Integer.parseInt(nbmin);
        Integer nbmaxJeu = Integer.parseInt(nbmax);
        Jeux.Theme themeJeu = null;

        if(dureeJeu < 1){
            String error_message_jeux_creation = "La durée doit être supérieure à 1 minute";
            request.setAttribute("message_jeu", error_message_jeux_creation);
            this.getServletContext().getRequestDispatcher("/jeuxAdd.jsp").forward(request, response);
        }
        else if(nbminJeu > nbmaxJeu){
            String error_message_jeux_creation = "Le nombre min. doit être inférieur au nombre max.";
            request.setAttribute("message_jeu", error_message_jeux_creation);
            this.getServletContext().getRequestDispatcher("/jeuxAdd.jsp").forward(request, response);
        }
        else{
            if (choixTheme.equals("Jeu de role")){
                themeJeu = Jeux.Theme.ROLE;
            }
            else if(choixTheme.equals("Jeu ambiance")){
                themeJeu = Jeux.Theme.AMBIANCE;
            }
            else if(choixTheme.equals("Jeu de strategie")){
                themeJeu = Jeux.Theme.STRATEGIE;
            }
            else{
                String error_message_jeu_creation = "Erreur : choix non trouvé ";
                request.setAttribute("message_jeu", error_message_jeu_creation);
                this.getServletContext().getRequestDispatcher("/jeuxAdd.jsp").forward(request, response);
            }
            // après vérification création du jeu
            Jeux newJeux = new Jeux(titreJeu, descriptionJeu, themeJeu, dureeJeu, nbminJeu, nbmaxJeu);
            User currentUser = (User)request.getServletContext().getAttribute("currentUser");
            currentUser.addListeJeux(newJeux);
            getServletContext().setAttribute("currentUser", currentUser);

            // sauvegarde des jeux
            try {
                System.out.println("Ecriture fichier jeux début");
                BufferedWriter writer = Files.newBufferedWriter(Path.of(getServletContext().getRealPath("data/jeux.csv")));

                for (Jeux jeu_to_save : currentUser.getJeux()) {
                    writer.write(jeu_to_save.getTitre()+",");
                    writer.write(jeu_to_save.getDescription()+",");
                    writer.write(jeu_to_save.getTheme().toString().toLowerCase()+",");
                    writer.write(jeu_to_save.getDuree().toString()+",");
                    writer.write(jeu_to_save.getNbJoueurMin().toString()+",");
                    writer.write(jeu_to_save.getNbJoueurMax().toString());
                    writer.newLine();
                }

                writer.close();
                System.out.println("Ecriture fichier jeux fin");

            } catch (IOException ex) {
                ex.printStackTrace();
            }


            String message_jeu = "Le jeu " + newJeux.getTitre() + " a bien été rajouté";
            request.setAttribute("message_jeu", message_jeu);

            this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // suppression jeu
        String id = request.getParameter("id");
        User currentUser = (User)request.getServletContext().getAttribute("currentUser");
        UUID UUID_id = UUID.fromString(id);

        for (Jeux jeu : currentUser.getJeux()) {
            if (jeu.getIdJeux().equals(UUID_id)) {
                currentUser.removeListeJeux(jeu);
                getServletContext().setAttribute("currentUser", currentUser);

                String validation_message_jeu_delete = "Le jeu se nommant " + jeu.getTitre() + " a bien été supprimé de votre liste";
                request.setAttribute("message_jeu", validation_message_jeu_delete);

                // sauvegarde des jeux
                try {
                    System.out.println("Ecriture fichier jeux début");
                    BufferedWriter writer = Files.newBufferedWriter(Path.of(getServletContext().getRealPath("data/jeux.csv")));

                    for (Jeux jeu_to_save : currentUser.getJeux()) {
                        writer.write(jeu_to_save.getTitre()+",");
                        writer.write(jeu_to_save.getDescription()+",");
                        writer.write(jeu_to_save.getTheme().toString().toLowerCase()+",");
                        writer.write(jeu_to_save.getDuree().toString()+",");
                        writer.write(jeu_to_save.getNbJoueurMin().toString()+",");
                        writer.write(jeu_to_save.getNbJoueurMax().toString());
                        writer.newLine();
                    }

                    writer.close();
                    System.out.println("Ecriture fichier jeux fin");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);
            }
        }
        // jeu non trouvé après le for les parcourants
        String error_message_jeu_delete = "Erreur : le jeu n'a pas été trouvé";
        request.setAttribute("message_seance", error_message_jeu_delete);
        this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);
    }
}
