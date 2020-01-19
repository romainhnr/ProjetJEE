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

// servlet permettant de gérer la réinitialisation du mot de passe demandé par les admins
@WebServlet(name = "resetPasswordServlet")
public class resetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User currentUser = (User)request.getServletContext().getAttribute("currentUser");

        // on récupère les champs du formulaire
        String reset_username = request.getParameter("resetUsername");
        String reset_user_password = request.getParameter("resetUserPassword");
        String confirm_reset_user = request.getParameter("confirmUserResetPassword");
        String admin_password = request.getParameter("passwordReset");

        if(!currentUser.getPassword().equals(admin_password) || !reset_user_password.equals(confirm_reset_user)){
            String error_form_password = "Erreur : mauvais mot de passe admin ou champs mdp user correspondent pas";
            request.setAttribute("message_gestionAdherents", error_form_password);
            this.getServletContext().getRequestDispatcher("/resetPassword.jsp").forward(request, response);
        }
        List<User> listUser = (List<User>) request.getServletContext().getAttribute(InitServlet.CONTEXT_USERS);
        for(User user_to_change_pass : listUser){
            if(user_to_change_pass.getLogin().equals(reset_username)){
                user_to_change_pass.setPassword(confirm_reset_user);
                String message_gestionAdherents = "Vous avez bien modifié le mot de passe de l'utilisateur " + user_to_change_pass.getNom();
                request.setAttribute("message_gestionAdherents", message_gestionAdherents);
                listUser.remove(user_to_change_pass);
                listUser.add(user_to_change_pass);
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
                this.getServletContext().getRequestDispatcher("/gestionAdherents.jsp").forward(request, response);
            }
        }
        // utilisateur non trouvé après le for les parcourants
        String error_form_password = "Erreur : utilisateur non trouvé";
        request.setAttribute("message_gestionAdherents", error_form_password);
        this.getServletContext().getRequestDispatcher("/resetPassword.jsp").forward(request, response);




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}