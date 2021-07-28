package lineorders;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderCommand {

    @NotBlank(message = "ProductNumber cannot be blank")
    private String productNumber;

}
