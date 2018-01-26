package service;


/**
 * Created by Ariana on 2018/1/5.
 */
public class VisitorCounterServiceBean implements VisitorCounterService {

    private static VisitorCounterServiceBean visitorCounter=new VisitorCounterServiceBean();
    public static VisitorCounterServiceBean getInstance() {
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
