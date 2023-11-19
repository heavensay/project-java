package test_cglib;

public class InfoManager {

    int i = 0;

    public InfoManager() {
        this(5);
    }

    private InfoManager(int i) {
        this.i = i;
    }

    // 模拟查询操作
    public void query() {
        System.out.println(" query " + i);
    }

    // 模拟创建操作
    public void create() {
        System.out.println(" create ");
    }

    // 模拟更新操作
    public void update() {
        System.out.println(" update ");
    }

    // 模拟删除操作
    public void delete() {
        System.out.println(" delete ");
    }
}