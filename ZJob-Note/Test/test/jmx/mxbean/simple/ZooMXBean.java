package test.jmx.mxbean.simple;

public interface ZooMXBean {

    public Tiger getTiger();

    public void addTiger(Tiger tiger);

    public String getZooName();

    public int getTigerCount();
}
