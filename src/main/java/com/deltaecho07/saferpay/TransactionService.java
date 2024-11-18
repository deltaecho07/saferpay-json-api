package com.deltaecho07.saferpay;

import com.deltaecho07.saferpay.configuration.PaymentConfiguration;
import com.deltaecho07.saferpay.models.RequestHeader;
import com.deltaecho07.saferpay.models.transaction.capture.TransactionCaptureReqDto;
import com.deltaecho07.saferpay.models.transaction.capture.TransactionCaptureRespDto;
import com.deltaecho07.saferpay.models.transaction.capture.TransactionReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class TransactionService {
    private final  PaymentConfiguration paymentConfiguration;
    private static final String AUTHORIZATION="Authorization";
    private static final String BASE_URL="/Payment/v1/Transaction";

    public TransactionService(PaymentConfiguration paymentConfiguration){
        this.paymentConfiguration=paymentConfiguration;
    }

    public TransactionCaptureRespDto capturePayment(String transactionId){
        WebClient webClient = getWebClient();
        return webClient
                .post()
                .uri("/Capture")
                .header(AUTHORIZATION,getAuthHeaderValue())
                .bodyValue(getCaptureReqDto(transactionId))
                .retrieve()
                .bodyToMono(TransactionCaptureRespDto.class)
                .block();
    }

    private WebClient getWebClient(){
        return WebClient.builder()
                .baseUrl(paymentConfiguration.getApiUrl()+BASE_URL)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private TransactionCaptureReqDto getCaptureReqDto(String id){
        RequestHeader header = new RequestHeader(PaymentConfiguration.getSpecVersion(),
                paymentConfiguration.getCustomerId(), UUID.randomUUID().toString(),0);
        return new TransactionCaptureReqDto(header,new TransactionReference(id));
    }

    private String getAuthHeaderValue(){
        return "Basic "+ Base64.getEncoder()
                .encodeToString((paymentConfiguration.getUsername()+":"+paymentConfiguration.getPassword()).getBytes(StandardCharsets.UTF_8));
    }
}
