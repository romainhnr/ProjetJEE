package servlet;

import model.Jeux;
import model.Message;
import model.Seance;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InitServlet")
public class InitServlet extends HttpServlet {

    public static final String CONTEXT_SEANCES = "CONTEXT_SEANCES";
    public static final String CONTEXT_USERS = "CONTEXT_USERS";
    public static final String CONTEXT_MESSAGES = "CONTEXT_MESSAGES";
    public static final String CONTEXT_JEUX = "CONTEXT_JEUX";

    @Override
    public void init(ServletConfig config) throws ServletException {
        String usersFilePath = config.getInitParameter("users-path");
        if (usersFilePath == null) {
            throw new IllegalArgumentException("Le paramètre d'initialisation [users-path] n'est pas renseigné");
        }
        String seancesFilePath = config.getInitParameter("seances-path");
        if (seancesFilePath == null) {
            throw new IllegalArgumentException("Le paramètre d'initialisation [seances-path] n'est pas renseigné");
        }
        String jeuxFilePath = config.getInitParameter("jeux-path");
        if (jeuxFilePath == null) {
            throw new IllegalArgumentException("Le paramètre d'initialisation [jeux-path] n'est pas renseigné");
        }
        String messagesFilePath = config.getInitParameter("messages-path");
        if (messagesFilePath == null) {
            throw new IllegalArgumentException("Le paramètre d'initialisation [messages-path] n'est pas renseigné");
        }

        // Chargement des users
        List<User> users = new ArrayList<User>();
        System.out.println("Début du chargement des users");
        try {
            List<String> lines = Files.readAllLines(Path.of(config.getServletContext().getRealPath(usersFilePath)));
            for (String line : lines) {
                try {
                    User user = parseUserFromLine(line);
                    users.add(user);
                } catch (Exception e) {
                    System.err.printf("Impossible de parser le user pour la ligne [%s]\n", line);
                }
            }
            System.out.printf("Chargement de %d users réalisé\n", users.size());
        } catch (IOException e) {
            throw new ServletException("Erreur à la lecture des users", e);
        }
        // Sauvegarde des users dans le scope "application" (ServletContext) sous la constante CONTEXT_USERS
        config.getServletContext().setAttribute(CONTEXT_USERS, users);

        // Chargement des séances
        List<Seance> seances = new ArrayList<Seance>();
        System.out.println("Début du chargement des séances");
        try {
            List<String> lines = Files.readAllLines(Path.of(config.getServletContext().getRealPath(seancesFilePath)));
            for (String line : lines) {
                try {
                    Seance seance = parseSeanceFromLine(line);
                    seances.add(seance);
                } catch (Exception e) {
                    System.err.printf("Impossible de parser la séance pour la ligne [%s]\n", line);
                }
            }
            System.out.printf("Chargement de %d séances réalisé\n", seances.size());
        } catch (IOException e) {
            throw new ServletException("Erreur à la lecture des séances", e);
        }

        // Sauvegarde des séances dans le scope "application" (ServletContext) sous la constante CONTEXT_SEANCES
        config.getServletContext().setAttribute(CONTEXT_SEANCES, seances);

        // Chargement des jeux
        List<Jeux> jeux = new ArrayList<Jeux>();
        System.out.println("Début du chargement des jeux");
        try {
            List<String> lines = Files.readAllLines(Path.of(config.getServletContext().getRealPath(jeuxFilePath)));
            for (String line : lines) {
                try {
                    Jeux jeu = parseJeuxFromLine(line);
                    jeux.add(jeu);
                } catch (Exception e) {
                    System.err.printf("Impossible de parser le jeu pour la ligne [%s]\n", line);
                }
            }
            System.out.printf("Chargement de %d jeux réalisés\n", jeux.size());
        } catch (IOException e) {
            throw new ServletException("Erreur à la lecture des jeux", e);
        }
        // Sauvegarde des jeux dans le scope "application" (ServletContext) sous la constante CONTEXT_JEUX
        config.getServletContext().setAttribute(CONTEXT_JEUX, jeux);

        // Chargement des messages
        List<Message> messages = new ArrayList<Message>();
        System.out.println("Début du chargement des messages");
        try {
            List<String> lines = Files.readAllLines(Path.of(config.getServletContext().getRealPath(messagesFilePath)));
            for (String line : lines) {
                try {
                    Message message = parseMessageFromLine(line);
                    messages.add(message);
                } catch (Exception e) {
                    System.err.printf("Impossible de parser le message pour la ligne [%s]\n", line);
                }
            }
            System.out.printf("Chargement de %d messages réalisés\n", messages.size());
        } catch (IOException e) {
            throw new ServletException("Erreur à la lecture des messages", e);
        }
        // Sauvegarde des messages dans le scope "application" (ServletContext) sous la constante CONTEXT_MESSAGES
        config.getServletContext().setAttribute(CONTEXT_MESSAGES, messages);


    }

    private User parseUserFromLine(String line) {
        String[] elements = line.split(",");

        String login = elements[0];
        String password = elements[1];
        String nom = elements[2];
        String roleS = elements[3];

        if(roleS.equals("admin")){
            User.Role role = User.Role.ADMIN;
            return new User(login, password, nom, role);
        }
        else{
            return new User(login, password, nom, User.Role.USER);
        }


    }

    private Seance parseSeanceFromLine(String line) {
        String[] elements = line.split(",");

        String dateS = elements[0];
        String hoursDS = elements[1];
        String hourFS = elements[2];;

        LocalDate dateD = LocalDate.parse(dateS);
        LocalTime hoursDD = LocalTime.parse(hoursDS);
        LocalTime hourFD = LocalTime.parse(hourFS);



        return new Seance(dateD, hoursDD, hourFD);
    }

    private Jeux parseJeuxFromLine(String line) {
        String[] elements = line.split(",");

        String titre = elements[0];
        String description = elements[1];
        String themeS = elements[2];
        String dureeS = elements[3];
        String nbJoueurMinS = elements[4];
        String nbJoueurMaxS = elements[5];

        Integer dureeJeu = Integer.parseInt(dureeS);
        Integer nbminJeu = Integer.parseInt(nbJoueurMinS);
        Integer nbmaxJeu = Integer.parseInt(nbJoueurMaxS);

        if(themeS.equals("ambiance")){
            Jeux.Theme theme = Jeux.Theme.AMBIANCE;
            return new Jeux(titre, description, theme, dureeJeu, nbminJeu, nbmaxJeu);
        }
        else if(themeS.equals("strategie")){
            Jeux.Theme theme = Jeux.Theme.STRATEGIE;
            return new Jeux(titre, description, theme, dureeJeu, nbminJeu, nbmaxJeu);
        }
        else{
            return new Jeux(titre, description, Jeux.Theme.ROLE, dureeJeu, nbminJeu, nbmaxJeu);
        }

    }

    private Message parseMessageFromLine(String line) {
        String[] elements = line.split(",");

        String message = elements[0];

        return new Message(message);


    }
}
