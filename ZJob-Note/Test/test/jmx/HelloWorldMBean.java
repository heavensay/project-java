package test.jmx;

public interface HelloWorldMBean {
	public String getHello();
	
	public void setHello(String hello);
	
	public Object getInstance();
	
	public String message(String ms);

}
