package TestRefect;

public class AnnotationBeTest {
	
	@AnnotationObject (getId=1)
	public void printInt(int i){
		System.out.println(i);
	}
}


//class InnerAnn extends AnnotationBeTest{
//	
//}