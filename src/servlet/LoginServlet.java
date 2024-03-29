package servlet;

import model.Jeux;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

// servlet gérant la connexion au site
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // récupération des champs du formulaire
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User currentUser = null;

        List<User> listUser = (List<User>) request.getServletContext().getAttribute(InitServlet.CONTEXT_USERS);
        for (User user : listUser) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                currentUser = user;
            }
        }
        // si user trouvé alors connexion
        if (currentUser != null){
            HttpSession session = request.getSession();

            // on attribue la liste de jeux, prélablement stocké dans le scope application après lecture du csv, à l'user courant
            List<Jeux> listJeux = (List<Jeux>) request.getServletContext().getAttribute(InitServlet.CONTEXT_JEUX);
            for (Jeux jeu_to_load : listJeux) {
                currentUser.addListeJeux(jeu_to_load);
            }
            // idem qu'au dessus mais pour les messages
            List<Message> listMessages = (List<Message>) request.getServletContext().getAttribute(InitServlet.CONTEXT_MESSAGES);
            for (Message msg_to_load : listMessages) {
                currentUser.addListeMessages(msg_to_load);
            }
            // on stocke l'user courant dans le scope application pour pouvoir aisément y accéder plus tard
            getServletContext().setAttribute("currentUser", currentUser);

            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
        }
        // sinon erreur
        else{
            String error_message = "Login et/ou mot de passe incorrect !";
            request.setAttribute("error_message_login", error_message);
            this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
