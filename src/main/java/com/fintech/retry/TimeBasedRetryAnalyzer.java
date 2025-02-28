package com.fintech.retry;

import java.util.Calendar;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.fintech.utils.ConfigManager;

public class TimeBasedRetryAnalyzer implements IRetryAnalyzer {

	private static final long MAX_WAIT_TIME = Long.parseLong(ConfigManager.getInstance().getProperty("maxWaitTime"));
	private static final long MAX_BACKOFF_SECONDS = Long
			.parseLong(ConfigManager.getInstance().getProperty("maxBackOffSecond"));
	private long totalWaitTime = 0;
	private int retryCount = 0;

	@Override
	public boolean retry(ITestResult result) {

		long backoffSeconds = (long) Math.min(Math.pow(2, retryCount), MAX_BACKOFF_SECONDS);

		long waitTime = backoffSeconds * 1000;

		if (totalWaitTime + waitTime <= MAX_WAIT_TIME) {
			totalWaitTime += waitTime;

			System.out.println(
					"Connection exception. Retrying after " + waitTime + " seconds. Attempt " + (retryCount + 1));

			try {

				Thread.sleep(waitTime);
				// Not ideal to use thread.sleep
				// Calendar class be used

				/*
				 * Calendar startTime = Calendar.getInstance(); startTime.add(Calendar.MINUTE,
				 * waitTime);
				 * 
				 * // Keep checking until the current time reaches the target time while
				 * (Calendar.getInstance().before(startTime)) {
				 * 
				 * }
				 */

			} catch (InterruptedException e) {

				Thread.currentThread().interrupt();
			}

			retryCount++;
			return true;
		}

		// Stop retrying if we've reached the time limit
		return false; // Do not retry
	}
}
