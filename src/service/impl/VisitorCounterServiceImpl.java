package service.impl;

import service.VisitorCounterService;

/**
 * Created by Ariana on 2018/1/5.
 */
public class VisitorCounterServiceImpl implements VisitorCounterService {

    private static VisitorCounterServiceImpl visitorCounter=new VisitorCounterServiceImpl();
    public static VisitorCounterServiceImpl getInstance() {
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
