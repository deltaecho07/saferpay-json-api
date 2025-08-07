package io.github.deltaecho07.saferpay.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentConfiguration {
    private static final String SPEC_VERSION ="1.38";
    private String customerId;
    private String terminalId;
    private String apiUrl;
    private String username;
    private String password;

    public static String getSpecVersion() {
        return SPEC_VERSION;
    }
}
