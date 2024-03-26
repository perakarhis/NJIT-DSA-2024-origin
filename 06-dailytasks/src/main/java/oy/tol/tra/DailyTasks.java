package oy.tol.tra;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
public class DailyTasks {
   private QueueInterface<String> dailyTaskQueue = null;
   private Timer timer = null;
   private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;
   private DailyTasks() {
   }
   public static void main(String[] args) {
      DailyTasks tasks = new DailyTasks();
      tasks.run();
   }

   private void run() {
      try {
         dailyTaskQueue=new QueueImplementation<>();
         readTasks();
         timer = new Timer();
         timer.scheduleAtFixedRate(new TimerTask() {
            
            @Override
            public void run() {
               if (!dailyTaskQueue.isEmpty()) { 
                  try {
                      String task = dailyTaskQueue.dequeue(); 
                      System.out.println("Task for today: " + task);
                  } catch (QueueIsEmptyException e) {
                      System.out.println("Error dequeueing task: " + e.getMessage());
                  }
              } else {
                  timer.cancel();
              }
            }
         }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);
      } catch (IOException e) {
         System.out.println("Something went wrong :( " + e.getLocalizedMessage());
      }
   }

   private void readTasks() throws IOException {
      String tasks;
      tasks = new String(getClass().getClassLoader().getResourceAsStream("DailyTasks.txt").readAllBytes());
      String[] allTasks = tasks.split("\\r?\\n");
      for (String task : allTasks) {
         dailyTaskQueue.enqueue(task);
      }
      // TODO: print out to the console the number of tasks in the queue:
      System.out.println("Number of tasks in the queue: " + dailyTaskQueue.size());
   }
}
