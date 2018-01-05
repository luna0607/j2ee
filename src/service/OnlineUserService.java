package service;

import service.impl.OnlineUserServiceImpl;

/**
 * Created by Ariana on 2018/1/5.
 */
public interface OnlineUserService {


    public static long getOnlineVisitor(){
        return OnlineUserServiceImpl.getInstance().getOnlineVisitor();
    };

    public  void raiseOnlineVisitor();

    public  void reduceOnlineVisitor();


    public static long getOnlineUser(){
        return OnlineUserServiceImpl.getInstance().getOnlineUser();
    };

    public  void raiseOnlineUser();

    public  void reduceOnlineUser();
}
