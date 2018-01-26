package dao;


import model.User;
import util.HQLTools;
import java.util.List;

public class UserDaoBean implements UserDao
{

    private static UserDao userDao=new UserDaoBean();
    public static UserDao getInstance(){
        return userDao;
    }

	public UserDaoBean() {
	}

	@Override
	    public int login(String username, String pwd) {

        String sql;
        int count = 0;
        int userid = 0;
        List result= HQLTools.find("select * from users where username="+username+"and pwd="+pwd+";");
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
