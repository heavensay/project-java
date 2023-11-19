package com.myhexin.shiro;


import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class CountSessionListener implements SessionListener {

    private static Logger logger = Logger.getLogger(CountSessionListener.class.getName());

    public void onExpiration(Session session) {
        // TODO Auto-generated method stub
        logger.debug(" ===onExpiration=== " + "sessionid:" + session.getId() + " sessiontimeout:" + session.getTimeout());
    }

    public void onStart(Session session) {
        // TODO Auto-generated method stub
        logger.debug(" ===onStart=== " + "sessionid:" + session.getId() + " sessiontimeout:" + session.getTimeout());
    }

    public void onStop(Session session) {
        // TODO Auto-generated method stub
        logger.debug(" ===onStop=== " + "sessionid:" + session.getId() + " sessiontimeout:" + session.getTimeout());
    }

}
