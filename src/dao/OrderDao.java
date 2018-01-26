package dao;

import model.Orders;

/**
 * Created by Ariana on 2018/1/5.
 */
public interface OrderDao {
    public Orders[] getOrders(int userId);
}
