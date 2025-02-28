package com.fintech.retry;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.fintech.utils.ConfigManager;

import java.net.SocketTimeoutException;

public class ApiRetryAnalyzer implements IRetryAnalyzer {

	private static final int MAX_RETRY_COUNT = Integer
			.parseInt(ConfigManager.getInstance().getProperty("retryAttempts"));
	private static final long MAX_BACKOFF_SECONDS = Long
			.parseLong(ConfigManager.getInstance().getProperty("maxWaitTime"));

	private int retryCount = 0;

	@Override
	public boolean retry(ITestResult result) {
		Throwable exception = result.getThrowable();

		boolean isConnectionException = isConnectionException(exception);

		if (isConnectionException) {

			long backoffSeconds = (long) Math.min(Math.pow(2, retryCount), (double) MAX_BACKOFF_SECONDS);

			System.out.println(
					"Connection exception. Retrying after " + backoffSeconds + " seconds. Attempt " + (retryCount + 1));

			try {
				Thread.sleep(backoffSeconds * 1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		} else {
			if (retryCount >= MAX_RETRY_COUNT) {
				return false;
			}
			System.out.println("Retrying test. Attempt " + (retryCount + 1) + "/" + MAX_RETRY_COUNT);
		}

		retryCount++;
		return true;
	}

	// Check if the exception is one of our connection exceptions
	private boolean isConnectionException(Throwable exception) {
		return exception instanceof HttpHostConnectException || exception instanceof SocketTimeoutException
				|| exception instanceof ConnectTimeoutException;
	}
}