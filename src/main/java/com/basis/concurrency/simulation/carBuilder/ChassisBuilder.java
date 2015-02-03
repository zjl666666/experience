package com.basis.concurrency.simulation.carBuilder;

import java.util.concurrent.TimeUnit;

import static com.basis.concurrency.Print.print;

public class ChassisBuilder implements Runnable {
	private CarQueue carQueue;
	private int counter = 0;

	public ChassisBuilder(CarQueue cq) {
		carQueue = cq;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				TimeUnit.MILLISECONDS.sleep(500);
				// Make chassis:
				Car c = new Car(counter++);
				print("ChassisBuilder created " + c);
				// Insert into queue
				carQueue.put(c);
			}
		} catch (InterruptedException e) {
			print("Interrupted: ChassisBuilder");
		}
		print("ChassisBuilder off");
	}
}