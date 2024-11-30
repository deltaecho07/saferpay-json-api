package com.github.deltaecho07.saferpay;

import com.github.deltaecho07.saferpay.configuration.PaymentConfiguration;
import com.github.deltaecho07.saferpay.models.InitPaymentDetails;
import com.github.deltaecho07.saferpay.models.RequestHeader;
import com.github.deltaecho07.saferpay.models.paymentpage.asserting.PaymentPageAssertReqDto;
import com.github.deltaecho07.saferpay.models.paymentpage.asserting.PaymentPageAssertRespDto;
import com.deltaecho07.saferpay.models.paymentpage.init.*;
import com.github.deltaecho07.saferpay.models.paymentpage.init.AmountDto;
import com.github.deltaecho07.saferpay.models.paymentpage.init.InitializePaymentPageReqDto;
import com.github.deltaecho07.saferpay.models.paymentpage.init.InitializePaymentPageRespDto;
import com.github.deltaecho07.saferpay.models.paymentpage.init.PaymentDto;
import org.apache.commons.math3.util.FastMath;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class PaymentPageService {
    private final PaymentConfiguration paymentConfiguration;
    private static final String AUTHORIZATION="Authorization";
    private static final String BASE_URL="/Payment/v1/PaymentPage";

    public PaymentPageService(PaymentConfiguration paymentConfiguration){
        this.paymentConfiguration=paymentConfiguration;
    }

    public InitializePaymentPageRespDto initializeNewPayment(InitPaymentDetails initPaymentDetails){
        WebClient webClient = getWebClient();
        return webClient
                .post()
                .uri("/Initialize")
                .header(AUTHORIZATION,getAuthHeaderValue())
                .bodyValue(getInitReqDto(initPaymentDetails))
                .retrieve()
                .bodyToMono(InitializePaymentPageRespDto.class)
                .block();
    }

    public PaymentPageAssertRespDto assertPayment(String token){
        WebClient webClient = getWebClient();
        return webClient
                .post()
                .uri("/Assert")
                .header(AUTHORIZATION,getAuthHeaderValue())
                .bodyValue(getAssertReqDto(token))
                .retrieve()
                .bodyToMono(PaymentPageAssertRespDto.class)
                .block();
    }

    private WebClient getWebClient(){
        return WebClient.builder()
                .baseUrl(paymentConfiguration.getApiUrl()+BASE_URL)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private InitializePaymentPageReqDto getInitReqDto(InitPaymentDetails initPaymentDetails){
        RequestHeader header = new RequestHeader(PaymentConfiguration.getSpecVersion(),
                paymentConfiguration.getCustomerId(),UUID.randomUUID().toString(),0);
        BigInteger amountSmall = BigInteger.valueOf(
                FastMath.round(
                        initPaymentDetails.amount().value().multiply(BigDecimal.valueOf(100)).doubleValue()
                )
        );
        AmountDto amountDto = new AmountDto(amountSmall.toString(),initPaymentDetails.amount().currency().toString());
        PaymentDto paymentDto = new PaymentDto(
                amountDto,initPaymentDetails.orderId(),
                initPaymentDetails.description());
        return new InitializePaymentPageReqDto(
                header,
                paymentConfiguration.getTerminalId(),
                paymentDto,
                initPaymentDetails.returnUrl());
    }

    private PaymentPageAssertReqDto getAssertReqDto(String token){
        RequestHeader header = new RequestHeader(paymentConfiguration.getSpecVersion(),
                paymentConfiguration.getCustomerId(),UUID.randomUUID().toString(),0);
        return new PaymentPageAssertReqDto(header,token);
    }

    private String getAuthHeaderValue(){
        return "Basic "+Base64.getEncoder()
                .encodeToString((paymentConfiguration.getUsername()+":"+paymentConfiguration.getPassword()).getBytes(StandardCharsets.UTF_8));
    }
}
