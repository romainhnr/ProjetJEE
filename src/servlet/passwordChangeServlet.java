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

        String actual_password = request.getParameter("mdp");

        String new_password = request.getParameter("new_password");
        String confirm_password = request.getParameter("confirm_password");
        User currentUser = (User)request.getServletContext().getAttribute("currentUser");

        if(!currentUser.getPassword().equals(actual_password)){
            // message erreur
            String mauvais_password = "Erreur : mauvais mot de passe";
            request.setAttribute("message_error_password", mauvais_password);
            this.getServletContext().getRequestDispatcher("/newPassword.jsp").forward(request, response);
        }
        if(!new_password.equals(confirm_password)){
            // message erreur
            String notEquals_password = "Erreur : les mots de passe ne sont pas les même";
            request.setAttribute("message_error_equalsPasswords", notEquals_password);
            this.getServletContext().getRequestDispatcher("/newPassword.jsp").forward(request, response);
        }
        if(currentUser.getPassword().equals(actual_password) && new_password.equals(confirm_password)){
            currentUser.setPassword(new_password);
        }

        this.getServletContext().getRequestDispatcher("/profil.jsp").forward(request, response);
    }
}