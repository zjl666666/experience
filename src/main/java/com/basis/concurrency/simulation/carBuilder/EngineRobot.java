package com.basis.concurrency.simulation.carBuilder;

import static com.basis.concurrency.Print.print;

public class EngineRobot extends Robot {
	public EngineRobot(RobotPool pool) {
		super(pool);
	}

	protected void performService() {
		print(this + " installing engine");
		assembler.car().addEngine();
	}
}
