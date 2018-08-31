package com.ipg.test.standalone;

import com.optimization.license.OptimizationSolver;
import com.optimization.license.lib.Job;

public class StandaloneApp {
	
	public static void main(String[] args) {
		
		Job longRunningJob = () -> {
			System.out.print("Running the job .");
			for (int i = 0; i < 60; i++) {
				try {
					Thread.sleep(1000);
					if (i % 2 == 0) {
						System.out.print(".");
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			System.out.println("Completed the job.");
		};
		
		OptimizationSolver mySolver = new OptimizationSolver();
		mySolver.solve(longRunningJob);

	}
}
