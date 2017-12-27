package testtesttool.jmockit;

import org.junit.Test;

import mockit.Capturing;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;



/**
 * 此测试目的：@Mocked,@Capturing,@Injectable注解范围影响测试
 * 
 * @Mocked：被修饰的对象将会被Mock，对应的类和实例都会受影响（同一个测试用例中）
 * @Capturing：可以mock接口以及其所有的实现类
 * @Injectable：仅Mock被修饰的对象
 * 
 * 影响范围@Injectable>@Mocked>@Injectable
 * 
 *
 */
public class OrderServiceTest {
//	@Mocked
	@Capturing
//	@Injectable
	OrderDaoImpl orderDao;
	
	@Test  
	public void testGetOrder() {
	    new Expectations() {//录制预期模拟行为
	        {
	        	//声明要Mock的方法(注：其它方法按照正常的业务逻辑运行)  
	        	orderDao.getOrder(3);
	        	result = "mock order";
	        }  
	    };

	    System.out.println(orderDao.getOrder(3));//output:mock order
	    System.out.println(orderDao.getOrder(4));//output:null
	    System.out.println(new OrderService().getOrder(3));//output:mock order
	    System.out.println(new OrderService().getOrder(4));//output:null
	}
	
	
	/**
	 * @Mocked：被修饰的对象将会被Mock，对应的类和实例都会受影响（同一个测试用例中）
	 * @Capturing：可以mock接口以及其所有的实现类
 	 * @Injectable：仅Mock被修饰的对象
	 */
	@Test  
	public void testGetOrder2() {
	    new Expectations() {//录制预期模拟行为
	        {
	        	//声明要Mock的方法(注：其它方法按照正常的业务逻辑运行)  
	        	orderDao.getOrder(3);
	        	result = "mock order";
	        }  
	    };

	    
	    /** 
	     * OrderDaoImpl有下列3种注解(@Mocked,@Capturing,@Injectable)修饰打印的内容
	     * orderDao实例受到了3种注释的影响
	     */
	    System.out.println(orderDao.getOrder(3));//@Mocked:mock order;@Capturing:mock order;@Injectable:mock order
	    System.out.println(orderDao.getOrder(4));//@Mocked:null;@Capturing:null;@Injectable:null
	    
	    /**
	     * orderService.getOrder方法中的OrderDaoImpl实例受@mocked，@Capturing影响，没有受@Injectable影响
	     */
	    System.out.println(new OrderService().getOrder(3));//@Mocked:mock order;@Capturing:mock order;@Injectable:book order3
	    System.out.println(new OrderService().getOrder(4));//@Mocked:null;@Capturing:null;@Injectable:book order4
	    
	    /**
	     * orderService.getOrder2方法中的OrderDaoImpl实现类的实例受@Capturing影响，没有受@mocked，@Injectable影响
	     */
	    System.out.println(new OrderService().getOrder2(3));//@Mocked:food order3;@Capturing:mock order;@Injectable:food order3
	    System.out.println(new OrderService().getOrder2(4));//@Mocked:food order4;@Capturing:null;@Injectable:food order4
	}
}
