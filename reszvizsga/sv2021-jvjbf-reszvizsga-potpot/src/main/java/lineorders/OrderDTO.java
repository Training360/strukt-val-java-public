package lineorders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String productNumber;
    private int shippingPrice;
    private LocalDate orderDate;
    private LocalDate shippingDate;
}
