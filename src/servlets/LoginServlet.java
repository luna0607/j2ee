package servlets;


import dao.OrderlistDao;
import dao.UserDao;
import factory.DaoFactory;
import factory.ServiceFactory;
import model.Orders;
import service.VisitorCounterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "servlet.LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = DaoFactory.getUserDao();
    private OrderlistDao orderlistDao = DaoFactory.getOrderlistDao();
    private VisitorCounterService visitorCounterService = ServiceFactory.getVisitorCounterService();
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("id");
        String password = request.getParameter("pwd");
        System.out.println(username);
        System.out.println(password);
        HttpSession session = request.getSession();
        session.setAttribute("tryLogin", 1);
        if (session.getAttribute("username") == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            System.out.print("trylogin");
            int userid = userDao.login(username, password);
            if (userid != 0) {
                visitorCounterService.raiseOnlineUser();
                visitorCounterService.reduceOnlineVisitor();
                System.out.println("login successfully");
                session.setAttribute("username", username);
                System.out.println("orderlist ready");

                Orders[] orderlist = orderlistDao.getOrders(userid);
                System.out.println("orderlist successfully");
                session.setAttribute("msg", "'欢迎您！" + username + "'");
                session.setAttribute("orders", orderlist);
                System.out.println("set successfully" + session.getAttribute("orders"));
                response.setContentType("text");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();


                //把数组转换成json字符串
                ArrayList<String> list = new ArrayList<>();
                StringBuilder sb = new StringBuilder("[");
                for (Orders order : orderlist) {
                    StringBuilder sb1 = new StringBuilder("");
                    sb1.append("{\"name\":\"").append(order.getName()).append("\",");
                    sb1.append("\"price\":\"").append(order.getPrice()).append("\",");
                    sb1.append("\"number\":\"").append(order.getNumber()).append("\",");
                    sb1.append("\"totalprice\":\"").append(order.getTotal_price()).append("\",");
                    sb1.append("\"time\":\"").append(order.getCreate_time()).append("\"}");
                    String thisOrder = sb1.toString();
                    list.add(thisOrder);
                }
                if (list.size() != 0) {
                    for (int i = 0; i < list.size() - 1; i++) {
                        sb.append(list.get(i));
                        sb.append(",");
                    }
                    sb.append(list.get(list.size() - 1)).append("]");
                } else {
                    sb.append("]");
                }


                out.print(sb.toString());
                System.out.println("out successfully");
                out.flush();
            } else {
                session.setAttribute("msg", "'用户名或密码错误'");
                session.setAttribute("tryLogin", 0);

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