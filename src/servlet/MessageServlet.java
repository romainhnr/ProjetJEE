package servlet;


import model.Message;
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
import java.util.List;
import java.util.UUID;

//servlet gérant la messagerie
@WebServlet(name = "MessageServlet")
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //si l'utilisateur arrive directement sans avoir cliqué sur "marqué comme lu" alors on affiche la messagerie normalement
        String id = request.getParameter("id");
        if(id == null){
            this.getServletContext().getRequestDispatcher("/messagerie.jsp").forward(request, response);
        }
        //sinon on traite la demande
        else {
            User currentUser = (User) request.getServletContext().getAttribute("currentUser");
            List<Message> currentListMessages = currentUser.getListeMessages();
            UUID UUID_id = UUID.fromString(id);
            for (Message message_make_read : currentListMessages) {
                if (message_make_read.getIdMessage().equals(UUID_id)) {
                    if(message_make_read.getEstLu()){
                        message_make_read.setEstLu(false);
                    }
                    else {
                        message_make_read.setEstLu(true);
                    }
                    //sauvegarde des messages après modifications
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
            }
            this.getServletContext().getRequestDispatcher("/messagerie.jsp").forward(request, response);
        }
    }
}
