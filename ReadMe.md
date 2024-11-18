# Java Saferpay Implementation
## Introduction
This library enables you to initialize and capture payments using the Saferpay API. It is a simple implementation that allows you to create a payment page and capture payments. It is not a full implementation of the Saferpay API, but it should be enough to get you started.

### Supported Features
- uses Spec Version 1.38
- Initialize payment
- Assert payment
- Capture payment

### Saferpay API Documentation
For more information about the Saferpay API, please refer to the official documentation: https://saferpay.github.io/jsonapi/index.html


## Getting Started
### Add Maven Dependency
Add the following dependency to your pom.xml file:
```xml
<dependency>
    <groupId>com.deltaecho07</groupId>
    <artifactId>saferpay</artifactId>
    <version>2024-11-1-beta</version>
</dependency>
```
### Create Test Account
To test the Saferpay API, you will need to create a test account. You can create a test account here: https://test.saferpay.com/BO/Welcome

### Initialize Saferpay
To initialize Saferpay, you need to create a new instance of the PaymentConfiguration class. You will need to provide the following parameters:
- **customerId**: Your Saferpay customer ID
- **terminalId**: Your Saferpay terminal ID
- **apiUrl**: The Saferpay API URL (e.g. https://test.saferpay.com/api/v1)
- **username**: Your Saferpay username
- **password**: Your Saferpay password

```java
PaymentConfiguration configuration = new PaymentConfiguration("customerId", "terminalId", "apiUrl", "username", "password");
Saferpay saferpay = new Saferpay(configuration);
```
You can then use the saferpay instance to initialize, assert and capture payments.