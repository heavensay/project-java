package test.jmx.mxbean_complex;

public class ZooImpl implements ZooMXBean {

    private Tiger tiger = new Tiger(new Meat(" i i i meat "));

    private String zooName = " China zoo";

    @Override
    public void addTiger(Tiger tiger) {
        this.tiger = tiger;
    }

    @Override
    public Tiger getTiger() {
        // TODO Auto-generated method stub
        return tiger;
    }

    @Override
    public String getZooName() {
        return zooName;
    }

}
