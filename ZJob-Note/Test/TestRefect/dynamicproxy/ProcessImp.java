package TestRefect.dynamicproxy;

public class ProcessImp implements Process {

	@Override
	public String hit(String s) {
		System.out.println("ProcessImp hit:"+s);
		return "hhit";
	}

	@Override
	public void process() {
		System.out.println("ProcessImp process()");
	}

	@Override
	public void superProcess() {
		System.out.println("ProcessImp superProcess()");
	}

}
