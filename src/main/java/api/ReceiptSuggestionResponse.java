package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class ReceiptSuggestionResponse {
    @JsonProperty
    public final String merchantName;

    @JsonProperty
    public final BigDecimal amount;


    public ReceiptSuggestionResponse(String merchantName, BigDecimal amount) {
        this.merchantName = merchantName;
        this.amount = amount;
    }
}
