package model;

/**
 * Created by Ariana on 2017/12/23.
 */
public class userInfo {
    private String username;
    private int id;
    private String pwd;
    private orderInfo[] orderInfos;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public orderInfo[] getOrderInfos() {
        return orderInfos;
    }


}
