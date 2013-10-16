import junit.framework.TestCase;


public class EnumTest extends TestCase {
	public void test1enum(){
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
	
	public void test2(){
		StaticConstructor s = new StaticConstructor();
		StaticConstructor s2 = new StaticConstructor();
		
	}
}
