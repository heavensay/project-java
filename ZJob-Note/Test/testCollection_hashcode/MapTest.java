package testCollection_hashcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import junit.framework.TestCase;

public class MapTest extends TestCase {
    public void test1map() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "cat");
        map.put("2", "dog");
        map.put("3", "bird");
        System.out.println(map.get("3"));
        System.out.println(map);
    }

    public void test2array() {
        int[][] a = {{1, 2}, {3, 4}};
        System.out.println(a[0]);
        System.out.println(a);
    }

    public void test3array() {
        A a = new A(1); //一个团
        A[] l = new A[]{new A(1),
                new A(2)}; //一个旅 a[2]

        A[][] s = new A[][]{{new A(1), new A(2)},
                {new A(3), new A(4)}}; //一个师 a[1][2]

        A[][][] j = new A[][][]{
                {{new A(1), new A(2), new A(3), new A(4)}, {new A(5), new A(6), new A(7), new A(8)}}
        }; //一个军团a[1][2][4];
        System.out.println("leng:" + l.length + "------" + Arrays.deepToString(l));
        System.out.println("leng:" + s.length + "------" + Arrays.deepToString(s));
        System.out.println("leng:" + j.length + "------" + Arrays.deepToString(j));
    }

    public void test4emptyconstruction() {
        C c = new C();
    }

    public void test5algorithm() {
        System.out.println(hash(8));
        System.out.println(hash(1121));
    }

    public void test6MapPutnull() {
        Map map = new HashMap();
        map.put(null, "i am null");
        System.out.println(map.get(null));
    }

    public void test7MyMap() {
        Map map = new MyMap(5);
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.put("d", "4");
        map.put("e", "5");
        map.put("f", "62");
        map.put("g", "7");
        System.out.println(map.get("f"));
        System.out.println(map.get("b"));
        System.out.println(map.toString());
        map.put("h", "3");
        System.out.println(map.toString());
    }

    public void test8queue() {
        Queue queue = new LinkedList();
        queue.add(3);
        queue.add(1);
        queue.add(2);
        while (!queue.isEmpty())
            System.out.println(queue.poll());
        System.out.println(queue);
    }

    /**
     * hashmap's hash algorithm
     *
     * @param h
     * @return
     */
    int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * 2个团构成1个旅，2个旅构成一个师,2个师构成一个军团
     *
     * @author Administrator
     */
    class A {
        int sequence;

        A(int seq) {
            this.sequence = seq;
        }

        public String toString() {
            // TODO Auto-generated method stub
            return sequence + "团";
        }
    }

    class B {
        B() {
            System.out.println("I'm a B");
        }
    }

    class C extends B {
        C() {
            System.out.println("I'm a C");
        }
    }
}
