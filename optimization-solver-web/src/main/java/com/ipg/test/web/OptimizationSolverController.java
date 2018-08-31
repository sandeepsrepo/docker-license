package com.ipg.test.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.optimization.license.OptimizationSolver;
import com.optimization.license.lib.Job;

@RestController
public class OptimizationSolverController {

	@GetMapping(value="/testing/{id}")
	public String getTicket(@PathVariable("id") Integer id) {
		
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
		
		return "Job "+id+" Completed Successfully";
	}
	
}
