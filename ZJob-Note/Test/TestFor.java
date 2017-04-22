import org.junit.Test;


/**
 * 嵌套循环测试
 * @author banana
 *
 */
public class TestFor {
	
	/**
	 * 双重for循环，break打断测试
	 * break会打断里面一层for，然后继续执行外面for逻辑
	 */
	@Test
	public void breakForTest(){
		for(int i=0;i<5;i++){
			System.out.println("i:"+i);
			for(int j=10;j>5;j--){
				if(j==7){
					break;
				}
				System.out.println("j:"+j);	
			}
			System.out.println("i2:"+i);
		}
	}
}
