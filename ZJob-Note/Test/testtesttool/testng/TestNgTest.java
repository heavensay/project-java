package testtesttool.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNgTest {


    @org.testng.annotations.Test
    public void firstTest(){
        System.out.println(" the first testng unit test");

        Assert.assertEquals(1,1);
    }

}
