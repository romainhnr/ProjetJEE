package servlet;

import model.Jeux;
import model.Seance;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

// servlet gérant la demande d'un jeu à une séance
@WebServlet(name = "askGameServlet")
public class askGameServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User currentUser = (User)request.getServletContext().getAttribute("currentUser");
            String id = request.getParameter("id");
            UUID UUID_id = UUID.fromString(id);

            for(Jeux jeu_demande : currentUser.getJeux()){
                if (jeu_demande.getIdJeux().equals(UUID_id)) {
                    Seance seanceDetails_to_show = (Seance) request.getServletContext().getAttribute("seanceDetails_to_show");
                    if(seanceDetails_to_show.getSeanceListeJeux().contains(jeu_demande)){
                        String error_message_seance_registration = "Erreur : le jeu est déjà demandé";
                        request.setAttribute("message_seance", error_message_seance_registration);
                        this.getServletContext().getRequestDispatcher("/seanceDetails.jsp").forward(request, response);
                    }
                    else {
                        seanceDetails_to_show.addSeanceListeJeux(jeu_demande);

                        String message_seance = "Votre demande d'ajout de jeu à la séance a bien été prise en compte";
                        request.setAttribute("message_seance", message_seance);

                        this.getServletContext().getRequestDispatcher("/seanceDetails.jsp").forward(request, response);
                    }
                }
            }
            // jeu non trouvé après le for les parcourants
            String error_message_seance_registration = "Erreur : le jeu n'a pas été trouvé";
            request.setAttribute("message_seance", error_message_seance_registration);
            this.getServletContext().getRequestDispatcher("/seanceDetails.jsp").forward(request, response);

        }
    }