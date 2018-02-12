package org.usfirst.frc.team6500.robot.auto;

/**Is a timer. Activate it with startTimer(seconds).
 * @author Thomas Dearth
 */
public class Timer extends Thread {
	public long time;
	
	public Timer(int i) {
		time = i;
	}

	public void run() {
		System.out.println(time);
		final long startTime = System.nanoTime();
		while(!isInterrupted()) {
			long now = (System.nanoTime()-startTime)/1000000000L;
			if(now >= time) {
				System.out.println("TIME IS UP. TRY HARDER");
				return;
			}
			System.out.println("There are " + (time-now) + " seconds.");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				//NOTHING
			}
		}
	}
	
	public void startTimer(long seconds) {
		this.time = seconds;
		(new Thread(new Timer(15))).start();
	}
	
	public static void main(String[] args) {
		Timer autoTimer = new Timer(15);
		autoTimer.startTimer(15);
	}

}
