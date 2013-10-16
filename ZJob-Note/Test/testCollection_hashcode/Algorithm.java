package testCollection_hashcode;

import junit.framework.TestCase;

// 5*(8+3)-9+3*(3/5+2)   中缀表达式 :   后缀表达式:583+*9-335/2+*+
public class Algorithm extends TestCase {
	int size ;
	char[] cs;
	MockStack<Character> ms;
	String expression;
	Algorithm(String expression){
		this.size = expression.length();
		this.expression = expression;
//		cs = expression.toCharArray();
		ms = new MockStack<Character>();
	}
	
	Integer calculate(){
		for (char c : expression.toCharArray()) {
			switch(c){
				case '+':
				case '-':
					
				case '*':
				case '/':
				case '(':
				case ')':
				default:
			}
			ms.push(c);
		}
		return null;
	}
	class MockStack<K>{
		int top;
		Object[] stack ;
		MockStack(){
			top = -1;
			stack = new Object[50];
		}
		
		void push(K key){
			stack[top++]=key;
		}
		K pop(){
			return (K)stack[top--];
		}
	}
}
