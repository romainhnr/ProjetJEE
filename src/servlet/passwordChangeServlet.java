package servlet;

import model.Jeux;
import model.Message;
import model.Seance;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static servlet.InitServlet.CONTEXT_SEANCES;

@WebServlet(name = "passwordChangeServlet")
public class passwordChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // On initiale les données dont on a besoin
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
            String notEquals_password = "Erreur : les mots de passe ne sont pas les même";
            request.setAttribute("message_error_equalsPasswords", notEquals_password);
            this.getServletContext().getRequestDispatcher("/newPassword.jsp").forward(request, response);
        }
        // Si tout correspond alors, on change le mot de passe
        if(currentUser.getPassword().equals(actual_password) && new_password.equals(confirm_password)){
            currentUser.setPassword(new_password);
            // écrire les modifs sur csv
        }

        this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);
    }
}