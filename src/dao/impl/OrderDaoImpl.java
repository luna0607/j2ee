package dao.impl;

import dao.DaoHelper;
import dao.OrderDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Ariana on 2018/1/5.
 */
public class OrderDaoImpl implements OrderDao {
    private static OrderDaoImpl orderlistDao = new OrderDaoImpl();
    private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();

    public static OrderDaoImpl getInstance() {
        return orderlistDao;
    }


    public String getOrders(int userId) {
        Connection conn = null;

        System.out.println("ok1");
        String sql;
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder("[");
        System.out.println("ok2");

        try {
            conn = DaoHelperImpl.getBaseDaoInstance().getConnection();
            Statement stmt = conn.createStatement();
            sql = "select name,price,number,total_price,create_time from orders where userid=" + userId;
            ResultSet result = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            System.out.println("ok3");

            while (result.next()) {
                StringBuilder sb1 = new StringBuilder("");
                sb1.append("{\"name\":\"").append(result.getString(1)).append("\",");
                sb1.append("\"price\":\"").append(result.getString(2)).append("\",");
                sb1.append("\"number\":\"").append(result.getString(4)).append("\",");
                sb1.append("\"totalprice\":\"").append(result.getString(3)).append("\",");
                sb1.append("\"time\":\"").append(result.getString(5)).append("\"}");
                String thisOrder = sb1.toString();
                list.add(thisOrder);
            }
            System.out.println("ok4");
            System.out.println(list.size());
            if (list.size() != 0) {
                for (int i = 0; i < list.size() - 1; i++) {
                    sb.append(list.get(i));
                    sb.append(",");
                }
                sb.append(list.get(list.size() - 1)).append("]");
            } else {
                sb.append("]");
            }
            System.out.println("ok5");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("ok6");

        return sb.toString();
    }
}
