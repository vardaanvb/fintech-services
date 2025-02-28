# fintech-services

# FintechServices API Testing Framework

## Overview
This project is a TestNG-based API testing framework for fintech services. It is designed to validate API functionalities including authentication, payment processing, third-party KYC verification, and more.

## Project Structure

```
FintechServices
│── src/main/java
│   ├── com.fintech.auth        # Authentication-related classes
│   ├── com.fintech.config      # Configuration settings
│   ├── com.fintech.helper      # Helper utilities
│   ├── com.fintech.models      # Data models
│   ├── com.fintech.retry       # Retry mechanisms
│   ├── com.fintech.utils       # Utility functions
│
│── src/test/java
│   ├── com.fintech.common
│   │   ├── BaseTest.java       # Base class for test execution setup
│   │   ├── BaseTestHelper.java # Helper functions for tests
│   ├── com.fintech.mock
│   │   ├── WireMockTest.java   # Mock service tests using WireMock
│   ├── com.fintech.payload
│   │   ├── Payload.java        # Request payload management
│   ├── com.fintech.tests
│   │   ├── FundsTransferTest.java     # Test cases for fund transfers
│   │   ├── PaymentFlowTest.java       # Test cases for payment flow
│   │   ├── ThirdPartyKYCTest.java     # Test cases for third-party KYC
│   │   ├── WireMockTest.java          # Additional WireMock test cases
│
│── Resources
│   ├── _files
│   │   ├── paymentRequest.json  # Sample request payload
│   │   ├── TestData.xlsx        # Test data file
│
│── test-output                 # TestNG reports and logs
│── config.properties            # Configuration properties
│── pom.xml                      # Maven build file
|__ testng.xml
```

