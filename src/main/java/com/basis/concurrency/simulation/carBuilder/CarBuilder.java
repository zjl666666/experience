/**
 * 
 */
package com.basis.concurrency.simulation.carBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



/**
 * @Description: TODO
 * @author zjl
 * @date 2015年2月3日 下午2:13:50 
 */
public class CarBuilder {
	public static void main(String[] args) throws Exception {
		CarQueue chassisQueue = new CarQueue(), finishingQueue = new CarQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		RobotPool robotPool = new RobotPool();
		exec.execute(new EngineRobot(robotPool));
		exec.execute(new DriveTrainRobot(robotPool));
		exec.execute(new WheelRobot(robotPool));
		exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
		exec.execute(new Reporter(finishingQueue));
		// Start everything running by producing chassis:
		exec.execute(new ChassisBuilder(chassisQueue));
		TimeUnit.SECONDS.sleep(7);
		exec.shutdownNow();
	}
}
