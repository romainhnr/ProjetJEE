package servlet;

import model.Message;
import model.Seance;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static servlet.InitServlet.CONTEXT_SEANCES;


// servlet permettant de gérer la création et la suppression d'une séance
@WebServlet(name = "SeanceAdd_RemoveServlet")
public class SeanceAdd_RemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // création séance
        // récupération des champs du formulaire
        String dateSeance = request.getParameter("dateSeance");
        String heureDebut = request.getParameter("heureDebut");
        String heureFin = request.getParameter("heureFin");

        if (dateSeance == null || dateSeance.isBlank() || dateSeance.isEmpty() || heureDebut == null || heureDebut.isBlank() || heureDebut.isEmpty() || heureFin == null || heureFin.isBlank() || heureFin.isEmpty()) {
            String error_message_seance_creation = "Les champs doivent être remplis";
            request.setAttribute("message_seance", error_message_seance_creation);
            this.getServletContext().getRequestDispatcher("/seanceAdd.jsp").forward(request, response);
        }
        LocalDate LD_dateSeance = LocalDate.parse(dateSeance);
        LocalTime LT_heureDebut = LocalTime.parse(heureDebut);
        LocalTime LT_heureFin = LocalTime.parse(heureFin);

        if(LT_heureDebut.isAfter(LT_heureFin) || LT_heureDebut.equals(LT_heureFin)){
            String error_message_seance_creation = "L'heure de début doit être ni égale ni supérieure à l'heure de fin";
            request.setAttribute("message_seance", error_message_seance_creation);
            this.getServletContext().getRequestDispatcher("/seanceAdd.jsp").forward(request, response);
        }
        else if(LD_dateSeance.isBefore(LocalDate.now())){
            String error_message_seance_creation = "La date de la séance doit se situer après la date actuelle";
            request.setAttribute("message_seance", error_message_seance_creation);
            this.getServletContext().getRequestDispatcher("/seanceAdd.jsp").forward(request, response);
        }
        // une fois les champs vérifiés on peut créer la séance
        else {
            Seance newSeance = new Seance(LD_dateSeance, LT_heureDebut, LT_heureFin);
            List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(CONTEXT_SEANCES);
            listSeance.add(newSeance);
            getServletContext().setAttribute(CONTEXT_SEANCES, listSeance);

            String validation_message_seance_creation = "La séance datant du : " + newSeance.getDate() + " a bien été créé";
            request.setAttribute("message_seance", validation_message_seance_creation);

            //sauvegarde des séances
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
                System.out.println("Ecriture fichier seances fin");

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // suppression séance
        String id = request.getParameter("id");
        List<Seance> listSeance = (List<Seance>) request.getServletContext().getAttribute(CONTEXT_SEANCES);
        UUID UUID_id = UUID.fromString(id);

        for (Seance seance_to_delete : listSeance) {
            if (seance_to_delete.getIdSeance().equals(UUID_id)) {

                listSeance.remove(seance_to_delete);
                getServletContext().setAttribute(CONTEXT_SEANCES, listSeance);

                //messagerie
                User currentUser = (User)request.getServletContext().getAttribute("currentUser");
                if(seance_to_delete.getListUserInscritCertain().contains(currentUser) || seance_to_delete.getListUserInscritIncertain().contains(currentUser)){
                    Message messageDeleteSeance = new Message("La séance datant du " + seance_to_delete.getDate() + " a été supprimée.\nVous êtes donc désinscrit de celle-ci. En nous excusant de la gêne occassionée.");
                    currentUser.addListeMessages(messageDeleteSeance);
                    getServletContext().setAttribute("currentUser", currentUser);
                    String message_alert = "Vous avez reçu un nouveau message ! <a href='messagerie.jsp'>cliquer-ici</a> pour accéder à votre messagerie";
                    request.setAttribute("message_alert", message_alert);

                    // on sauvegarde les messages
                    try {
                        System.out.println("Ecriture fichier messages début");
                        BufferedWriter writer = Files.newBufferedWriter(Path.of(getServletContext().getRealPath("data/messages.csv")));

                        for (Message msg_to_save : currentUser.getListeMessages()) {
                            writer.write(msg_to_save.getTexteMessage()+",");
                            writer.write(msg_to_save.getDateTimeMessage()+",");
                            writer.write(msg_to_save.getEstLu().toString());
                            writer.newLine();
                        }

                        writer.close();
                        System.out.println("Ecriture fichier messages fin");

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                String validation_message_seance_delete = "La séance datant du " + seance_to_delete.getDate() + " a bien été supprimée";
                request.setAttribute("message_seance", validation_message_seance_delete);

                // on sauvegarde les séances après la suppression d'une d'entre elles
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
        // séance non trouvée après le for parcourant chacune d'entre elles
        String error_message_seance_delete = "Erreur : la séance n'a pas été trouvée";
        request.setAttribute("message_seance", error_message_seance_delete);
        this.getServletContext().getRequestDispatcher("/seance.jsp").forward(request, response);

    }
}
