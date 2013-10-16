package test.jmx.monitor;

public class Count implements CountMBean{
	int count = 0;
	
	Count(int count){
		this.count = count;
	}
	
	@Override
	public int getCount() {
		return count;
	}

	public void addNumber(){
		count = count + 3;
	}
}
