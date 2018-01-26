package dao;


import model.User;

import javax.persistence.*;
import java.util.List;

public class UserDaoBean implements UserDao
{

	@PersistenceUnit(name = "nju")
	private EntityManagerFactory factory;

	public UserDaoBean() {
		factory = Persistence.createEntityManagerFactory("nju");
		em = factory.createEntityManager();
	}

	@PersistenceContext
	protected EntityManager em;


	@Override
	    public int login(String username, String pwd) {

        String sql;
        int count = 0;
        int userid = 0;
        Query query = em.createQuery("select * from users where username="+username+"and pwd="+pwd+";");
        List result= query.getResultList();
        System.out.print("ok");
        for(Object user:result) {
            count++;
            System.out.print(((User) user).getUsername());
            userid = ((User) user).getId();
        }
        if (count > 0) {
            return userid;
        } else return 0;
    }

}
