package service;


/**
 * Created by Ariana on 2018/1/5.
 */
public interface VisitorCounterService {


    public static long getOnlineVisitor(){
        return VisitorCounterServiceBean.getInstance().getOnlineVisitor();
    };

    public  void raiseOnlineVisitor();

    public  void reduceOnlineVisitor();


    public static long getOnlineUser(){
        return VisitorCounterServiceBean.getInstance().getOnlineUser();
    };

    public  void raiseOnlineUser();

    public  void reduceOnlineUser();
}
