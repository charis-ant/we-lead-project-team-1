package gr.athtech.spring.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Order extends BaseModel{
    private Store store;
    private Account account;
    private Date date;
    private Map<Product, Integer> products; //Maybe we would need to add Id not Product
    //Need to Add Hash and Equals properties
    private Integer orderRating;
    private BigDecimal total;
    private PaymentMethod paymentMethod;
    private BigDecimal deliveryTip;
    private Status status;
}
