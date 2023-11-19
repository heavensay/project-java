package testtimer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class CustomTask extends TimerTask {

    private long sleepTime;
    private String taskName;

    CustomTask() {

    }

    CustomTask(String taskName) {
        this.taskName = taskName;
    }

    CustomTask(String taskName, long sleepTime) {
        this.sleepTime = sleepTime;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("taskName :" + taskName + " ,before the thread sleep" + sleepTime + " ,the time is " + sdf.format(new Date()));
            Thread.sleep(sleepTime);
            System.out.println("taskName :" + taskName + " ,after the thread sleep " + sleepTime + " ,the time is " + sdf.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
