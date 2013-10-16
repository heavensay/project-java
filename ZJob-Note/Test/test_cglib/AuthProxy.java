package test_cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AuthProxy implements MethodInterceptor {

    private  String name;  //  会员登录名 

    public  AuthProxy(String name) { 
        this .name  =  name; 
   } 

	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
        if  ( ! "maurice" .equals( this.name)) { 
            System.out.println( " AuthProxy:you have no permits to do manager! " ); 
             return   null ; 
        } 
         return  proxy.invokeSuper(obj, args); 
	}
	
    public  String getName() { 
        return  name; 
   } 

    public   void  setName(String name) { 
        this .name  =  name; 
   } 
}
