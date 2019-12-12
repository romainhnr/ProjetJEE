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
        LocalDate LD_dateSeance = LocalDate.parse(dateSeance);
        LocalTime LT_heureDebut = LocalTime.parse(heureDebut);
        LocalTime LT_heureFin = LocalTime.parse(heureFin);

        Seance newSeance = new Seance(LD_dateSeance, LT_heureDebut, LT_heureFin);
        //List<Seance> listSeance = (List<Seance>) (session.getAttribute("listSeance"));

        List<Seance> listeSeance = new ArrayList<Seance>();
        listeSeance.add(newSeance);

        HttpSession session = request.getSession();
        session.setAttribute("listeSeance", listeSeance);

        this.getServletContext().getRequestDispatcher( "/seance.jsp" ).forward( request, response );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
