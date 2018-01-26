package servlets;



import factory.EJBFactory;
import service.VisitorCounterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ariana on 2018/1/5.
 */
@WebServlet(name = "servlet.LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private VisitorCounterService visitorCounterService =
            (VisitorCounterService) EJBFactory.getEJB("VisitorCounterServiceBean","VisitorCounterService");


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            session.invalidate();
            visitorCounterService.reduceOnlineUser();
            visitorCounterService.raiseOnlineVisitor();
            System.out.println("logout");
            PrintWriter out = response.getWriter();
            out.print("logout");
        }
    }
}
