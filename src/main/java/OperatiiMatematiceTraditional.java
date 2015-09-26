
import db.DbOps;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class OperatiiMatematiceTraditional extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession https = req.getSession();
        if(action!=null && action.equals("REGISTER")) {
            String user = req.getParameter("user");
            String pass = req.getParameter("password");
            String email = req.getParameter("email");
            https.setAttribute("keyUsername", user);
            this.registerUser(user, pass, email);
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/contulmeu.html");
            dispatcher.forward(req, resp);
        }
        else
        {
                 String username =(String) https.getAttribute("keyUsername");
                String followers = req.getParameter("usersToFollow");
                this.addUsers(username, followers);
        }
    }

    protected void registerUser(String user, String pass, String email) {

        // apel db
        DbOps user1 = new DbOps();
        try {
            user1.signUp(user, pass, email);
            System.out.println("am adaugat user" + user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void addUsers(String username, String followers) {
        DbOps user1 = new DbOps();
       try {
            user1.addFollowers(username, followers);
            // System.out.println("");
       } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
