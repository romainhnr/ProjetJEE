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
import javax.servlet.ServletConfig;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Seance seanceTest = new Seance(LocalDate.of(2020, 01, 01), LocalTime.of(15, 05), LocalTime.of(16, 05));
        List<Seance> listSeance = new ArrayList<>();
        listSeance.add(seanceTest);

        if (login.equals("admin") && (password.equals("root")))
        {
            HttpSession session = request.getSession();
            User user1 = new User("jean", "root", "jean", User.Role.ADMIN);
            //session.setAttribute("user", user1);
            getServletContext().setAttribute("user", user1);
            getServletContext().setAttribute("listSeance", listSeance);

            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
        }
        else if(login.equals("toto") && (password.equals("root")))
        {
            HttpSession session = request.getSession();
            User user2 = new User("toto", "root", "toto", User.Role.USER);
            //session.setAttribute("user", user2);


            getServletContext().setAttribute("listSeance", listSeance);

            Jeux jeux1test = new Jeux("Loup garou", "jeux de loup garou", Jeux.Theme.STRATEGIE, 20, 2, 10);
            Jeux jeux2test = new Jeux("Monopoly", "jeux de monopoly", Jeux.Theme.AMBIANCE, 30, 2, 5);
            user2.addListeJeux(jeux1test);
            user2.addListeJeux(jeux2test);

            getServletContext().setAttribute("user", user2);
            session.setAttribute("userS", user2);
            session.setAttribute("listMessages", user2.getListeMessages());

            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
        }
        else{
            String error_message = "Login et/ou mot de passe incorrect !";
            request.setAttribute("error_message_login", error_message);
            this.getServletContext().getRequestDispatcher( "/login.jsp" ).forward( request, response );
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
