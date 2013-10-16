package test.syntax;

import org.junit.Test;

/**
 * @author banana
 *
 */
public class Syntax {
	
      public static  void  size(String... values){
    	  System.out.println(values.length);
      }
      
      
      /**
     * 
     */
    @Test
      public void test1(){
    	  size("aaa",null); //the result is 2
      }
    /**
     * 
     */
    @Test
      public void test2(){
    	  size(new String[]{"aaaa",null}); //the result is 2
      }
      
}
