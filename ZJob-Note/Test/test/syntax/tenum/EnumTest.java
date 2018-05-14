package test.syntax.tenum;

import org.junit.Assert;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.EnumMap;
import java.util.Map;


public class EnumTest {


	@Test
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

		Assert.assertEquals(dog, dog2);
	}
	
	/**
	 * key-value结构枚举类测试
	 * 自定义方法
	 */
	@Test
	public void testKV(){
		KeyValueEnum[] kvs = KeyValueEnum.values();
		for (KeyValueEnum kv : kvs) {
			System.out.println(MessageFormat.format("KeyValue id:{0},name:{1}", kv.getKey(),kv.getValue()));
			
			switch (kv) {
			case KV1:
				System.out.println("KV1:"+KeyValueEnum.KV1);
				break;
			}
		}
	}

	/**
	 * enum中可以自定义方法
	 */
	@Test
	public void customMethodEnumTest(){

		System.out.println(CustomMethodEnum.ADD.getCalculateResult());
		System.out.println(CustomMethodEnum.MINUS.getCalculateResult());
		System.out.println(CustomMethodEnum.MULTIPLY.getCalculateResult());
		System.out.println(CustomMethodEnum.DEFAULT.getCalculateResult());
	}


	/**
	 * 效率比HashMap高，可以直接获取数组下标索引并访问到元素；
	 */
	@Test
	public void enummapTest(){
		EnumMap<SimpleEnum,String> enumMap = new EnumMap<SimpleEnum,String>(SimpleEnum.class);
		enumMap.put(SimpleEnum.A,"AAA");
		enumMap.put(SimpleEnum.B,"BBB");

		//key-value集合
		for(Map.Entry<SimpleEnum, String> entry:enumMap.entrySet()){
			System.out.println(entry.getKey() + ": " + entry.getValue() + ", ");
		}
	}


}
