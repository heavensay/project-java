import org.junit.Test;


/**
 * 嵌套循环测试
 * @author banana
 *
 */
public class TestFor {
	
	@Test
	public void test1(){
		for(int i=0;i<5;i++){
			System.out.println("i:"+i);
			for(int j=10;j>5;j--){
				if(j==7){
					break;
				}
				System.out.println("j:"+j);	
			}
		}
	}
}
