package test_cglib;

public class Client {

    public static void main(String[] args) {
        Client c = new Client();
        c.anyonecanManager();
        c.haveNoAuthManager();
        c.haveAuthManager();
    }

    /**
     * 模拟：没有任何权限要求，任何人都可以操作
     */
    public void anyonecanManager() {
        System.out.println(" any one can do manager ");
        InfoManager manager = InfoManagerFactory.getInstance();
        doCRUD(manager);
        separatorLine();
    }

    /**
     * 模拟：登录会员没有权限
     */
    public void haveNoAuthManager() {
        System.out
                .println(" the loginer's name is not maurice,so have no permits do manager ");
        InfoManager noAuthManager = InfoManagerFactory
                .getAuthInstance(new AuthProxy("maurice1"));
        doCRUD(noAuthManager);
        separatorLine();
    }

    /**
     * 模拟：登录会员有权限
     */
    public void haveAuthManager() {
        System.out
                .println(" the loginer's name is maurice,so have permits do manager ");
        InfoManager authManager = InfoManagerFactory
                .getAuthInstance(new AuthProxy("maurice"));
        doCRUD(authManager);
        separatorLine();
    }

    /**
     * 对Info做增加／更新／删除／查询操作
     *
     * @param manager
     */
    private void doCRUD(InfoManager manager) {
        manager.create();
        manager.update();
        manager.delete();
        manager.query();
    }

    /**
     * 加一个分隔行，用于区分
     */
    private void separatorLine() {
        System.out.println(" ################################ ");
    }

} 