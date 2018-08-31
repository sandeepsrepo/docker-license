package com.optimization.license.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public enum LicenseUtil {

	INSTANCE;

	private static int maxParallelExecutions;
	private static Environment environment;

	private static int licenseUsage = 0;

	static {
		try {
			File file = new File("filename.txt");
			if (!file.exists()) {
				new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filename.txt"), "utf-8"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		licenseUsage = readLicenseUsage();
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	public Environment setEnvironment() {
		return environment;
	}
	
	public void setMaxLicenseUsage(int parallelExcecutions) {
		maxParallelExecutions = parallelExcecutions;
	}
	
	public int getMaxLicenseUsage() {
		return maxParallelExecutions;
	}

	public static boolean checkLicenseAvailable() {

		return readLicenseUsage() == maxParallelExecutions ? false : true;

	}

	public static void updateLicenseUsage(boolean using) {

		String data = "";
		licenseUsage = readLicenseUsage();
		if (using)
			data += (++licenseUsage);
		else {
			data += (--licenseUsage);
		}
		try {
			Files.write(Paths.get("filename.txt"), data.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static int readLicenseUsage() {

		String content;
		try {
			content = new String(Files.readAllBytes(Paths.get("filename.txt")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return Integer.parseInt(content.length() > 0 ? content.toString() : "0");
	}

}
