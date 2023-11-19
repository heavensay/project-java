package testCollection_hashcode;

import junit.framework.TestCase;

public class Test extends TestCase {
    /**
     * copy of array
     */
    public void test1array() {
        int[] orig = new int[]{1, 2, 3, 4, 5, 6};
        int[] dest = new int[orig.length];
        System.arraycopy(orig, 1, orig, 3, 2);
        System.out.println(orig);
        System.arraycopy(orig, 1, dest, 3, 2);
        System.out.println(dest);
    }
}
