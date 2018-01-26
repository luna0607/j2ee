package dao;

import model.Orders;
import util.HQLTools;

import javax.persistence.*;
import java.util.List;


/**
 * Created by Ariana on 2018/1/5.
 */

public class OrderlistDaoBean implements OrderlistDao {
	private static OrderlistDao orderlistDao = new OrderlistDaoBean();


	public static OrderlistDao getInstance() {
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
