package com.basis.concurrency.simulation.carBuilder;
import static com.basis.concurrency.Print.print;
public class DriveTrainRobot extends Robot {
	public DriveTrainRobot(RobotPool pool) {
		super(pool);
	}

	protected void performService() {
		print(this + " installing DriveTrain");
		assembler.car().addDriveTrain();
	}
}
