package testtimer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.junit.Test;

public class TimerTest {
    @Test
    public void test1() {
        Timer timer = new Timer();
        CustomTask task1 = new CustomTask("task1", 2000);
        CustomTask task2 = new CustomTask("task2", 0);
        CustomTask task3 = new CustomTask("task3", 0);
        timer.schedule(task1, 1000, 1000);
        timer.schedule(task3, 1000, 2000);
        timer.schedule(task2, 5000, 5000);
        System.out.println("begin");
    }

    @Test
    public void test2() {
        int i = 1;
        int k = 2;
        while ((i = k << 1) > 0 && i < 20) {
            k++;
            System.out.println(i + "  " + k);
        }
    }

    @Test
    public void test3() throws InterruptedException {
        Timer timer = new Timer();
        //1000毫秒后执行，只执行一次
        timer.schedule(new CustomTask(" task1 "), 1000);
        //1000毫秒后执行第一次，以后按照每2000毫秒频率执行
//		timer.schedule(new CustomTask(" task2 "), 5000,2000);
//		timer.scheduleAtFixedRate(new CustomTask(" task2 "), 5000,2000);
        System.out.println(getTime(Calendar.getInstance(), "10:45"));
        timer.scheduleAtFixedRate(new CustomTask(" task2 "), getTime(Calendar.getInstance(), "10:57"), 2000);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 2);
        //2天后执行第一次，以后按照每2000毫秒频率执行
        timer.scheduleAtFixedRate(new CustomTask(" tasj3 "), c.getTime(), 2000);

        Thread.sleep(Long.MAX_VALUE);
    }

    private static Date getTime(Calendar c, String time) {
        String[] tmp = time.split(":");

        int hour = Integer.parseInt(tmp[0]);

        int minute = Integer.parseInt(tmp[1]);

        c.set(Calendar.HOUR_OF_DAY, hour);

        c.set(Calendar.MINUTE, minute);

        return c.getTime();
    }
}
