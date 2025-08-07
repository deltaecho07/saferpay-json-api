package io.github.deltaecho07.saferpay;

import io.github.deltaecho07.saferpay.configuration.PaymentConfiguration;
import io.github.deltaecho07.saferpay.models.InitPaymentDetails;
import io.github.deltaecho07.saferpay.models.paymentpage.asserting.PaymentPageAssertRespDto;
import io.github.deltaecho07.saferpay.models.paymentpage.init.InitializePaymentPageRespDto;
import io.github.deltaecho07.saferpay.models.transaction.capture.TransactionCaptureRespDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class SaferpayService {
    @Getter
    @Setter
    private PaymentConfiguration paymentConfiguration;
    private final PaymentPageService paymentPageService;
    private final TransactionService transactionService;

    public SaferpayService(PaymentConfiguration paymentConfiguration){
        this.paymentConfiguration=paymentConfiguration;
        this.paymentPageService=new PaymentPageService(paymentConfiguration);
        this.transactionService=new TransactionService(paymentConfiguration);
    }

    public InitializePaymentPageRespDto initializeNewPayment(InitPaymentDetails initPaymentDetails){
        return paymentPageService
                .initializeNewPayment(initPaymentDetails);
    }

    public PaymentPageAssertRespDto assertPayment(String token){
        try{
            return paymentPageService.assertPayment(token);
        }
        catch (WebClientResponseException exception){
            if( exception.getStatusCode().is4xxClientError()){
                return null;
            }
            else{
                throw exception;
            }
        }
    }

    public TransactionCaptureRespDto capturePayment(String transactionId){
        return transactionService.capturePayment(transactionId);
    }

}
