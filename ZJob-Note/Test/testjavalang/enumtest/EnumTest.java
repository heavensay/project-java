package testjavalang.enumtest;
import java.text.MessageFormat;

import junit.framework.TestCase;


public class EnumTest extends TestCase {
	
	public void testEquals(){
		AnimalEnum dog = AnimalEnum.DOG;
		AnimalEnum dog2 = AnimalEnum.DOG;
		AnimalEnum bird = AnimalEnum.BIRD;
		
		if(dog.equals(dog2)){
			System.out.println("dog1"+dog+"==dog2:"+dog2);
		}else{
			System.out.println("dog1"+dog+"!=dog2:"+dog2);
		}
		if(dog.equals(bird)){
			System.out.println("dog1"+dog+"==bird:"+bird);
		}else{
			System.out.println("dog1"+dog+"!=bird:"+bird);
		}
		System.out.println("dog=dog2:"+(dog==dog2));
		System.out.println("dog=bird:"+(dog==bird));
		
		assertEquals(dog, dog2);
	}
	
	/**
	 * key-value结构枚举类测试
	 * 自定义方法
	 */
	public void testKV(){
		KeyValue[] kvs = KeyValue.values();
		for (KeyValue kv : kvs) {
			System.out.println(MessageFormat.format("KeyValue id:{0},name:{1}", kv.getKey(),kv.getValue()));
			
			switch (kv) {
			case KV1:
				System.out.println("KV1:"+KeyValue.KV1);
				break;
			}
		}
		

		
	}
	
}
