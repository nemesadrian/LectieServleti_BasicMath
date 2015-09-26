
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


public class OperatiiMatematiceTraditional extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out =resp.getWriter();

        String user=req.getParameter("user");
        String pass=req.getParameter("password");
        String email=req.getParameter("email");


        // apel db




        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calcule </title>");
        out.println("</head>");

        out.println("<body>");


        out.println("Ai introdus: <b>" + user + "</br>" + pass + "</br>" + email + "</b>");

        out.close();



        out.println("</body>");
    }
}