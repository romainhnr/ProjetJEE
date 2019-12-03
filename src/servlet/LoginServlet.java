package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("admin") && (password.equals("root")))
        {
            HttpSession session = request.getSession();
            //User user1 = new User("jean", User.Role.ADMIN);
            //session.setAttribute("user", user1);
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
        }
        else if(login.equals("toto") && (password.equals("root")))
        {
            HttpSession session = request.getSession();
            //User user2 = new User("toto", User.Role.USER);
            //session.setAttribute("user", user2);
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
        }
        else{
            String error_message = "Login et/ou mot de passe incorrect !";
            request.setAttribute("message", error_message);
            this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
