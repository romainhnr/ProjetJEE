package servlet;

import model.Message;
import model.Seance;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "MessageServlet")
public class MessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if(id == null){
            this.getServletContext().getRequestDispatcher("/messagerie.jsp").forward(request, response);
        }
        else {
            User currentUser = (User) request.getServletContext().getAttribute("user");
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

                }
            }
            this.getServletContext().getRequestDispatcher("/messagerie.jsp").forward(request, response);
        }
    }
}
