package servlet;

import dao.OrderDao;
import dao.CustomerDao;
import factory.DaoFactory;
import factory.ServiceFactory;
import model.Orders;
import service.OnlineUserService;

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

                Orders[] orderlist = orderDao.getOrders(userId);
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