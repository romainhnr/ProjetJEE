package servlet;

import model.Jeux;
import model.User;

import javax.lang.model.type.NullType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "JeuxServlet")
public class JeuxServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // création jeux
        String choixTheme = request.getParameter("choixTheme");
        String titre = request.getParameter("titre");
        String duree = request.getParameter("duree");
        String nbmin = request.getParameter("nbmin");
        String nbmax = request.getParameter("nbmax");
        String description = request.getParameter("description");

        if (titre == null || titre.isBlank() || titre.isEmpty() || description == null || description.isBlank() || description.isEmpty() || duree == null || duree.isBlank() || duree.isEmpty() || nbmin == null || nbmin.isBlank() || nbmin.isEmpty() || nbmax == null || nbmax.isBlank() || nbmax.isEmpty() || choixTheme == null || choixTheme.isBlank() || choixTheme.isEmpty()) {
            String error_message_jeu_creation = "Les champs doivent être remplis";
            request.setAttribute("message_jeu", error_message_jeu_creation);
            this.getServletContext().getRequestDispatcher("/addJeux.jsp").forward(request, response);
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
            this.getServletContext().getRequestDispatcher("/addJeux.jsp").forward(request, response);
        }
        else if(nbminJeu > nbmaxJeu){
            String error_message_jeux_creation = "Le nombre min. doit être inférieur au nombre max.";
            request.setAttribute("message_jeu", error_message_jeux_creation);
            this.getServletContext().getRequestDispatcher("/addJeux.jsp").forward(request, response);
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
                this.getServletContext().getRequestDispatcher("/addJeux.jsp").forward(request, response);
            }

            Jeux newJeux = new Jeux(titreJeu, descriptionJeu, themeJeu, dureeJeu, nbminJeu, nbmaxJeu);
            User current_user = (User)request.getServletContext().getAttribute("user");
            current_user.listeJeux.add(newJeux);

            getServletContext().setAttribute("user", current_user);

            //String validation_message_seance_creation = "La séance datant du : " + newSeance.date + " a bien été crée";
            //request.setAttribute("message_seance", validation_message_seance_creation);

            this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // suppression jeu
        String id = request.getParameter("id");
        User current_user = (User)request.getServletContext().getAttribute("user");
        Jeux jeu = null;
        UUID UUID_id = UUID.fromString(id);
        for (Jeux j : current_user.getJeux()) {
            if (j.getIdJeux().equals(UUID_id)) {
                jeu = j;
                current_user.listeJeux.remove(j);
                getServletContext().setAttribute("user", current_user);

                String validation_message_jeu_delete = "Le jeu se nommant " + j.titre + " a bien été supprimé";
                request.setAttribute("message_jeu", validation_message_jeu_delete);

                this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);

            }
            else{
                String error_message_jeu_delete = "Erreur : le jeu n'a pas été trouvé";
                request.setAttribute("message_seance", error_message_jeu_delete);
                this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);
            }
        }
    }
}
