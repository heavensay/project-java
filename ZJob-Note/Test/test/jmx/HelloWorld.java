package test.jmx;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;


/**
 * 此标准MBean需实现XXXMBean这样名称的借口，XXX为这个类的名称，
 * 如果MBean需要消息事件的发送、监听等需要实现
 *
 * @see javax.management.NotificationBroadcaster，
 * 或者继承@see javax.management.NotificationBroadcasterSupport
 */
public class HelloWorld extends NotificationBroadcasterSupport implements HelloWorldMBean {
    public String hello;

    private long seq = 0l;

    public HelloWorld() {
        this.hello = "Hello World! I am a Standard MBean";
    }

    public HelloWorld(String hello) {
        this.hello = hello;
    }

    public String getHello() {
        return hello;
    }

    @Override
    public Object getInstance() {
        return new Object();
    }

    /*
     * 当执行message的时候，发送一个消息(事件)
     * @see test.jmx.HelloWorldMBean#message(java.lang.String)
     */
    @Override
    public String message(String ms) {
        Notification notice = new Notification("type1", this, seq++, " the message metheod is invoked,the argument ms: " + ms);
        sendNotification(notice);
        return " the message : ...... ";
    }

    @Override
    public void setHello(String hello) {
        this.hello = hello;
    }
}
