package com.optimization.license.lib;

public abstract class LicensedJob extends Thread {

	public LicensedJob() {
		if (!LicenseUtil.checkLicenseAvailable() && LicenseUtil.INSTANCE.getMaxLicenseUsage() != 0) {

			/**
			 * Monitoring job will be triggered to monitor the license usage availablity
			 */
			new Thread(() -> {

				/**
				 * If the usage available, then bring the thread back from the waiting state.
				 */
				while (!LicenseUtil.checkLicenseAvailable()) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				synchronized (this) {
					this.notify();
				}
				
			}).start();

			synchronized (this) {
				try {
					/**
					 * If the no of usages exhausted, then push the job to the waiting state.
					 */
					this.wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
		LicenseUtil.updateLicenseUsage(true);
	}

	protected void shutdown()  {
		LicenseUtil.updateLicenseUsage(false);
	}
}

