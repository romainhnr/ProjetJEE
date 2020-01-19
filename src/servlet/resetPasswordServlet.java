package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "resetPasswordServlet")
public class resetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User currentUser = (User)request.getServletContext().getAttribute("currentUser");

        String reset_username = request.getParameter("resetUsername");
        String reset_user_password = request.getParameter("resetUserPassword");
        String confirm_reset_user = request.getParameter("confirmUserResetPassword");
        String admin_password = request.getParameter("passwordReset");

        if(!currentUser.getPassword().equals(admin_password) || !reset_user_password.equals(confirm_reset_user) || !currentUser.getLogin().equals(reset_username)){
            String mauvaise_combinaison = "Erreur : mauvais mot de passe admin ou user ou mauvais login";
            request.setAttribute("admin_error_password", mauvaise_combinaison);
            this.getServletContext().getRequestDispatcher("/resetPassword.jsp").forward(request, response);
        }
        else {
            // Ecrire le reset / changement de password sur le csv
        }

        this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}