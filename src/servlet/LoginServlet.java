package servlet;

import model.Jeux;
import model.Seance;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Seance seance1 = new Seance(LocalDate.now(), LocalTime.now(), LocalTime.now());
        Seance seance2 = new Seance(LocalDate.of(2020, 01, 01), LocalTime.of(15, 05), LocalTime.of(16, 05));
        List<Seance> listSeance = new ArrayList<>();
        listSeance.add(seance1);
        listSeance.add(seance2);


        if (login.equals("admin") && (password.equals("root")))
        {
            HttpSession session = request.getSession();
            User user1 = new User("jean", "root", "jean", User.Role.ADMIN);
            session.setAttribute("user", user1);
            session.setAttribute("listSeance", listSeance);
            this.getServletContext().getRequestDispatcher( "/navbar.jsp" ).forward( request, response );
        }
        else if(login.equals("toto") && (password.equals("root")))
        {
            HttpSession session = request.getSession();
            User user2 = new User("toto", "root", "toto", User.Role.USER);
            session.setAttribute("user", user2);
            session.setAttribute("listSeance", listSeance);

            this.getServletContext().getRequestDispatcher( "/navbar.jsp" ).forward( request, response );
        }
        else{
            String error_message = "Login et/ou mot de passe incorrect !";
            request.setAttribute("message", error_message);
            this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
