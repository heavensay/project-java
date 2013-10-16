package testjvm.testclassloader.instrument;

import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.TimerTask;
 
public  class  ReloadTask  extends  TimerTask {
   
    private  Instrumentation  inst;
    protected  ReloadTask(Instrumentation  inst){
       this.inst=inst;
    }
   
    @Override
    public  void  run() {
       try{
           ClassDefinition[]  cd=new ClassDefinition[1];
           Class[]  classes=inst.getAllLoadedClasses();
           for(Class  cls:classes){
          if(cls.getClassLoader()==null||!cls.getClassLoader().getClass().getName().equals("sun.misc.Launcher$AppClassLoader"))
                  continue;
              String  name=cls.getName().replaceAll("\\.","/");
             cd[0]=new ClassDefinition(cls,loadClassBytes(cls,name+".class"));
              inst.redefineClasses(cd);
           }
          
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }
   
    private  byte[]  loadClassBytes(Class  cls,String  clsname) throws  Exception{
//       System.out.println(clsname+":"+cls);
       InputStream  is=cls.getClassLoader().getSystemClassLoader().getResourceAsStream(clsname);
       if(is==null)return  null;
       byte[]  bt=new  byte[is.available()];
       is.read(bt);
       is.close();
       return  bt;
    }
}