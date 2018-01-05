package dao.impl;

import dao.DaoHelper;
import dao.UserDao;

import java.sql.*;

/**
 * Created by Ariana on 2018/1/5.
 */
public class UserDaoImpl implements UserDao {
    private static UserDaoImpl userDao = new UserDaoImpl();
    private static DaoHelper daoHelper = DaoHelperImpl.getBaseDaoInstance();

    public static UserDaoImpl getInstance() {
        return userDao;
    }

    public int login(String username, String pwd) {
        Connection conn = null;
        String sql;
        int count = 0;
        int userid = 0;
        try {
            conn = DaoHelperImpl.getBaseDaoInstance().getConnection();
            Statement stmt = conn.createStatement();
            sql = "select id from users where username='" + username + " 'and pwd='" + pwd + "'";
            ResultSet result = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
            System.out.print("ok");
            while (result.next()) {
                count++;
                System.out.print(result.getInt(1));
                userid = result.getInt(1);
            }
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
        if (count > 0) {
            return userid;
        } else return 0;
    }
}
