package servlet;

import dao.OrderDao;
import dao.CustomerDao;
import factory.DaoFactory;
import factory.ServiceFactory;
import service.OnlineUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "servlet.LoginServlet")
public class LoginServlet extends HttpServlet {
    private CustomerDao customerDao = DaoFactory.getUserDao();
    private OrderDao orderDao = DaoFactory.getOrderlistDao();
    private OnlineUserService onlineUserService = ServiceFactory.getVisitorCounterService();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("id");
        String password = request.getParameter("pwd");
        HttpSession session = request.getSession();
        session.setAttribute("loginTimes", 1);
        if (session.getAttribute("username") == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            int userId = customerDao.login(username, password);
            if (userId != 0) {
                onlineUserService.raiseOnlineUser();
                onlineUserService.reduceOnlineVisitor();
                session.setAttribute("username", username);
                String orders = orderDao.getOrders(userId);
                session.setAttribute("msg", "'登陆成功！" + username + "'");
                session.setAttribute("orders", orders);
                response.setContentType("text");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print(orders);
                out.flush();
            } else {
                session.setAttribute("msg", "'用户名或密码错误！'");
                session.setAttribute("loginTimes", 0);
                PrintWriter out = response.getWriter();
                out.flush();
            }
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}  