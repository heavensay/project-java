package testtesttool.jmockit;

import org.junit.Test;

import junit.framework.Assert;
import mockit.Capturing;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;


/**
 * Jmockit基本用法测试
 *  
 *
 */
public class JmockitTest {
	@Mocked  //用@Mocked标注的对象，不需要赋值，jmockit自动mock
	UserDao userDao;
	
	
	/**
	 * 简单mock接口的方法来进行测试
	 * 
	 * UserDao接口被mock，mock UserDao.getUserName()逻辑
	 */
	@Test  
	public void testSimpleMockMethod() {
	    new Expectations() {//录制预期模拟行为
	        {
	        	//声明要Mock的方法(注：其它方法按照正常的业务逻辑运行)  
	        	userDao.getUserName(5);
	        	//期望方法返回的结果  
	        	result = "jack";
	        }  
	    };

	    System.out.println(userDao.getUserName(5));
	    System.out.println(userDao.getUser("dddf"));
	    System.out.println(userDao.getUserName(6));
	    System.out.println(userDao.getUserName(5));
//	    System.out.println(new UserService().getUserName(5));//userService.userDao nullPointException
	    
	}
	
	/**
	 * 使用Expectations模拟NonStrictExpectations
	 * jmockit 1.37版本中已经找不到NonStrictExpectations类：
	 * 		原因在于Excectations已经提供了NonStrictExpectations的所有功能，唯一不同在于minTimes参数，
	 * 		Expectations：1，NonStrictExpectations：0；
	 * 		所以设置Expectations块中minTimes=0，可以实现跟NonStrictExpectations一样的效果
	 * 
	 */
	@Test  
	public void testImitateNonStrictExpectations() {
	    new Expectations() {//录制预期模拟行为
	        {
	        	//声明要Mock的方法(注：其它方法按照正常的业务逻辑运行)  
	        	userDao.getUserName(5);
	        	//期望方法返回的结果  
	        	result = "jack";minTimes=0;
	        	userDao.getUser("ddd");
	        	result = new User();minTimes=0;
	        	
	        }  
	    };

	    System.out.println(userDao.getUserName(5));
	    System.out.println(userDao.getUserName(6));
	    System.out.println(userDao.getUser("dddf"));
	    System.out.println(userDao.getUserName(6));
	    System.out.println(userDao.getUserName(5));
	    System.out.println(userDao.getUser("ddd"));
	    System.out.println(userDao.getUser("dddf"));
	    
	}
	
	@Tested UserService userService;
	@Injectable UserDao userDaoByInjectTableForTested;
	/**
	 * 测试@Tested作用，@Tested标注一个需要被测试的类，这个类里面的属性会根据被@Injectable注解的属性进行自动拼接
	 *  对@Tested对象判断是否为null，是则通过合适构造器、变量注入等方式初始化， @Injectable的实例会自动注入到@Tested对象中。
	 *  
	 * 用@Tested标注被测试类，在运行测试方法时，如果该实例仍然为null，JMockit会自动组装相关mock对象，进行初始化。
	 * 在组装被测试类过程中，相关mock对象必须使用@Injectable标记，非mock对象除了使用@Injectable标记，还需要有明确初始值。
	 * 注入先根据类型匹配，再根据参数名称匹配。
	 */
	@Test  
	public void testTestedByInjectTable() {
	    new Expectations() {//录制预期模拟行为
	        {
	        	
	        	//声明要Mock的方法(注：其它方法按照正常的业务逻辑运行)  
	        	userDaoByInjectTableForTested.getUserName(6);
	        	//期望方法返回的结果  
	        	result = "tom";
	        }  
	    };

	    System.out.println(userService.getUserName(6));//
	}
	
	/**
	 * 大型软件项目，往往会在设计的时候进行模块化划分，模块之间存在依赖关系。
	 * 为了减少各个模块之间的耦合，通过接口进行依赖，各个模块由不同的开发组进行并行开发。
	 * 如果A模块需要使用B模块的接口，但是B模块由于开发进度缓慢，并没有完成对应实现类的开发。
	 * 那么这个时候A进行单元测试就有困难了。如果A需要测试自己的模块功能，就需要将B的接口进行mock。
	 * 下面介绍下jmockit如何mock接口的。
	 * 
	 * MockUp：模拟函数实现
	 * @Mock：MockUp模式中，指定被Fake的方法
	 */
	
	@Test
	public void testMockUpAndMock(){
		MockUp<UserDao> proxyStub = new MockUp<UserDao>() {
            // 需要使用@Mock标记,否则jmockit不会处理;  
            // 而且方法的签名必须与接口中方法签名一致，否则jmockit会报错  
			@Mock
			public String getUserName(Integer id){
				return "default"+id;
			}
		};
		
		UserDao userDao = proxyStub.getMockInstance();
		System.out.println(userDao.getUserName(3));
		System.out.println(userDao.getUserName(4));
		System.out.println(userDao.getUser("jack"));//output:null
		System.out.println(userDao.getUser("jack"));//output:null
	}
	
	/**
	 * jmockit有如下特点：
	 * 1、如果目标接口中存在多个方法，我们只需要关注需要自己mock的方法即可，对于不需要的方法,jmockit会提法默认实现，不用我们去实现接口中的所有方法。
	 * 2、MockUp中mock的方法一定要与对应接口中方法签名一致，否则会报错java.lang.IllegalArgumentException: Matching real methods not found for the following mocks。
	 * 3、MockUp中的方法一定要加@Mock注解，否则会被jmockit忽略，不能达到mock的目的
	 */
	@Test
	public void testMockUpAndMock2(){
		MockUp<UserService> proxyStub = new MockUp<UserService>() {
            // 需要使用@Mock标记,否则jmockit不会处理;  
            // 而且方法的签名必须与接口中方法签名一致，否则jmockit会报错  
			////未mock函数不受影响
			@Mock
			public String getUserName(Integer id){
				return "default"+id;
			}
		};
		
		UserService service = proxyStub.getMockInstance();
		System.out.println(service.getUserName(3));//output:default3    执行的是mock之后的返回值
		System.out.println(service.getUserName(4));//default4  执行的是mock之后的返回值
		System.out.println(service.getBedroom("jack")); //output：bedroom 001:jack   执行的是UserService的源方法逻辑
	}

}
