package testjvm.testclassloader.instrument;

import java.util.Timer;

public  class  Test {

    public  static  void  main(String[] args)throws  InterruptedException {

       Bean1  c1=new  Bean1();

       while(true){

           c1.test1();

           Thread.sleep(10000);

       }

    }

}