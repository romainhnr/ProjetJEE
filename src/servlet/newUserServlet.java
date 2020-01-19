package servlet;

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

// servlet permettant de créer un nouvel utilisateur demandé par les admins
@WebServlet(name = "newUserServlet")
public class newUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> listUser = (List<User>) request.getServletContext().getAttribute(InitServlet.CONTEXT_USERS);

        // On récupère les données du formulaire
        String role = request.getParameter("role");
        String nom = request.getParameter("nom");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");

        // si les nouveaux mdp ne correspondent pas alors message erreur
        if(!password.equals(confirm_password)){
            String notEquals_password = "Erreur : les mots de passe ne correspondent pas";
            request.setAttribute("newUser_error_equalsPasswords", notEquals_password);
            this.getServletContext().getRequestDispatcher("/nouveauAdherents.jsp").forward(request, response);
        }

        // Sinon on créer un utilisateur
        else{
            // si la value de la case "role" est user alors on créer un nouveau USER
            if(role.equals("USER")){
                // L'utilisateur est créé en USER
                User new_user = new User(login, confirm_password, nom, User.Role.USER);
                listUser.add(new_user);
            }
            // si la value de la case "role" est user alors on créer un nouveau ADMIN
            else {
                // L'utilisateur est créé en ADMIN
                User new_user = new User(login, confirm_password, nom, User.Role.ADMIN);
                listUser.add(new_user);
            }
            getServletContext().setAttribute(InitServlet.CONTEXT_USERS, listUser);

            String message_gestionAdherents = "Vous avez bien créé l'utilisateur : " + nom;
            request.setAttribute("message_gestionAdherents", message_gestionAdherents);

            //sauvegarde des users
            try {
                System.out.println("Ecriture fichier users début");
                BufferedWriter writer = Files.newBufferedWriter(Path.of(getServletContext().getRealPath("data/users.csv")));

                for (User user_to_save : listUser) {
                    writer.write(user_to_save.getLogin()+",");
                    writer.write(user_to_save.getPassword()+",");
                    writer.write(user_to_save.getNom()+",");
                    writer.write(user_to_save.getRole().toString().toLowerCase());

                    writer.newLine();
                }

                writer.close();
                System.out.println("Ecriture fichier users fin");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.getServletContext().getRequestDispatcher("/gestionAdherents.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}