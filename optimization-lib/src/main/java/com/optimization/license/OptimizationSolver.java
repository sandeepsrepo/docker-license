package com.optimization.license;

import com.optimization.license.lib.Environment;
import com.optimization.license.lib.Job;
import com.optimization.license.lib.LicenseUtil;
import com.optimization.license.lib.LicensedJob;

public class OptimizationSolver extends LicensedJob{
	
	static {
		LicenseUtil.INSTANCE.setMaxLicenseUsage(2);
		LicenseUtil.INSTANCE.setEnvironment(Environment.DEV);
	}
	
	private volatile Job job;
	
	public OptimizationSolver() {}
	
	public void solve(Job job) {
		this.job = job;
		start();
	}
	
	@Override
	public void run() {
		job.executeJob();
		shutdown();
	}
		
}
