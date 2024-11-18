package com.deltaecho07.saferpay;

import com.deltaecho07.saferpay.configuration.PaymentConfiguration;
import com.deltaecho07.saferpay.models.Amount;
import com.deltaecho07.saferpay.models.InitPaymentDetails;
import com.deltaecho07.saferpay.models.ResponseHeader;
import com.deltaecho07.saferpay.models.paymentpage.asserting.PaymentPageAssertRespDto;
import com.deltaecho07.saferpay.models.paymentpage.asserting.TransactionStatus;
import com.deltaecho07.saferpay.models.paymentpage.init.InitializePaymentPageRespDto;
import com.deltaecho07.saferpay.models.paymentpage.init.ReturnUrl;
import com.deltaecho07.saferpay.models.transaction.capture.CaptureStatus;
import com.deltaecho07.saferpay.models.transaction.capture.TransactionCaptureRespDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class SaferpayServiceTest {
    private static MockWebServer mockWebServer;

    private SaferpayService saferpayService;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void givenPaymentConfiguration_whenCreateInstanceOfSaferpayService_thenSuccessful(){
        // given
        PaymentConfiguration paymentConfiguration=getPaymentConfiguration();
        paymentConfiguration.setApiUrl(mockWebServer.url("").toString());

        // when
        saferpayService = new SaferpayService(paymentConfiguration);

        // then
        assertNotNull(saferpayService);
    }

    @Test
    void givenSaferpayService_whenInitialize_thenSuccessful() throws JsonProcessingException {
        // given
        PaymentConfiguration paymentConfiguration=getPaymentConfiguration();
        paymentConfiguration.setApiUrl(mockWebServer.url("").toString());
        saferpayService = new SaferpayService(paymentConfiguration);
        InitPaymentDetails initPaymentDetails = new InitPaymentDetails(
                new Amount(BigDecimal.valueOf(100), Currency.getInstance("CHF")),
                new ReturnUrl("https://returnurl.com"),
                "orderId",
                "Testpayment"
        );
        MockResponse mockResponse = new MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json")
                .setBody(JsonConverterForTest.asJsonString(getInitializePaymentPageRespDto()));
        mockWebServer.enqueue(mockResponse);

        // when
        var result= saferpayService.initializeNewPayment(initPaymentDetails);

        // then
        assertNotNull(result);
    }

    @Test
    void givenAbortedPayment_whenAssertPayment_thenCorrectlyHandled(){
        // given
        PaymentConfiguration paymentConfiguration=getPaymentConfiguration();
        HttpUrl url = mockWebServer.url("");
        paymentConfiguration.setApiUrl(url.toString());
        saferpayService = new SaferpayService(paymentConfiguration);
        MockResponse mockResponse = new MockResponse().setResponseCode(402).addHeader("Content-Type", "application/json");
        mockWebServer.enqueue(mockResponse);

        // when
        PaymentPageAssertRespDto response = saferpayService.assertPayment("Token");

        // then
        assertNull(response);
    }

    @Test
    void givenPayment_whenCapturePayment_thenSuccessful() throws JsonProcessingException {
        // given
        PaymentConfiguration paymentConfiguration=getPaymentConfiguration();
        HttpUrl url = mockWebServer.url("");
        paymentConfiguration.setApiUrl(url.toString());
        saferpayService = new SaferpayService(paymentConfiguration);
        MockResponse mockResponse = new MockResponse().setResponseCode(200)
                .addHeader("Content-Type", "application/json")
                .setBody(JsonConverterForTest.asJsonString(getTransactionCaptureRespDto()));
        mockWebServer.enqueue(mockResponse);

        // when
        var response = saferpayService.capturePayment("Token");

        // then
        assertNotNull(response);
        assertEquals(CaptureStatus.CAPTURED,response.Status());
    }

    @Test
    void givenSaferpayService_whenUpdatePaymentConfig_thenSuccessful(){
        // given
        PaymentConfiguration paymentConfiguration=getPaymentConfiguration();
        paymentConfiguration.setApiUrl(mockWebServer.url("").toString());
        saferpayService = new SaferpayService(paymentConfiguration);
        PaymentConfiguration newPaymentConfiguration = getPaymentConfiguration();
        String newUrl = mockWebServer.url("").toString();
        newPaymentConfiguration.setApiUrl(newUrl);

        // when
        saferpayService.setPaymentConfiguration(newPaymentConfiguration);

        // then
        assertNotNull(saferpayService);
        assertEquals(newUrl,saferpayService.getPaymentConfiguration().getApiUrl());
    }

    private PaymentConfiguration getPaymentConfiguration(){
        return new PaymentConfiguration(
                "Test",
                "Test",
                "https://test.com",
                "tester",
                "password");
    }

    private InitializePaymentPageRespDto getInitializePaymentPageRespDto(){
       return new InitializePaymentPageRespDto(
                new ResponseHeader("1.38","1234"),
                "Token1234",
                "2024-01-13T22:47:42.040+01:00","https://redirectUrl.com");
    }

    private TransactionCaptureRespDto getTransactionCaptureRespDto(){
        return new TransactionCaptureRespDto(
                new ResponseHeader("1.38","1234"),
                "TransactionId1234",
                CaptureStatus.CAPTURED,"2024-01-13T22:47:42.040+01:00");
    }
}