package com.basis.concurrency.simulation.carBuilder;
import static com.basis.concurrency.Print.print;
public class WheelRobot extends Robot {
	public WheelRobot(RobotPool pool) {
		super(pool);
	}

	protected void performService() {
		print(this + " installing Wheels");
		assembler.car().addWheels();
	}
}