package gr.athtech.spring.app.transfer.resource;

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
    private StoreResource store;
    private AccountResource account;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    private Date date;
    private Map<ProductResource, Integer> products;
    private Integer orderRating;
    private BigDecimal total;
    private PaymentMethod paymentMethod;
    private BigDecimal deliveryTip;
    private Status status;
}
