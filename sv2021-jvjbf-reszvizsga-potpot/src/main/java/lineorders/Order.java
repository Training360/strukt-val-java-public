package lineorders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private long id;
    private String productNumber;
    private int shippingPrice;
    private LocalDate orderDate;
    private LocalDate shippingDate;

    public Order(long id, String productNumber) {
        this.id = id;
        this.productNumber = productNumber;
        orderDate = LocalDate.now();
    }
}
