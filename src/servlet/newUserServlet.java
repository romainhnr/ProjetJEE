package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "newUserServlet")
public class newUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // On initialise les données à utiliser pour les conditions
        User currentUser = (User)request.getServletContext().getAttribute("currentUser");

        String user_role = request.getParameter("role");
        String user_nom = request.getParameter("nom");
        String user_prenom = request.getParameter("prénom");
        String username = request.getParameter("username");
        String new_password_user = request.getParameter("password");
        String confirm_password_user = request.getParameter("confirm_password");

        // si les nouveaux mdp ne correspondent pas alors message erreur
        if(!new_password_user.equals(confirm_password_user)){
            String notEquals_password = "Erreur : les mots de passe ne correspondent pas";
            request.setAttribute("newUser_error_equalsPasswords", notEquals_password);
            this.getServletContext().getRequestDispatcher("/nouveauAdherents.jsp").forward(request, response);
        }
        // Sinon on créer un utilisateur
        else{
            // si la value de la case "role" est user alors on créer un nouveau USER
            if(user_role.equals("USER")){
                // L'utilisateur est créé en USER, il faut le mettre en csv
                User user = new User(username, new_password_user, user_nom, User.Role.USER);

            }
            // si la value de la case "role" est user alors on créer un nouveau ADMIN
            else {
                // L'utilisateur est créé en ADMIN, il faut le mettre en csv
                User user = new User(username, new_password_user, user_nom, User.Role.ADMIN);

            }
        }

        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}