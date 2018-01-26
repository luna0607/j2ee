package dao.impl;

import dao.OrderDao;
import model.Orders;
import util.HQLTools;

import java.util.List;

/**
 * Created by Ariana on 2018/1/5.
 */
public class OrderDaoImpl implements OrderDao {
    private static OrderDaoImpl orderlistDao = new OrderDaoImpl();
    public static OrderDaoImpl getInstance() {
        return orderlistDao;
    }


    public Orders[] getOrders(int userId) {
        try {
					List orders = HQLTools.find("select * from orders p where userid="+userId+";");
					Orders[] tmp=new Orders[orders.size()];
    				for(int i=0;i<orders.size();i++){
    					tmp[i]=(Orders)orders.get(i);
					}
    				return tmp;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}
