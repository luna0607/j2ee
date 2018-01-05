package service.impl;

import service.OnlineUserService;

/**
 * Created by Ariana on 2018/1/5.
 */
public class OnlineUserServiceImpl implements OnlineUserService {

    private static OnlineUserServiceImpl visitorCounter=new OnlineUserServiceImpl();
    public static OnlineUserServiceImpl getInstance() {
        return visitorCounter;
    }

    private static long onlineVisitor = 0;

    public  long getOnlineVisitor() {
        return onlineVisitor;
    }

    public  void raiseOnlineVisitor() {
        onlineVisitor++;
    }

    public  void reduceOnlineVisitor() {
        onlineVisitor--;
    }

    private static  long onlineUser = 0;

    public  long getOnlineUser() {
        return onlineUser;
    }

    public  void raiseOnlineUser(){
        onlineUser++;
    }

    public  void reduceOnlineUser(){
        onlineUser--;
    }
}
