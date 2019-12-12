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

@WebServlet(name = "SeanceServlet")
public class SeanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateSeance = request.getParameter("dateSeance");
        String heureDebut = request.getParameter("heureDebut");
        String heureFin = request.getParameter("heureFin");

        if(dateSeance == null || dateSeance.isBlank() || dateSeance.isEmpty() || heureDebut == null || heureDebut.isBlank() || heureDebut.isEmpty() || heureFin == null || heureFin.isBlank() || heureFin.isEmpty()) {
            String error_message = "Les champs doivent être remplis";
            request.setAttribute("error_message_seance", error_message);
            this.getServletContext().getRequestDispatcher("/addSeance.jsp").forward(request, response);
        }
        else {
            LocalDate LD_dateSeance = LocalDate.parse(dateSeance);
            LocalTime LT_heureDebut = LocalTime.parse(heureDebut);
            LocalTime LT_heureFin = LocalTime.parse(heureFin);

            if(LT_heureDebut.isAfter(LT_heureFin)){
                String error_message = "La date de début doit être inférieure à la date de fin";
                request.setAttribute("error_message_seance", error_message);
                this.getServletContext().getRequestDispatcher("/addSeance.jsp").forward(request, response);
            }

            Seance newSeance = new Seance(LD_dateSeance, LT_heureDebut, LT_heureFin);
            List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute("listSeance");
            listSeance.add(newSeance);
            getServletContext().setAttribute("listSeance", listSeance);

            this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
