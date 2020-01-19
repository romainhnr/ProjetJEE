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


//servlet permettant de gérer la demande de changement de mot de passe de l'utilisateur
@WebServlet(name = "passwordChangeServlet")
public class passwordChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = (List<User>) request.getServletContext().getAttribute(InitServlet.CONTEXT_USERS);

        // On récupère les données du formulaire
        String actual_password = request.getParameter("mdp");
        String new_password = request.getParameter("new_password");
        String confirm_password = request.getParameter("confirm_password");

        User currentUser = (User)request.getServletContext().getAttribute("currentUser");

        // Si mdp admin ne correspond pas alors erreur
        if(!currentUser.getPassword().equals(actual_password)){
            // message erreur
            String mauvais_password = "Erreur : mauvais mot de passe";
            request.setAttribute("message_error_password", mauvais_password);
            this.getServletContext().getRequestDispatcher("/newPassword.jsp").forward(request, response);
        }

        // si les champs du nouveau mdp ne correspondent pas alors erreur
        if(!new_password.equals(confirm_password)){
            // message erreur
            String notEquals_password = "Erreur : les mots de passe ne sont pas les mêmes";
            request.setAttribute("message_error_equalsPasswords", notEquals_password);
            this.getServletContext().getRequestDispatcher("/newPassword.jsp").forward(request, response);
        }
        // Si tout correspond alors, on change le mot de passe
        if(currentUser.getPassword().equals(actual_password) && new_password.equals(confirm_password)){
            currentUser.setPassword(new_password);
            String message_mdp = "Vous avez bien modifié votre mot de passe";
            request.setAttribute("message_mdp", message_mdp);
            listUser.remove(currentUser);
            listUser.add(currentUser);
            getServletContext().setAttribute(InitServlet.CONTEXT_USERS, listUser);

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

        this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);
    }
}