package servlet;

import model.Seance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static servlet.InitServlet.CONTEXT_SEANCES;

// servlet gérant la page avec les détails d'une séance : avec la liste des inscrits, les jeux demandés pour la séance
@WebServlet(name = "SeanceDetailsServlet")
public class SeanceDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(CONTEXT_SEANCES);
        UUID UUID_id = UUID.fromString(id);

        for (Seance seanceDetails_to_show : listSeance) {
            if (seanceDetails_to_show.getIdSeance().equals(UUID_id)) {

                request.getServletContext().setAttribute("seanceDetails_to_show", seanceDetails_to_show);

                this.getServletContext().getRequestDispatcher("/seanceDetails.jsp").forward(request, response);
            }

        }
        // séance non trouvée après le for parcourant chacune d'entre elles
        String error_message_seance_delete = "Erreur : la séance n'a pas été trouvée";
        request.setAttribute("message_seance", error_message_seance_delete);
        this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
    }
}
