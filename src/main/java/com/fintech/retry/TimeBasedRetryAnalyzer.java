package com.fintech.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TimeBasedRetryAnalyzer implements IRetryAnalyzer {

    private static final long MAX_WAIT_TIME = 180000;  // 3 minutes in milliseconds
    private static final long MAX_BACKOFF_SECONDS = 30; // Maximum backoff time in seconds (capped)
    private long totalWaitTime = 0;
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        // Calculate the exponential backoff, but cap it at MAX_BACKOFF_SECONDS
        long backoffSeconds = (long) Math.min(Math.pow(2, retryCount), MAX_BACKOFF_SECONDS);
        
        // Convert backoff from seconds to milliseconds for Thread.sleep
        long waitTime = backoffSeconds * 1000;

        // Ensure total wait time doesn't exceed the MAX_WAIT_TIME
        if (totalWaitTime + waitTime <= MAX_WAIT_TIME) {
            totalWaitTime += waitTime;
            try {
                // Sleep for the calculated backoff time
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                // Handle the interruption
                Thread.currentThread().interrupt();
            }

            // Increment retryCount and continue retrying
            retryCount++;
            return true; // Retry the test
        }

        // Stop retrying if we've reached the time limit
        return false; // Do not retry
    }
}
