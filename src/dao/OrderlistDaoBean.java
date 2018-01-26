package dao;

import model.Orders;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ariana on 2018/1/5.
 */

public class OrderlistDaoBean implements OrderlistDao {
    private OrderlistDaoBean orderlistDao = new OrderlistDaoBean();

	@PersistenceUnit(name = "nju")
	private EntityManagerFactory factory;

	public OrderlistDaoBean() {
		factory = Persistence.createEntityManagerFactory("nju");
		em = factory.createEntityManager();
	}

	@PersistenceContext
	protected EntityManager em;


    public OrderlistDaoBean getInstance() {
        return orderlistDao;
    }


    public Orders[] getOrders(int userId) {
    			try {
    				Query query = em.createQuery("select * from orders p where userid="+userId+";");
    				List orders = query.getResultList();
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
