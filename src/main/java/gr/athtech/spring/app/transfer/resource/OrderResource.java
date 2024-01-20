package gr.athtech.spring.app.transfer.resource;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import gr.athtech.spring.app.model.PaymentMethod;
import gr.athtech.spring.app.model.Status;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ToString(callSuper = true)
public class OrderResource extends BaseResource {
    private StoreResource store; //Do we have to add something??
    private AccountResource account; //Same here ??
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    private Date date;

    private Map<ProductResource, Integer> products;

    @NotNull(message = "Order Rating cannot be null")
    @Pattern(regexp = "[1-5]", message = "The Order Rating format is not correct")
    private Integer orderRating;

    @NotNull(message = "Total amount cannot be null")
    private BigDecimal total;

    @NotNull(message = "Payment method cannot be null")
    private PaymentMethod paymentMethod;

    private BigDecimal deliveryTip;

    private Status status;
}
