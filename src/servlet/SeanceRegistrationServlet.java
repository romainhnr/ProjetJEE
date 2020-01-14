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

@WebServlet(name = "SeanceRegistrationServlet")
public class SeanceRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // inscription séance après formulaire
        Seance seance_to_register = (Seance) request.getServletContext().getAttribute("seance_to_register");
        User currentUser = (User)request.getServletContext().getAttribute("user");

        String choixInscription = request.getParameter("choixInscription");
        String choixNbMinInscription = request.getParameter("choixNbMinInscription");

        if (choixInscription == null || choixInscription.isBlank() || choixInscription.isEmpty()) {
            String error_message_seance_inscription = "Vous devez sélection votre type d'inscription";
            request.setAttribute("message_seance", error_message_seance_inscription);
            this.getServletContext().getRequestDispatcher("/seanceRegistration.jsp").forward(request, response);
        }

        else {
            if (choixInscription.equals("Certain")){
                seance_to_register.addListUserInscritCertain(currentUser);
            }
            else if(choixInscription.equals("Incertain")){
                if (choixNbMinInscription == null || choixNbMinInscription.isBlank() || choixNbMinInscription.isEmpty()) {
                    String error_message_seance_inscription = "Vous devez sélection votre nombre minimum d'inscrit à la séance pour passer certain";
                    request.setAttribute("message_seance", error_message_seance_inscription);
                    this.getServletContext().getRequestDispatcher("/seanceRegistration.jsp").forward(request, response);
                }
                else {
                    seance_to_register.addListUserInscritIncertain(currentUser);
                    currentUser.setNbAdherentMinInscription(Integer.parseInt(choixNbMinInscription));
                }
            }
            else{
                String error_message_seance_inscription = "Erreur : choix non trouvé ";
                request.setAttribute("message_seance", error_message_seance_inscription);
                this.getServletContext().getRequestDispatcher("/seanceRegistration.jsp").forward(request, response);
            }

            //List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute("listSeance");
            //listSeance.add(seance_to_modify);
            //getServletContext().setAttribute("listSeance", listSeance);

            String validation_message_seance_register = "Vous avez été bien inscrit à la séance datant du " + seance_to_register.getDate();
            request.setAttribute("message_seance", validation_message_seance_register);

            this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // inscription séance avant formulaire
        String id = request.getParameter("id");
        List<Seance> listSeance = (List<Seance>)request.getServletContext().getAttribute("listSeance");
        User currentUser = (User)request.getServletContext().getAttribute("user");
        UUID UUID_id = UUID.fromString(id);
        for (Seance seance_to_register : listSeance) {
            if (seance_to_register.getIdSeance().equals(UUID_id) && currentUser != null) {

                request.getServletContext().setAttribute("seance_to_register", seance_to_register);

                this.getServletContext().getRequestDispatcher("/seanceRegistration.jsp").forward(request, response);

                /*s.addListUserInscritCertain(currentUser);
                getServletContext().setAttribute("listSeance", listSeance);
                getServletContext().setAttribute("user", currentUser);
                String validation_message_seance_registration = "Vous vous êtes bien inscrit de manière certaine à la séance datant du " + s.getDate();
                request.setAttribute("message_seance", validation_message_seance_registration);

                this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);*/

            }
            else{
                String error_message_seance_registration = "Erreur : la séance ou l'utilisateur n'a pas été trouvé";
                request.setAttribute("message_seance", error_message_seance_registration);
                this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
            }
        }

    }
}
