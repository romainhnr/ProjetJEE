package servlet;

import model.Seance;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static servlet.InitServlet.CONTEXT_SEANCES;
import static servlet.InitServlet.CONTEXT_USERS;


//servlet permettant l'inscription à une séance : avant et après le formulaire associé (choix certain et incertain)
@WebServlet(name = "SeanceRegistrationServlet")
public class SeanceRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // inscription séance après formulaire
        Seance seance_to_register = (Seance) request.getServletContext().getAttribute("seance_to_register");
        User currentUser = (User)request.getServletContext().getAttribute("currentUser");
        // récupération des champs du formulaire
        String choixInscription = request.getParameter("choixStatus");
        String choixNbMinInscription = request.getParameter("joueurMin");

        if (choixInscription == null || choixInscription.isBlank() || choixInscription.isEmpty()) {
            String error_message_seance_inscription = "Vous devez sélectionner votre type d'inscription";
            request.setAttribute("message_seance", error_message_seance_inscription);
            this.getServletContext().getRequestDispatcher("/seanceRegistration.jsp").forward(request, response);
        }

        else {
            // si l'utilisateur a choisi certain alors on le rajoute à la liste associée
            if (choixInscription.equals("Certain")){
                seance_to_register.addListUserInscritCertain(currentUser);
                //seance_to_register.addListUserInscrit(currentUser);
                String validation_message_seance_register = "Vous avez été bien inscrit de manière certaine à la séance datant du " + seance_to_register.getDate();
                request.setAttribute("message_seance", validation_message_seance_register);;

                List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(CONTEXT_SEANCES);
                listSeance.remove(seance_to_register);
                listSeance.add(seance_to_register);
                getServletContext().setAttribute(CONTEXT_SEANCES, listSeance);

                this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
            }
            else if(choixInscription.equals("Incertain")){
                if (choixNbMinInscription == null || choixNbMinInscription.isBlank() || choixNbMinInscription.isEmpty()) {
                    String error_message_seance_inscription = "Vous devez sélectionner votre nombre minimum d'inscrit à la séance pour passer certain";
                    request.setAttribute("message_seance", error_message_seance_inscription);
                    this.getServletContext().getRequestDispatcher("/seanceRegistration.jsp").forward(request, response);
                }
                // sinon et après vérification du champ on le rajoute à la liste associée
                else {
                    seance_to_register.addListUserInscritIncertain(currentUser);
                    //seance_to_register.addListUserInscrit(currentUser);
                    currentUser.setNbAdherentMinInscription(Integer.parseInt(choixNbMinInscription));
                    String validation_message_seance_register = "Vous avez été bien inscrit de manière incertaine à la séance datant du " + seance_to_register.getDate();
                    request.setAttribute("message_seance", validation_message_seance_register);

                    List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(CONTEXT_SEANCES);
                    listSeance.remove(seance_to_register);
                    listSeance.add(seance_to_register);
                    getServletContext().setAttribute(CONTEXT_SEANCES, listSeance);

                    List<User> listUser = (List<User>) request.getServletContext().getAttribute(InitServlet.CONTEXT_USERS);
                    listUser.remove(currentUser);
                    listUser.add(currentUser);
                    getServletContext().setAttribute(CONTEXT_USERS, listUser);

                    this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
                }
            }
            else{
                String error_message_seance_inscription = "Erreur : choix non trouvé ";
                request.setAttribute("message_seance", error_message_seance_inscription);
                this.getServletContext().getRequestDispatcher("/seanceRegistration.jsp").forward(request, response);
            }


        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // inscription séance avant formulaire
        String id = request.getParameter("id");
        List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(CONTEXT_SEANCES);
        User currentUser = (User)request.getServletContext().getAttribute("currentUser");
        UUID UUID_id = UUID.fromString(id);
        for (Seance seance_to_register : listSeance) {
            if (seance_to_register.getIdSeance().equals(UUID_id) && currentUser != null) {

                request.getServletContext().setAttribute("seance_to_register", seance_to_register);

                this.getServletContext().getRequestDispatcher("/seanceRegistration.jsp").forward(request, response);
            }

        }
        // séance non trouvée après le for parcourant chacune d'entre elles ou user null
        String error_message_seance_registration = "Erreur : la séance ou l'utilisateur n'a pas été trouvé";
        request.setAttribute("message_seance", error_message_seance_registration);
        this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);

    }
}
