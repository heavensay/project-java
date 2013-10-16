package testjvm.testclassloader.hot;

public class A {  
    private B b = new B();  
   
    public void setB(B b) {  
         this .b = b;  
    }  
   
    public B getB() {  
         return b;  
    }  
}  
