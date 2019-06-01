package schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler { //Scheduling
	//@Scheduled annotation is used to trigger the scheduler for a specific time period.
	@Scheduled(cron = "0 * 9 * * ?") //execute the task every minute starting at 9:00 AM and ending at 9:59 AM, every day
	public void cronJobSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Java cron job expression:: " + strDate);
	}

	//Fixed Delay scheduler is used to execute the tasks at a specific time. It should wait for the previous task completion. The values should be in milliseconds
	@Scheduled(fixedRate = 1000) //executing a task on every second from the application startup
	public void fixedRateSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Fixed Rate scheduler:: " + strDate);
	}

	//the initialDelay is the time after which the task will be executed the first time after the initial delay value.
	@Scheduled(fixedDelay = 1000, initialDelay = 3000) //execute the task for every second after 3 seconds from the application startup has been completed
	public void fixedDelaySch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Fixed Delay scheduler:: " + strDate);
	}
}
