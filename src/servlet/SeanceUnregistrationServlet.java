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

@WebServlet(name = "SeanceUnregistrationServlet")
public class SeanceUnregistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // désinscription séance
        String id = request.getParameter("id");
        List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(CONTEXT_SEANCES);
        User currentUser = (User)request.getServletContext().getAttribute("currentUser");
        UUID UUID_id = UUID.fromString(id);

        for (Seance seance_to_unregister : listSeance) {
            if (seance_to_unregister.getIdSeance().equals(UUID_id) && currentUser != null) {

                List<User> listUserRegisteredCertain = seance_to_unregister.getListUserInscritCertain();
                List<User> listUserRegisteredUncertain = seance_to_unregister.getListUserInscritIncertain();

                if (listUserRegisteredCertain.contains(currentUser)){
                    seance_to_unregister.removeListUserInscritCertain(currentUser);

                }
                else if (listUserRegisteredUncertain.contains(currentUser)){
                    seance_to_unregister.removeListUserInscritIncertain(currentUser);
                }
                else {
                    String error_message_seance_unregistration = "Erreur : Vous n'êtes pas inscrit à la séance";
                    request.setAttribute("message_seance", error_message_seance_unregistration);
                    this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
                }

                getServletContext().setAttribute(CONTEXT_SEANCES , listSeance);
                getServletContext().setAttribute("currentUser", currentUser);

                String validation_message_seance_registration = "Vous vous êtes bien désinscrit de la séance datant du " + seance_to_unregister.getDate();
                request.setAttribute("message_seance", validation_message_seance_registration);

                this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);

            }
            else{
                String error_message_seance_unregistration = "Erreur : la séance ou l'utilisateur n'a pas été trouvé";
                request.setAttribute("message_seance", error_message_seance_unregistration);
                this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
            }
        }

    }
}
