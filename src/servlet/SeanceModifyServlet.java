package servlet;

import model.Message;
import model.Seance;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static servlet.InitServlet.CONTEXT_SEANCES;

@WebServlet(name = "SeanceModifyServlet")
public class SeanceModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // modification séance après formulaire
        String heureDebut = request.getParameter("heureDebut");
        String heureFin = request.getParameter("heureFin");

        if (heureDebut == null || heureDebut.isBlank() || heureDebut.isEmpty() || heureFin == null || heureFin.isBlank() || heureFin.isEmpty()) {
            String error_message_seance_creation = "Les champs doivent être remplis";
            request.setAttribute("message_seance", error_message_seance_creation);
            this.getServletContext().getRequestDispatcher("/seanceModify.jsp").forward(request, response);
        }
        LocalTime LT_heureDebut = LocalTime.parse(heureDebut);
        LocalTime LT_heureFin = LocalTime.parse(heureFin);

        if(LT_heureDebut.isAfter(LT_heureFin) || LT_heureDebut.equals(LT_heureFin)){
            String error_message_seance_creation = "L'heure de début doit être ni égale ni supérieure à l'heure de fin";
            request.setAttribute("message_seance", error_message_seance_creation);
            this.getServletContext().getRequestDispatcher("/seanceModify.jsp").forward(request, response);
        }
        else {
            Seance seance_to_modify = (Seance) request.getServletContext().getAttribute("seance_to_modify");
            seance_to_modify.setHoraireDebut(LT_heureDebut);
            seance_to_modify.setHoraireFin(LT_heureFin);

            List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(CONTEXT_SEANCES);
            listSeance.remove(seance_to_modify);
            listSeance.add(seance_to_modify);
            getServletContext().setAttribute(CONTEXT_SEANCES, listSeance);

            //messagerie
            User currentUser = (User)request.getServletContext().getAttribute("currentUser");
            if(seance_to_modify.getListUserInscritCertain().contains(currentUser) || seance_to_modify.getListUserInscritIncertain().contains(currentUser)){
                Message messageModifySeance = new Message("La séance datant du " + seance_to_modify.getDate() + " a été modifié. Les horaires sont maintenant : " + seance_to_modify.getHoraireDebut() + " à " + seance_to_modify.getHoraireFin() + ".<br/> Si vous voulez annuler votre inscription <a href='unregistration_seance?id=" + seance_to_modify.getIdSeance() +"'>cliquer-ici</a>. En nous excusant de la gêne occassionée.");
                currentUser.addListeMessages(messageModifySeance);
                getServletContext().setAttribute("currentUser", currentUser);
                String message_alert = "Vous avez reçu un nouveau message !";
                request.setAttribute("message_alert", message_alert);
            }

            String validation_message_seance_modify = "La séance datant du " + seance_to_modify.getDate() + " a bien été modifié";
            request.setAttribute("message_seance", validation_message_seance_modify);

            // sauvegarde
            try {
                System.out.println("Ecriture fichier séances début");
                BufferedWriter writer = Files.newBufferedWriter(Path.of(getServletContext().getRealPath("data/seances.csv")));

                for (Seance seance_to_save : listSeance) {
                    writer.write(seance_to_save.getDate()+",");
                    writer.write(seance_to_save.getHoraireDebut()+",");
                    writer.write(seance_to_save.getHoraireFin().toString());

                    writer.newLine();
                }

                writer.close();
                System.out.println("Ecriture fichier séances fin");

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // modification séance avant formulaire
        String id = request.getParameter("id");
        List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(CONTEXT_SEANCES);
        UUID UUID_id = UUID.fromString(id);

        for (Seance seance_to_modify : listSeance) {
            if (seance_to_modify.getIdSeance().equals(UUID_id)) {

                request.getServletContext().setAttribute("seance_to_modify", seance_to_modify);

                this.getServletContext().getRequestDispatcher("/seanceModify.jsp").forward(request, response);

            }
            else{
                String error_message_seance_delete = "Erreur : la séance n'a pas été trouvée";
                request.setAttribute("message_seance", error_message_seance_delete);
                this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
            }
        }
    }
}
