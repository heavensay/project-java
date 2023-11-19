package aop;

import org.aopalliance.aop.Advice;

public class ZooServiceAdvice implements Advice {

    public void before() {
        System.out.println(" before zoo ...");
    }

    public void after() {
        System.out.println(" after zoo ...");
    }
}
