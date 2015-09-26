import db.DbOps;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by nemes_000 on 26.09.2015.
 */
public class Posteaza extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username;
        HttpSession https = req.getSession();
        username = (String)https.getAttribute("keyUsername");

        if(username == null) {
            System.out.println("userul nu este logat");
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/login.html");
            dispatcher.forward(req, resp);

        }
        else {

            // mesaj
            String post = req.getParameter("post");
            if (post != null && !post.equals("")) {
                System.out.println("userul a postat un mesaj si nu-i gol");

                DbOps user1 = new DbOps();
                try {
                    user1.AddPost(username, post);
                    System.out.println("am adaugat o postare");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
