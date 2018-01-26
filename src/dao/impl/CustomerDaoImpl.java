package dao.impl;

import dao.CustomerDao;
import util.HQLTools;

import java.sql.*;
import java.util.List;

/**
 * Created by Ariana on 2018/1/5.
 */
public class CustomerDaoImpl implements CustomerDao {
    private static CustomerDaoImpl userDao = new CustomerDaoImpl();

    public static CustomerDaoImpl getInstance() {
        return userDao;
    }

    public int login(String username, String pwd) {
        Connection conn = null;
        String sql;
        int count = 0;
        int userid = 0;
        try {
            sql = "select id from users where username='" + username + " 'and pwd='" + pwd + "'";
             List<Object[]> result= HQLTools.find(sql);
            System.out.print("ok");
            if (result != null) {
                while (result.size()!=0) {
                    count++;
                    userid = (int)result.get(0)[1];
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (count > 0) {
            return userid;
        } else return 0;
    }
}
