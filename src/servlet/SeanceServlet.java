package servlet;

import model.Seance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "SeanceServlet")
public class SeanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // création séance
        String dateSeance = request.getParameter("dateSeance");
        String heureDebut = request.getParameter("heureDebut");
        String heureFin = request.getParameter("heureFin");

        if (dateSeance == null || dateSeance.isBlank() || dateSeance.isEmpty() || heureDebut == null || heureDebut.isBlank() || heureDebut.isEmpty() || heureFin == null || heureFin.isBlank() || heureFin.isEmpty()) {
            String error_message_seance_creation = "Les champs doivent être remplis";
            request.setAttribute("message_seance", error_message_seance_creation);
            this.getServletContext().getRequestDispatcher("/addSeance.jsp").forward(request, response);
        }
        LocalDate LD_dateSeance = LocalDate.parse(dateSeance);
        LocalTime LT_heureDebut = LocalTime.parse(heureDebut);
        LocalTime LT_heureFin = LocalTime.parse(heureFin);

        if(LT_heureDebut.isAfter(LT_heureFin) || LT_heureDebut.equals(LT_heureFin)){
            String error_message_seance_creation = "L'heure de début doit être ni égale ni inférieure à l'heure de fin";
            request.setAttribute("message_seance", error_message_seance_creation);
            this.getServletContext().getRequestDispatcher("/addSeance.jsp").forward(request, response);
        }
        else if(LD_dateSeance.isBefore(LocalDate.now())){
            String error_message_seance_creation = "La date de la séance doit se situer après la date actuelle";
            request.setAttribute("message_seance", error_message_seance_creation);
            this.getServletContext().getRequestDispatcher("/addSeance.jsp").forward(request, response);
        }
        else {
            Seance newSeance = new Seance(LD_dateSeance, LT_heureDebut, LT_heureFin);
            List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute("listSeance");
            listSeance.add(newSeance);
            getServletContext().setAttribute("listSeance", listSeance);

            //String validation_message_seance_creation = "La séance datant du : " + newSeance.date + " a bien été crée";
            //request.setAttribute("message_seance", validation_message_seance_creation);

            this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // suppression séance
        String id = request.getParameter("id");
        List<Seance> listSeance = (List<Seance>)request.getServletContext().getAttribute("listSeance");
        Seance seance = null;
        UUID UUID_id = UUID.fromString(id);
        for (Seance s : listSeance) {
            if (s.getIdSeance().equals(UUID_id)) {
                seance = s;
                listSeance.remove(s);
                getServletContext().setAttribute("listSeance", listSeance);

                String validation_message_seance_delete = "La séance datant du " + s.date + " a bien été supprimée";
                request.setAttribute("message_seance", validation_message_seance_delete);

                this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);

            }
            else{
                String error_message_seance_delete = "Erreur : la séance n'a pas été trouvée";
                request.setAttribute("message_seance", error_message_seance_delete);
                this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
            }
        }

    }
}
